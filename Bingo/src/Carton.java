import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Carton extends JFrame {
	private final static int COL = 9;
	private final static int FIL = 3;
	private JPanel contentPane;
	private static int[][] numeros = new int[COL][FIL];
	private final JButton btnNewButton_15 = new JButton("New button");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Carton frame = new Carton();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		for (int j = 0; j < FIL; j++) {
			for (int i = 0; i < COL; i++) {
				numeros[i][j] = 120;
			}
		}
		
		generarEspaciosVacios(numeros);
		generarNumerosColumnas();
		mostrar();
	}

	public Carton() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 9, 0, 0));
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("New button");
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("New button");
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("New button");
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_5 = new JButton("New button");
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_7 = new JButton("New button");
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("New button");
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("New button");
		contentPane.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("New button");
		contentPane.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("New button");
		contentPane.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("New button");
		contentPane.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("New button");
		contentPane.add(btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton("New button");
		contentPane.add(btnNewButton_14);
		contentPane.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("New button");
		contentPane.add(btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton("New button");
		contentPane.add(btnNewButton_17);
		
		JButton btnNewButton_18 = new JButton("New button");
		contentPane.add(btnNewButton_18);
		
		JButton btnNewButton_19 = new JButton("New button");
		contentPane.add(btnNewButton_19);
		
		JButton btnNewButton_20 = new JButton("New button");
		contentPane.add(btnNewButton_20);
		
		JButton btnNewButton_21 = new JButton("New button");
		contentPane.add(btnNewButton_21);
		
		JButton btnNewButton_22 = new JButton("New button");
		contentPane.add(btnNewButton_22);
		
		JButton btnNewButton_24 = new JButton("New button");
		contentPane.add(btnNewButton_24);
		
		JButton btnNewButton_25 = new JButton("New button");
		contentPane.add(btnNewButton_25);
		
		JButton btnNewButton_26 = new JButton("New button");
		contentPane.add(btnNewButton_26);
		
		JButton btnNewButton_27 = new JButton("New button");
		contentPane.add(btnNewButton_27);
		
		JButton btnNewButton_23 = new JButton("New button");
		cotentPane.add(btnNewButton_23);
	}
	
	private static void generarEspaciosVacios(int[][] carton) {
		int[] vacio = new int[4];
		for (int fila = 0; fila < FIL; fila++) {
			int aux = 0;
			int num;
			boolean igual;
			while(aux < 4) {
				igual = false;
				num = (int)(Math.random()*9+0);
				for (int i = 0; i < vacio.length; i++) {
					if (num == vacio[i]) {
						igual = true;
					}
				}
				if(!igual) {
					vacio[aux] = num;
					aux++;
				}
			}
			
			for (int i = 0; i < vacio.length; i++) {
				carton[vacio[i]][fila] = 0;
			}
		}
	}
	
	private static void generarNumerosColumnas() {
		int min, max, sum;
		for (int i = 0; i < COL; i++) {
			min = i * 10;
			for (int j = 0; j < FIL; j++) {
				if(numeros[i][j] != 0) {
					sum = (int)(Math.random()*10+1) + min;
					if (numeros[i][0] != sum && numeros[i][1] != sum && numeros[i][2] != sum) {
						numeros[i][j] = sum;
					}
				}
			}
		}
	}
	
	private static void mostrar() {
		for (int j = 0; j < FIL; j++) {
			for (int i = 0; i < COL; i++) {
				if(numeros[i][j] > 9) {
					System.out.print(numeros[i][j] + " ");
				} else {
					System.out.print(" " + numeros[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
