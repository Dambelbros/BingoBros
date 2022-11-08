import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class Carton extends JFrame {
	private final static int COL = 9;
	private final static int FIL = 3;
	private JPanel contentPane;
	private static int[][] numeros = new int[COL][FIL];

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
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 424, 128);
		layeredPane.add(panel);
		panel.setLayout(new GridLayout(3, 9, 0, 0));
		
		JButton btnV1H1 = new JButton("New button");
		panel.add(btnV1H1);
		
		JButton btnV1H2 = new JButton("New button");
		panel.add(btnV1H2);
		
		JButton btnV1H3 = new JButton("New button");
		panel.add(btnV1H3);
		
		JButton btnV1H4 = new JButton("New button");
		panel.add(btnV1H4);
		
		JButton btnV1H5 = new JButton("New button");
		panel.add(btnV1H5);
		
		JButton btnV1H6 = new JButton("New button");
		panel.add(btnV1H6);
		
		JButton btnV1H7 = new JButton("New button");
		panel.add(btnV1H7);
		
		JButton btnV1H8 = new JButton("New button");
		panel.add(btnV1H8);
		
		JButton btnV1H9 = new JButton("New button");
		panel.add(btnV1H9);
		
		JButton btnV2H1 = new JButton("New button");
		panel.add(btnV2H1);
		
		JButton btnV2H2 = new JButton("New button");
		panel.add(btnV2H2);
		
		JButton btnV2H3 = new JButton("New button");
		panel.add(btnV2H3);
		
		JButton btnV2H4 = new JButton("New button");
		panel.add(btnV2H4);
		
		JButton btnV2H5 = new JButton("New button");
		panel.add(btnV2H5);
		
		JButton btnNewButton_7_1 = new JButton("New button");
		panel.add(btnNewButton_7_1);
		
		JButton btnNewButton_6_1 = new JButton("New button");
		panel.add(btnNewButton_6_1);
		
		JButton btnNewButton_8_1 = new JButton("New button");
		panel.add(btnNewButton_8_1);
		
		JButton btnNewButton_9_1 = new JButton("New button");
		panel.add(btnNewButton_9_1);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JButton btnNewButton_5 = new JButton("New button");
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_11 = new JButton("New button");
		panel.add(btnNewButton_11);
		
		JButton btnNewButton_7 = new JButton("New button");
		panel.add(btnNewButton_7);
		
		JButton btnNewButton_6 = new JButton("New button");
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_8 = new JButton("New button");
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("New button");
		panel.add(btnNewButton_9);
		
		JButton btnNewButton_12 = new JButton("New button");
		panel.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("New button");
		panel.add(btnNewButton_13);
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
