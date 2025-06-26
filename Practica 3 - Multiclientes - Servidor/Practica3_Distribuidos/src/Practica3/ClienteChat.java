package Practica3;

import java.io.*;
import java.net.*;

public class ClienteChat {
    private static final String DIRECCION_SERVIDOR = "localhost";
    private static final int PUERTO_SERVIDOR = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(DIRECCION_SERVIDOR, PUERTO_SERVIDOR);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor de chat. Escribe tu mensaje:");
            
            Thread hiloRecepcion = new Thread(() -> {
                try {
                    String mensajeServidor;
                    while ((mensajeServidor = entrada.readLine()) != null) {
                        System.out.println(mensajeServidor);
                    }
                } catch (IOException e) {
                    System.out.println("Conexión cerrada.");
                }
            });
            hiloRecepcion.start();
            
            String mensajeUsuario;
            while ((mensajeUsuario = entradaUsuario.readLine()) != null) {
                salida.println(mensajeUsuario);
                if (mensajeUsuario.equalsIgnoreCase("salir")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
