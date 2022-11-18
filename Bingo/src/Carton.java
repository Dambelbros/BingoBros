import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
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
	private static JPanel contentPane, panelCasillas, panelBotones, panelArriba;
	private JLayeredPane layeredPane;
	private static JButton[][] carton = new JButton[COL][FIL];
	private static JButton btnLinea, btnBingo, btnNuevocarton;
	private static String nombre, ganadorLinea, ganadorBingo;
	private static boolean cantadoLinea = false, cantadoBingo = false, persLinea = false, persBingo = false;
	private static Timer reloj;
	private static Color fondo= new Color(245,245,245), verde = new Color(155, 250, 176), negro = new Color(44, 44,44);

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
		setIconImage(new ImageIcon(getClass().getResource("bolaCarton.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 314);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(fondo);
		contentPane.setForeground(fondo);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		layeredPane = new JLayeredPane();
		layeredPane.setForeground(fondo);
		layeredPane.setBackground(fondo);
		layeredPane.setOpaque(true);
		contentPane.add(layeredPane, BorderLayout.CENTER);

		panelCasillas = new JPanel();
		panelCasillas.setBackground(fondo);
		panelCasillas.setBounds(0, 43, 541, 160);
		layeredPane.add(panelCasillas);
		panelCasillas.setLayout(new GridLayout(3, 9, 0, 0));
		
		/*Genera los botones de las casillas*/
		for (int i = 0; i < FIL; i++) {
			for (int j = 0; j < COL; j++) {
				JButton btnCasilla = new JButton("");
				btnCasilla.setBackground(Color.WHITE);
				btnCasilla.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
				panelCasillas.add(btnCasilla);
				carton[j][i] = btnCasilla;
			}
		}

		/*Paneles y botones*/
		panelBotones = new JPanel();
		panelBotones.setBackground(fondo);
		panelBotones.setOpaque(true);
		panelBotones.setBounds(0, 214, 541, 51);
		layeredPane.add(panelBotones);
		panelBotones.setLayout(null);

		btnLinea = new JButton("LINEA");
		btnLinea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLinea.setBackground(new Color(255, 255, 255));
		btnLinea.setBounds(0, 0, 143, 40);
		panelBotones.add(btnLinea);

		btnBingo = new JButton("BINGO");
		btnBingo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBingo.setBackground(new Color(255, 255, 255));
		btnBingo.setBounds(199, 0, 143, 40);
		panelBotones.add(btnBingo);
		
		btnNuevocarton = new JButton("NUEVO CARTÓN");
		btnNuevocarton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevocarton.setBackground(new Color(255, 255, 255));
		btnNuevocarton.setBounds(398, 0, 143, 40);
		panelBotones.add(btnNuevocarton);

		panelArriba = new JPanel();
		panelArriba.setBackground(fondo);
		panelArriba.setBounds(0, 0, 541, 43);
		panelArriba.setOpaque(true);
		layeredPane.add(panelArriba);
		panelArriba.setLayout(null);

		/*Título*/
		JLabel lblNewLabel = new JLabel("BINGOBROS");
		lblNewLabel.setForeground(new Color(44, 44, 44));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBounds(145, 0, 243, 43);
		panelArriba.add(lblNewLabel);
		
		reloj = new Timer(100, new ActionListener() {

			/*Creación de fichero y aparición de mensaje*/
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!cantadoLinea && !persLinea) {
					try {
						Scanner archivoLinea = new Scanner (new File("ganadoLinea"));
						while (archivoLinea.hasNext()) {
							ganadorLinea = (String) archivoLinea.next();
						}
						archivoLinea.close();
						cantadoLinea = true;
						JOptionPane.showMessageDialog(null, (ganadorLinea + " a cantado linea correctamente"));
					} catch (Exception e2) {
					}
				}

				if (!cantadoBingo && !persBingo) {
					try {
						Scanner archivoLinea = new Scanner (new File("ganadoBingo"));
						while (archivoLinea.hasNext()) {
							ganadorBingo = (String) archivoLinea.next();
						}
						archivoLinea.close();
						cantadoBingo = true;
						deshabilitarTodo();
						JOptionPane.showMessageDialog(null, (ganadorBingo + " a cantado bingo correctamente"));
					} catch (Exception e2) {
					}
				}
			}
		});

		reloj.start();

		generarEspaciosVaciosJ();
		generarNumerosColumnasJ();
		generarListener();
		
		/*Ventana de entrada para introducir el nombre*/
		do {
			nombre = JOptionPane.showInputDialog("Nombre");
			if(nombre==null) {
				System.exit(1);
			}
		} while (nombre==null||nombre.equals(""));
	}

	/*Deshabilitar todo*/
	private static void deshabilitarTodo() {
		for (int i = 0; i < FIL; i++) {
			for (int j = 0; j < COL; j++) {
				carton[j][i].setEnabled(false);
			}
		}
		
		btnBingo.setEnabled(false);
		btnLinea.setEnabled(false);
	}
	
	/*Generar Listeners*/
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
		
		btnNuevocarton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartonNuevo();
			}
		});
	}

	/*Generador de espacios vacios*/
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
					if (num == vacio[i] || (fila == 2 && (carton[num][0].getBackground().equals(negro) && carton[num][1].getBackground().equals(negro)))) {
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
				carton[vacio[i]][fila].setBackground(negro);
				carton[vacio[i]][fila].setEnabled(false);
			}
		}
	}

	/*Generador de numeros*/
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

	/*Consigue texto en los botones y los pasa a números controlando los posibles fallos*/
	private static int aNumeroJ(JButton boton) {
		if(esNumero(boton.getText())) {
			return Integer.parseInt(boton.getText());
		}
		return 0;
	}

	/*Comprobación de números en botones*/
	private static boolean esNumero(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}
	
	/*Clickar un número y que cambie de color*/
	private static void cambioColor(JButton boton) {
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (boton.getBackground().equals(Color.WHITE)) {
					boton.setBackground(verde);
				} else {
					boton.setBackground(Color.WHITE);
				}
			}
		});
	}

	/*Comprobador de línea*/
	public static void comprobarLinea() {
		boolean correcto, acierto = false;
		try {
			Scanner ganado = new Scanner (new File("ganadoLinea"));
			ganado.close();
			JOptionPane.showMessageDialog(null, "Ya se ha cantado linea");
		} catch (Exception e) {
			try {
				Scanner fichero = new Scanner (new File("fichero"));
				String numero;
				int numUno, comprobados = 0;
				ArrayList<String> correctos = new ArrayList<String>();
				while (fichero.hasNext()) {
					numero = (String) fichero.next();
					correctos.add(numero);
				}
				fichero.close();
				
				for (int i = 0; i < FIL; i++) {
					correcto = true;
					for (int j = 0; j < COL; j++) {
						if(carton[j][i].getBackground().equals(Color.WHITE)){
							correcto = false;
							j = COL;
						}
					}

					if (correcto) {
						for (int j = 0; j < COL; j++) {
							if(carton[j][i].getBackground().equals(verde)) {
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
							persLinea = true;
							JOptionPane.showMessageDialog(null, "Has cantado linea correctamente");
							acierto = true;
							i = FIL;
						} else {
						}
					}
				}
				if (!acierto) {
					JOptionPane.showMessageDialog(null, "Linea Fallida");
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Aun no ha empezado la partida");
			}
		}
	}

	/*comprobador de bingo*/
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
				fichero.close();

				for (int i = 0; i < FIL; i++) {
					for (int j = 0; j < COL; j++) {
						igual = false;
						if(carton[j][i].getBackground().equals(Color.WHITE)) {
							correcto = false;
							break;
						} else if(carton[j][i].getBackground().equals(verde)) {
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
					persBingo = true;
					deshabilitarTodo();
					JOptionPane.showMessageDialog(null, "Has cantado bingo, has ganado");
				} else {
					JOptionPane.showMessageDialog(null, "Bingo Fallido");
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Aun no ha empezado la partida");
			}
		}
	}
	
	/*Generar cartón nuevo*/
	public static void cartonNuevo() {
		try {
			Scanner partidaIniciada = new Scanner (new File("fichero"));
			partidaIniciada.close();
			JOptionPane.showMessageDialog(null, "No puedes cambiar el carton cuando ha empezado la partida");
		} catch (Exception e) {
			for (int i = 0; i < FIL; i++) {
				for (int j = 0; j < COL; j++) {
					carton[j][i].setText("");
					carton[j][i].setBackground(Color.WHITE);
					carton[j][i].setEnabled(true);
				}
			}
			
			cantadoLinea = false;
			cantadoBingo = false;
			persLinea = false;
			persBingo = false;
			
			btnLinea.setEnabled(true);
			btnBingo.setEnabled(true);
			
			
			generarEspaciosVaciosJ();
			generarNumerosColumnasJ();
		}
	}
}
