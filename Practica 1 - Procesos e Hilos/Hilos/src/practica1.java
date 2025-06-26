import java.util.Random;

class Corredor extends Thread {
    private String nombre;

    public Corredor(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        Random random = new Random();
        int distancia = 10;

        for (int i = 1; i <= distancia; i++) {
            System.out.println(nombre + " avanzó a la posición " + i);
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " ha terminado la carrera.");
    }
}

public class practica1 {
    public static void main(String[] args) {
        Corredor corredor1 = new Corredor("Corredor 1");
        Corredor corredor2 = new Corredor("Corredor 2");

        corredor1.start();
        corredor2.start();
    }
}
