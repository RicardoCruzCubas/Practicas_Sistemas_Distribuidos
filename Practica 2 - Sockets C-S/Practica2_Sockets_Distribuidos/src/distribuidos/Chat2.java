package distribuidos;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.ActiveEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class Chat2 extends JFrame implements Observer{

	private JTextField text1;
	private JButton btn1;
	private JTextArea Area1;
	private JScrollPane panel1;
	
	public Chat2() {
		
		getContentPane().setLayout(null);
		
		Area1 = new JTextArea();
		
		panel1 = new JScrollPane(Area1);
		panel1.setBounds(10, 10, 460, 200);
		getContentPane().add(panel1);
		
		text1 = new JTextField();
		text1.setBounds(10, 220, 270, 40);
		getContentPane().add(text1);
		
		btn1 = new JButton("Enviar");
		btn1.setBounds(290, 220, 180, 40);
		getContentPane().add(btn1);
		
		ActionListener act1 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String mensaje = "2.- " + text1.getText() + "\n";
				Area1.append(mensaje);
				
				Cliente clien = new Cliente(5000, mensaje);
				Thread hilo2 = new Thread(clien);
				hilo2.start();
			}
		};
		btn1.addActionListener(act1);
		
		this.setSize(500, 320);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Chat 2");
		
		Servidor server = new Servidor(6000);
		server.addObserver(this);
		
		Thread hilo1 = new Thread(server);
		hilo1.start();
	}
	
	public static void main(String[] args) {
		Chat2 obj = new Chat2();
	}
	
	public void update(Observable o, Object arg) {
		this.Area1.append((String) arg);
	}

}
