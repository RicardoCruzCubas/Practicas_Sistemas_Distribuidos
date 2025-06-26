package Practica4;

import java.io.*;  
import java.net.*;  

public class Cliente {  
    public static void main(String[] args) {  
        String host = "localhost"; // Dirección por defecto  
        int puerto = 12345; // Puerto por defecto  

        // Verifica si el usuario ingresó un host y puerto como argumentos  
        if (args.length > 0) {  
            host = args[0];  
        }  
        if (args.length > 1) {  
            try {  
                puerto = Integer.parseInt(args[1]);  
            } catch (NumberFormatException e) {  
                System.out.println("Puerto inválido. Usando el puerto por defecto: " + puerto);
            }  
        }  

        try (Socket socket = new Socket(host, puerto);  
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);  
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {  

            System.out.println("Conectado al chat en " + host + ":" + puerto + ". Escribe tu mensaje:");  

            // Hilo para recibir mensajes del servidor
            new Thread(() -> {  
                try {  
                    String mensaje;  
                    while ((mensaje = entrada.readLine()) != null) {  
                        System.out.println(mensaje);  
                    }  
                } catch (IOException e) {  
                    System.out.println("Desconectado del servidor.");  
                }  
            }).start();  

            // Envío de mensajes al servidor
            String mensajeUsuario;  
            while ((mensajeUsuario = teclado.readLine()) != null) {  
                salida.println(mensajeUsuario);  
            }  

        } catch (IOException e) {  
            System.out.println("No se pudo conectar al servidor en " + host + ":" + puerto);  
        }  
    }  
}
