import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private static int[] numeros = new int[90];
	private static int pos = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		for (int i = 0; i < 7; i++) {
			generarNumero();
		}
		
		for (int j = 0; j < 10; j++) {
			System.out.println(numeros[j]);
		}
	}

	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}
	
	private static void generarNumero() {
		boolean igual = false;
		do {
			igual = false;
			int num = (int)(Math.random()*90+1);
			for (int i = 0; i < pos; i++) {
				if(numeros[i] == num) {
					igual = true;
					System.out.println("------------------");
				}
			}

			if(!igual) {
				numeros[pos] = num;
				pos++;
			}
		} while (igual);
	}
}
