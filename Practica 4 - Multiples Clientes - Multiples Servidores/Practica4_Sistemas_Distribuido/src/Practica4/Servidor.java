package Practica4;

import java.io.*;  
import java.net.*;  
import java.util.*;  

public class Servidor {  
    private static List<PrintWriter> clientes = new ArrayList<>();  

    public static void main(String[] args) {  
        int puerto = 12345; // Puerto por defecto  

        // Verifica si el usuario ingresó un puerto como argumento
        if (args.length > 0) {  
            try {  
                puerto = Integer.parseInt(args[0]);  
            } catch (NumberFormatException e) {  
                System.out.println("Puerto inválido. Usando el puerto por defecto: " + puerto);
            }  
        }  

        System.out.println("El servidor está en funcionamiento en el puerto " + puerto + "...");  

        try (ServerSocket servidor = new ServerSocket(puerto)) {  
            while (true) {  
                Socket socket = servidor.accept();  
                new ManejadorCliente(socket).start();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

    private static class ManejadorCliente extends Thread {  
        private Socket socket;  
        private PrintWriter salida;  

        public ManejadorCliente(Socket socket) {  
            this.socket = socket;  
        }  

        public void run() {  
            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {  
                salida = new PrintWriter(socket.getOutputStream(), true);  
                synchronized (clientes) {  
                    clientes.add(salida);  
                }  

                String mensaje;  
                while ((mensaje = entrada.readLine()) != null) {  
                    synchronized (clientes) {  
                        for (PrintWriter cliente : clientes) {  
                            cliente.println(mensaje);  
                        }  
                    }  
                }  
            } catch (IOException e) {  
                System.out.println("Cliente desconectado.");  
            } finally {  
                synchronized (clientes) {  
                    clientes.remove(salida);  
                }  
            }  
        }  
    }  
}
