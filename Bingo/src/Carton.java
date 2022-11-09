import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class Carton extends JFrame {
	private final static int COL = 9;
	private final static int FIL = 3;
	private JPanel contentPane;
	private static int[][] numeros = new int[COL][FIL];
	private static JButton[][] carton = new JButton[COL][FIL];
	private static JButton btnLinea;

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
		setBounds(100, 100, 567, 314);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBounds(0, 43, 541, 160);
		layeredPane.add(panel);
		panel.setLayout(new GridLayout(3, 9, 0, 0));

		JButton btnV1H1 = new JButton("");
		btnV1H1.setBackground(Color.WHITE);
		panel.add(btnV1H1);
		carton[0][0] = btnV1H1;

		JButton btnV1H2 = new JButton("");
		btnV1H2.setBackground(Color.WHITE);
		panel.add(btnV1H2);
		carton[1][0] = btnV1H2;

		JButton btnV1H3 = new JButton("");
		btnV1H3.setBackground(Color.WHITE);
		panel.add(btnV1H3);
		carton[2][0] = btnV1H3;

		JButton btnV1H4 = new JButton("");
		btnV1H4.setBackground(Color.WHITE);
		panel.add(btnV1H4);
		carton[3][0] = btnV1H4;

		JButton btnV1H5 = new JButton("");
		btnV1H5.setBackground(Color.WHITE);
		panel.add(btnV1H5);
		carton[4][0] = btnV1H5;

		JButton btnV1H6 = new JButton("");
		btnV1H6.setBackground(Color.WHITE);
		panel.add(btnV1H6);
		carton[5][0] = btnV1H6;

		JButton btnV1H7 = new JButton("");
		btnV1H7.setBackground(Color.WHITE);
		panel.add(btnV1H7);
		carton[6][0] = btnV1H7;

		JButton btnV1H8 = new JButton("");
		btnV1H8.setBackground(Color.WHITE);
		panel.add(btnV1H8);
		carton[7][0] = btnV1H8;

		JButton btnV1H9 = new JButton("");
		btnV1H9.setBackground(Color.WHITE);
		panel.add(btnV1H9);
		carton[8][0] = btnV1H9;

		JButton btnV2H1 = new JButton("");
		btnV2H1.setBackground(Color.WHITE);
		panel.add(btnV2H1);
		carton[0][1] = btnV2H1;

		JButton btnV2H2 = new JButton("");
		btnV2H2.setBackground(Color.WHITE);
		panel.add(btnV2H2);
		carton[1][1] = btnV2H2;

		JButton btnV2H3 = new JButton("");
		btnV2H3.setBackground(Color.WHITE);
		panel.add(btnV2H3);
		carton[2][1] = btnV2H3;

		JButton btnV2H4 = new JButton("");
		btnV2H4.setBackground(Color.WHITE);
		panel.add(btnV2H4);
		carton[3][1] = btnV2H4;

		JButton btnV2H5 = new JButton("");
		btnV2H5.setBackground(Color.WHITE);
		panel.add(btnV2H5);
		carton[4][1] = btnV2H5;

		JButton btnV2H6 = new JButton("");
		btnV2H6.setBackground(Color.WHITE);
		panel.add(btnV2H6);
		carton[5][1] = btnV2H6;

		JButton btnV2H7 = new JButton("");
		btnV2H7.setBackground(Color.WHITE);
		panel.add(btnV2H7);
		carton[6][1] = btnV2H7;

		JButton btnV2H8 = new JButton("");
		btnV2H8.setBackground(Color.WHITE);
		panel.add(btnV2H8);
		carton[7][1] = btnV2H8;

		JButton btnV2H9 = new JButton("");
		btnV2H9.setBackground(Color.WHITE);
		panel.add(btnV2H9);
		carton[8][1] = btnV2H9;

		JButton btnV3H1 = new JButton("");
		btnV3H1.setBackground(Color.WHITE);
		panel.add(btnV3H1);
		carton[0][2] = btnV3H1;

		JButton btnV3H2 = new JButton("");
		btnV3H2.setBackground(Color.WHITE);
		panel.add(btnV3H2);
		carton[1][2] = btnV3H2;

		JButton btnV3H3 = new JButton("");
		btnV3H3.setBackground(Color.WHITE);
		panel.add(btnV3H3);
		carton[2][2] = btnV3H3;

		JButton btnV3H4 = new JButton("");
		btnV3H4.setBackground(Color.WHITE);
		panel.add(btnV3H4);
		carton[3][2] = btnV3H4;

		JButton btnV3H5 = new JButton("");
		btnV3H5.setBackground(Color.WHITE);
		panel.add(btnV3H5);
		carton[4][2] = btnV3H5;

		JButton btnV3H6 = new JButton("");
		btnV3H6.setBackground(Color.WHITE);
		panel.add(btnV3H6);
		carton[5][2] = btnV3H6;

		JButton btnV3H7 = new JButton("");
		btnV3H7.setBackground(Color.WHITE);
		panel.add(btnV3H7);
		carton[6][2] = btnV3H7;

		JButton btnV3H8 = new JButton("");
		btnV3H8.setBackground(Color.WHITE);
		panel.add(btnV3H8);
		carton[7][2] = btnV3H8;

		JButton btnV3H9 = new JButton("");
		btnV3H9.setBackground(Color.WHITE);
		panel.add(btnV3H9);
		carton[8][2] = btnV3H9;

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 214, 541, 51);
		layeredPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		btnLinea = new JButton("LINEA");
		panel_1.add(btnLinea);

		JButton btnBingo = new JButton("BINGO");
		panel_1.add(btnBingo);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 531, 43);
		layeredPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("BINGOBROS");
		lblNewLabel.setFont(new Font("Snap ITC", Font.PLAIN, 35));
		lblNewLabel.setBounds(145, 0, 279, 43);
		panel_2.add(lblNewLabel);

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

		btnLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comprobarLinea();
			}
		});
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
				if (boton.getBackground().equals(Color.WHITE)) {
					boton.setBackground(Color.GREEN);
				} else {
					boton.setBackground(Color.WHITE);
				}
			}
		});
	}

	private static boolean esNumero(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}

	public static void comprobarLinea() {
		boolean correcto;
		for (int i = 0; i < FIL; i++) {
			correcto = true;
			for (int j = 0; j < COL; j++) {
				if(carton[j][i].getBackground().equals(Color.WHITE)){
					correcto = false;
					break;
				}
			}

			if (correcto) {
				try {
					Scanner ganado = new Scanner (new File("ganadoLinea"));
					ganado.close();
					JOptionPane.showMessageDialog(null, "Ya se ha cantado linea");
				} catch (FileNotFoundException e1) {
					try {
						Scanner fichero = new Scanner (new File("fichero"));
						String numero;
						int numUno, comprobados = 0;
						ArrayList<String> correctos = new ArrayList<String>();
						while (fichero.hasNext()) {
							numero = (String) fichero.next();
							correctos.add(numero);
						}

						for (int j = 0; j < COL; j++) {
							if(carton[j][i].getBackground().equals(Color.GREEN)) {
								numUno = aNumeroJ(carton[j][i]);
								for (int c = 0; c < correctos.size(); c++) {
									if(Integer.parseInt(correctos.get(c)) == numUno) {
										comprobados++;
										break;
									};
								}
							}
						}

						if (comprobados == 5) {
							JOptionPane.showMessageDialog(null, "Has cantado linea correctamente");
							PrintWriter ganado = new PrintWriter(new File("ganadoLinea"));
							ganado.close();
						} else {
							JOptionPane.showMessageDialog(null, "Has cantado linea incorrectamente");
						}

						for (int j = 0; j < 3; j++) {

						}
					} catch (Exception e) {

					}
				}
			}
		}
	}
	
	/*
	public static void comprobarBingo() {
		boolean correcto = true;
		try {
			Scanner fichero = new Scanner (new File("fichero"));
			String numero;
			ArrayList<String> correctos = new ArrayList<String>();
			while (fichero.hasNext()) {
				numero = (String) fichero.next();
				correctos.add(numero);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	*/
}
