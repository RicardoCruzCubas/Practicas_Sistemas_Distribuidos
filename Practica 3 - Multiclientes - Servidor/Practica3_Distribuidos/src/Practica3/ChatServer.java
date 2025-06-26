package Practica3;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static ExecutorService pool = Executors.newCachedThreadPool();
    
    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado en el puerto " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + clientSocket.getInetAddress());
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private static CopyOnWriteArrayList<PrintWriter> clients = new CopyOnWriteArrayList<>();

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
            clients.add(out);
            out.println("Bienvenido al chat! Escribe 'salir' para desconectarte.");
            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("salir")) {
                    out.println("Desconectando...");
                    break;
                }
                System.out.println("Mensaje recibido: " + message);
                broadcast("Mensaje de " + clientSocket.getInetAddress() + ": " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clients.removeIf(out -> out.checkError());
            System.out.println("Cliente desconectado");
        }
    }

    private void broadcast(String message) {
        for (PrintWriter client : clients) {
            client.println(message);
        }
    }
}

