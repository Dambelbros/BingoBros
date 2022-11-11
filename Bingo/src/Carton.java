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
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Carton extends JFrame {
	private final static int COL = 9;
	private final static int FIL = 3;
	private JPanel contentPane;
	private static JButton[][] carton = new JButton[COL][FIL];
	private static JButton btnLinea, btnBingo;
	public static String nombre;
	private JButton btnNuevocarton;

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
	}

	public Carton() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("bolaCarton.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 314);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		JPanel panelCasillas = new JPanel();
		panelCasillas.setBounds(0, 43, 541, 160);
		layeredPane.add(panelCasillas);
		panelCasillas.setLayout(new GridLayout(3, 9, 0, 0));
		
		/*Genera los botones de las casillas*/
		for (int i = 0; i < FIL; i++) {
			for (int j = 0; j < COL; j++) {
				JButton btnCasilla = new JButton("");
				btnCasilla.setBackground(Color.WHITE);
				panelCasillas.add(btnCasilla);
				carton[j][i] = btnCasilla;
			}
		}

		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(0, 214, 541, 51);
		layeredPane.add(panelBotones);
		panelBotones.setLayout(null);

		btnLinea = new JButton("LINEA");
		btnLinea.setBounds(0, 0, 143, 40);
		panelBotones.add(btnLinea);

		btnBingo = new JButton("BINGO");
		btnBingo.setBounds(199, 0, 143, 40);
		panelBotones.add(btnBingo);
		
		btnNuevocarton = new JButton("NUEVO CARTÓN");
		btnNuevocarton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNuevocarton.setBounds(398, 0, 143, 40);
		panelBotones.add(btnNuevocarton);

		JPanel panelArriba = new JPanel();
		panelArriba.setBounds(0, 0, 531, 43);
		layeredPane.add(panelArriba);
		panelArriba.setLayout(null);

		JLabel lblNewLabel = new JLabel("BINGOBROS");
		lblNewLabel.setFont(new Font("Snap ITC", Font.PLAIN, 35));
		lblNewLabel.setBounds(145, 0, 279, 43);
		panelArriba.add(lblNewLabel);

		generarEspaciosVaciosJ();
		generarNumerosColumnasJ();
		generarListener();
		
		do {
			nombre = JOptionPane.showInputDialog("Nombre");
		} while (nombre.equals(""));
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
		
		btnBingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comprobarBingo();
			}
		});
	}

	private static void generarEspaciosVaciosJ() {
		int[] vacio = new int[4];
		for (int fila = 0; fila < FIL; fila++) {
			int aux = 0, num;
			boolean igual;
			
			while(aux < 4) {
				igual = false;
				num = (int)(Math.random()*9+0);
				/*Comprueba que la casilla no este en la lista de casillas vacias y si es 
				 * la ultima fila comprueba que las dos de arriba no esten en negro*/
				for (int i = 0; i < vacio.length; i++) {
					if (num == vacio[i] || (fila == 2 && (carton[num][0].getBackground().equals(Color.BLACK) && carton[num][1].getBackground().equals(Color.BLACK)))) {
						igual = true;
						i = 4;
					}
				}
				
				/*Si no es igual lo añade a la lista de las casillas negras*/
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
		int min, sum, menor, medio = 0, mayor, menorPos = 0, mayorPos = 0;
		int[] arrayNum = new int[3];
		for (int i = 0; i < COL; i++) {
			/*Genera numeros aleatorios comprobando que no coincide con ninguno de los anteriores*/
			min = i * 10;
			arrayNum[0] = (int)(Math.random()*10+1) + min;
			do {
				sum = (int)(Math.random()*10+1) + min;
			} while (arrayNum[0] == sum);
			arrayNum[1] = sum;
			do {
				sum = (int)(Math.random()*10+1) + min;
			} while (arrayNum[0] == sum || arrayNum[1] == sum);
			arrayNum[2] = sum;
			
			/*Ordena los numeros de menor a mayor*/
			mayor = arrayNum[0];
			menor = arrayNum[0];
			
			for (int j = 0; j < arrayNum.length; j++) {
				if (arrayNum[j] >= mayor) {
					mayor = arrayNum[j];
					mayorPos = j;
				}
				
				if(arrayNum[j] <= menor) {
					menor = arrayNum[j];
					menorPos = j;
				}
			}
			
			for (int j = 0; j < arrayNum.length; j++) {
				if(j != mayorPos && j != menorPos) {
					medio = arrayNum[j];
				}
			}
			
			/*Cambia fondo y deshabilita los botones de las casillas en negro*/
			if(carton[i][0].getBackground().equals(Color.WHITE)) {
				carton[i][0].setText(String.valueOf(menor));
			}
			
			if(carton[i][1].getBackground().equals(Color.WHITE)) {
				carton[i][1].setText(String.valueOf(medio));
			}
			
			if(carton[i][2].getBackground().equals(Color.WHITE)) {
				carton[i][2].setText(String.valueOf(mayor));
			}
		}
	}

	private static int aNumeroJ(JButton boton) {
		if(esNumero(boton.getText())) {
			return Integer.parseInt(boton.getText());
		}
		return 0;
	}

	private static boolean esNumero(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
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
					i = 5;
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
							PrintWriter ganado = new PrintWriter(new File("ganadoLinea"));
							ganado.println(nombre);
							ganado.close();
							JOptionPane.showMessageDialog(null, "Has cantado linea correctamente");
							i = 5;
						} else {
							JOptionPane.showMessageDialog(null, "Has cantado linea incorrectamente");
						}
					} catch (Exception e) {

					}
				}
			}
		}
	}
	
	public static void comprobarBingo() {
		boolean correcto = true, igual;
		try {
			Scanner ganado = new Scanner (new File("ganadoBingo"));
			ganado.close();
			JOptionPane.showMessageDialog(null, "Ya se ha cantado bingo");
		} catch (Exception e) {
			try {
				Scanner fichero = new Scanner (new File("fichero"));
				String numero;
				ArrayList<String> bingo = new ArrayList<String>();
				while (fichero.hasNext()) {
					numero = (String) fichero.next();
					bingo.add(numero);
				}

				for (int i = 0; i < FIL; i++) {
					for (int j = 0; j < COL; j++) {
						igual = false;
						if(carton[j][i].getBackground().equals(Color.WHITE)) {
							correcto = false;
							break;
						} else if(carton[j][i].getBackground().equals(Color.GREEN)) {
							for (int numBingo = 0; numBingo < bingo.size(); numBingo++) {
								if (Integer.parseInt(bingo.get(numBingo)) == aNumeroJ(carton[j][i])) {
									igual = true;
								}
							}

							if(!igual) {
								correcto = false;
								break;
							}
						}
					}
				}
				if(correcto) {
					PrintWriter ganado = new PrintWriter(new File("ganadoBingo"));
					ganado.println(nombre);
					ganado.close();
					JOptionPane.showMessageDialog(null, "Has cantado bingo, has ganado");
				} else {
					JOptionPane.showMessageDialog(null, "Has cantado bingo incorrectamente");
				}

			} catch (Exception e1) {

			}
		}
	}
	
	public static void cartonNuevo() {
		
	}
	
}
