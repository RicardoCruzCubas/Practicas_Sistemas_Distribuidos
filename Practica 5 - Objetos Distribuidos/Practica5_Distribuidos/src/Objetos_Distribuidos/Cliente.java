package Objetos_Distribuidos;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculadora stub = (Calculadora) registry.lookup("Calculadora");

            int suma = stub.sumar(5, 3);
            int resta = stub.restar(10, 4);

            System.out.println("Suma: " + suma);
            System.out.println("Resta: " + resta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
