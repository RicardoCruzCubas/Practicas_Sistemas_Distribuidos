package distribuidos;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.Observable;

public class Servidor extends Observable implements Runnable{

	private int puerto;
	
	public Servidor(int puerto) {
		this.puerto = puerto;
	}
	
	public void run() {
		
		ServerSocket server = null;
		Socket sc = null;
		DataInputStream in;
		
		try {
			server = new ServerSocket(puerto);
			System.out.println("Servidor Iniciado");
			
			while(true) {
				sc = server.accept();
				System.out.println("Cliente Conectado");
				
				in = new DataInputStream(sc.getInputStream());
				
				String mensaje = in.readUTF();
				System.out.println(mensaje);
				
				this.setChanged();
				this.notifyObservers(mensaje);
				this.clearChanged();
				
				sc.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

	}

}
