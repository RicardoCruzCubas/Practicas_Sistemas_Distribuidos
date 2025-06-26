package distribuidos;
import java.net.Socket;
import java.io.DataOutputStream;

public class Cliente implements Runnable{

	private int puerto;
	private String mensaje;
	
	public Cliente(int puerto, String mensaje) {
		this.puerto = puerto;
		this.mensaje = mensaje;
	}
	
	public void run() {
		
		Socket sc = null;
		DataOutputStream out;
		String host = "127.0.0.1";
		
		try {
			sc = new Socket(host, puerto);
			
			out = new DataOutputStream(sc.getOutputStream());
			
			out.writeUTF(mensaje);
			
			sc.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		
	}
}
