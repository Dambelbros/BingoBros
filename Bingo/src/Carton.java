import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Carton extends JFrame {
	private final static int COL = 9;
	private final static int FIL = 3;
	private JPanel contentPane;
	private static int[][] numeros = new int[COL][FIL];
	private static JButton[][] carton = new JButton[COL][FIL];

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
	}

	public Carton() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 530, 128);
		layeredPane.add(panel);
		panel.setLayout(new GridLayout(3, 9, 0, 0));
		
		JButton btnV1H1 = new JButton("");
		panel.add(btnV1H1);
		carton[0][0] = btnV1H1;
		
		JButton btnV1H2 = new JButton("");
		panel.add(btnV1H2);
		carton[1][0] = btnV1H2;
		
		JButton btnV1H3 = new JButton("");
		panel.add(btnV1H3);
		carton[2][0] = btnV1H3;
		
		JButton btnV1H4 = new JButton("");
		panel.add(btnV1H4);
		carton[3][0] = btnV1H4;
		
		JButton btnV1H5 = new JButton("");
		panel.add(btnV1H5);
		carton[4][0] = btnV1H5;
		
		JButton btnV1H6 = new JButton("");
		panel.add(btnV1H6);
		carton[5][0] = btnV1H6;
		
		JButton btnV1H7 = new JButton("");
		panel.add(btnV1H7);
		carton[6][0] = btnV1H7;
		
		JButton btnV1H8 = new JButton("");
		panel.add(btnV1H8);
		carton[7][0] = btnV1H8;
		
		JButton btnV1H9 = new JButton("");
		panel.add(btnV1H9);
		carton[8][0] = btnV1H9;
		
		JButton btnV2H1 = new JButton("");
		panel.add(btnV2H1);
		carton[0][1] = btnV2H1;
		
		JButton btnV2H2 = new JButton("");
		panel.add(btnV2H2);
		carton[1][1] = btnV2H2;
		
		JButton btnV2H3 = new JButton("");
		panel.add(btnV2H3);
		carton[2][1] = btnV2H3;
		
		JButton btnV2H4 = new JButton("");
		panel.add(btnV2H4);
		carton[3][1] = btnV2H4;
		
		JButton btnV2H5 = new JButton("");
		panel.add(btnV2H5);
		carton[4][1] = btnV2H5;
		
		JButton btnV2H6 = new JButton("");
		panel.add(btnV2H6);
		carton[5][1] = btnV2H6;
		
		JButton btnV2H7 = new JButton("");
		panel.add(btnV2H7);
		carton[6][1] = btnV2H7;
		
		JButton btnV2H8 = new JButton("");
		panel.add(btnV2H8);
		carton[7][1] = btnV2H8;
		
		JButton btnV2H9 = new JButton("");
		panel.add(btnV2H9);
		carton[8][1] = btnV2H9;
		
		JButton btnV3H1 = new JButton("");
		panel.add(btnV3H1);
		carton[0][2] = btnV3H1;
		
		JButton btnV3H2 = new JButton("");
		panel.add(btnV3H2);
		carton[1][2] = btnV3H2;
		
		JButton btnV3H3 = new JButton("");
		panel.add(btnV3H3);
		carton[2][2] = btnV3H3;
		
		JButton btnV3H4 = new JButton("");
		panel.add(btnV3H4);
		carton[3][2] = btnV3H4;
		
		JButton btnV3H5 = new JButton("");
		panel.add(btnV3H5);
		carton[4][2] = btnV3H5;
		
		JButton btnV3H6 = new JButton("");
		panel.add(btnV3H6);
		carton[5][2] = btnV3H6;
		
		JButton btnV3H7 = new JButton("");
		panel.add(btnV3H7);
		carton[6][2] = btnV3H7;
		
		JButton btnV3H8 = new JButton("");
		panel.add(btnV3H8);
		carton[7][2] = btnV3H8;
		
		JButton btnV3H9 = new JButton("");
		panel.add(btnV3H9);
		carton[8][2] = btnV3H9;
		
		generarEspaciosVaciosJ();
		generarNumerosColumnasJ();
		generarListener();
	}
	
	private static void generarListener() {
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < FIL; j++) {
				cambioColor(carton[i][j]);
			}
		}
	}
	
	private static void generarEspaciosVaciosJ() {
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
				carton[vacio[i]][fila].setBackground(Color.BLACK);
				carton[vacio[i]][fila].setEnabled(false);
			}
		}
	}
	
	private static void generarNumerosColumnasJ() {
		int min, sum;
		for (int i = 0; i < COL; i++) {
			min = i * 10;
			for (int j = 0; j < FIL; j++) {
				if(!carton[i][j].getBackground().equals(Color.BLACK)) {
					do {
						sum = (int)(Math.random()*10+1) + min;
					} while (aNumeroJ(carton[i][0]) == sum || aNumeroJ(carton[i][1]) == sum || aNumeroJ(carton[i][2]) == sum);
					
					carton[i][j].setText(String.valueOf(sum));	
				}
			}
		}
	}
	
	private static int aNumeroJ(JButton boton) {
		if(esNumero(boton.getText())) {
			return Integer.parseInt(boton.getText());
		}
		
		return 0;
	}
	
	private static void cambioColor(JButton boton) {
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boton.setBackground(Color.GREEN);
			}
		});
	}
	
	public static boolean esNumero(String str) { 
		try {  
		    Double.parseDouble(str);  
		    return true;
		} catch(NumberFormatException e){  
		    return false;  
		}  
	}

}
