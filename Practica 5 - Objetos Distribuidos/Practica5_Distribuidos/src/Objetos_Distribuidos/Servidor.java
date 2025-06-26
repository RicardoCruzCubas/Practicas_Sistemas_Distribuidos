package Objetos_Distribuidos;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements Calculadora {
    public Servidor() {}

    @Override
    public int sumar(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public int restar(int a, int b) throws RemoteException {
        return a - b;
    }

    public static void main(String[] args) {
        try {
            Servidor obj = new Servidor();
            Calculadora stub = (Calculadora) UnicastRemoteObject.exportObject(obj, 0);
            
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Calculadora", stub);

            System.out.println("Servidor listo...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
