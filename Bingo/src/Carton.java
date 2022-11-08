import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
				numeros[i][j] = 1;
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
				System.out.print(vacio[i] + " ");
				carton[vacio[i]][fila] = 0;
			}
			System.out.println();
			
			System.out.println("Siguiente");
		}
	}
	
	private static void generarNumerosColumnas() {
		int num, min, max, sum;
		for (int i = 0; i < COL; i++) {
			System.out.println("Max: " + (i * 10 + 10));
			System.out.println("Min: " + (i * 10));
			min = i * 10;
			for (int j = 0; j < FIL; j++) {
				if(numeros[i][j] != 0) {
					sum = (int)(Math.random()*10+1);
					System.out.println(min + sum);
				}
			}
		}
	}
	
	private static void mostrar() {
		for (int j = 0; j < FIL; j++) {
			for (int i = 0; i < COL; i++) {
				System.out.print(numeros[i][j] + " ");
			}
			System.out.println();
		}
	}
}
