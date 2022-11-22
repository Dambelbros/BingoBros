import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private JPanel contentPane;

	private static JLabel[] labels = new JLabel[90];
	private static int cantNumeros = 0, pos = 0;
	private static int[] numeros = new int[90];
	private static JLabel lblNumeroGrande;
	private static Timer reloj, automatico;
	private static boolean cantaLinea = false, cantaBingo = false;
	private static String ganadorLinea, ganadorBingo;
	private static JButton btnAuto, btnGenerarNumero, btnReinicio;
	private static Color verde = new Color(155, 250, 176);

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
	}

	public Ventana() {
		setTitle("Bingo");
		setIconImage(new ImageIcon(getClass().getResource("bingoVentana.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		/*Elimina los archivos creados*/
		try {
			File eliminarLinea = new File("ganadoLinea");
			eliminarLinea.delete();
		} catch (Exception e) {
		}

		try {
			File eliminarBingo = new File("ganadoBingo");
			eliminarBingo.delete();
		} catch (Exception e) {
		}
		
		try {
			File eliminarNumeros = new File("fichero");
			eliminarNumeros.delete();
		} catch (Exception e) {
		}
		
		/*Panel principal (lo que hay dentro de la ventana)*/
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNumeroGrande = new JPanel();
		layeredPane.add(panelNumeroGrande, BorderLayout.EAST);
		panelNumeroGrande.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblNumeroGrande = new JLabel("00");
		lblNumeroGrande.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroGrande.setFont(new Font("Tahoma", Font.PLAIN, 99));
		panelNumeroGrande.add(lblNumeroGrande);

		JPanel panelOpciones = new JPanel();
		layeredPane.add(panelOpciones, BorderLayout.SOUTH);

		/*Boton generar bolas*/
		btnGenerarNumero = new JButton("Sacar Bola");
		btnGenerarNumero.setBackground(new Color(255, 255, 255));
		btnGenerarNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelOpciones.add(btnGenerarNumero);

		/*Creamos el boton y le indicamos que cuando le demos se inicie y si volvemos a pulsar se pare*/
		btnAuto = new JButton("Modo automático");
		btnAuto.setBackground(new Color(255, 255, 255));
		btnAuto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelOpciones.add(btnAuto);

		/*Boton reiniciar*/
		btnReinicio = new JButton("Reiniciar");
		btnReinicio.setBackground(new Color(255, 255, 255));
		btnReinicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelOpciones.add(btnReinicio);

		JPanel panelNumeros = new JPanel();
		layeredPane.add(panelNumeros, BorderLayout.CENTER);
		panelNumeros.setLayout(new GridLayout(9, 10, 0, 0));

		/*Generador de botones numero*/
		for (int i = 0; i < 90; i++) {
			JLabel lbl = new JLabel(String.valueOf((i + 1)), SwingConstants.CENTER);
			lbl.setBackground(Color.WHITE);
			lbl.setOpaque(true);
			lbl.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
			lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			panelNumeros.add(lbl);
			
			labels[i] = lbl;
		}
		
		generarListener();
	}
	
	/*Generar Listeners*/
	private static void generarListener() {
		btnGenerarNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cantNumeros < 90) {
					automatico.stop();
					btnAuto.setBackground(Color.WHITE);
					generarNumeroJ();
					cantNumeros++;
				}
			}
		});
		
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambioAutomatico();
			}
		});
		
		btnReinicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				partidaNueva();
			}
		});
		
		reloj = new Timer(100, new ActionListener() {

			/*Creación de fichero y aparición de mensaje*/
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!cantaLinea) {
					try {
						Scanner archivoLinea = new Scanner (new File("ganadoLinea"));
						while (archivoLinea.hasNext()) {
							ganadorLinea = (String) archivoLinea.next();
						}
						archivoLinea.close();
						cantaLinea = true;
						automatico.stop();
						btnAuto.setBackground(Color.WHITE);
						JOptionPane.showMessageDialog(null, (ganadorLinea + " ha cantado linea correctamente"));
					} catch (Exception e2) {
					}
				}

				if (!cantaBingo) {
					try {
						Scanner archivoLinea = new Scanner (new File("ganadoBingo"));
						while (archivoLinea.hasNext()) {
							ganadorBingo = (String) archivoLinea.next();
						}
						archivoLinea.close();
						cantaBingo = true;
						automatico.stop();
						btnAuto.setBackground(Color.WHITE);
						JOptionPane.showMessageDialog(null, (ganadorBingo + " ha cantado bingo correctamente"));
					} catch (Exception e2) {
					}
				}
			}
		});
		reloj.start();
		
		/*Botón sacar números de forma automática*/
		automatico=new Timer(3000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cantNumeros < 90) {
					generarNumeroJ();
					cantNumeros++;
				} else {
					automatico.stop();
					btnAuto.setBackground(Color.WHITE);
				}
			}
		});
	}

	private static void cambioAutomatico() {
		if (cantNumeros < 90) {
			if(automatico.isRunning()) {
				automatico.stop();
				btnAuto.setBackground(Color.WHITE);
			} else {
				automatico.start();
				btnAuto.setBackground(verde);
			}
		}
	}
	
	/*Generador de números*/
	private static void generarNumeroJ() {
		boolean igual = false;
		do {
			igual = true;
			int num = (int)(Math.random()*90+1);
			if(labels[num - 1].getBackground() != verde) {
				labels[num - 1].setBackground(verde);
				igual = false;
				numeros[pos] = num;
				pos++;
				if (num > 9) {
					lblNumeroGrande.setText(String.valueOf(num));
				} else {
					lblNumeroGrande.setText("0" + String.valueOf(num));
				}
				try {
					PrintWriter fichero = new PrintWriter(new File("fichero"));
					for (int i = 0; i < pos; i++) {
						fichero.println(numeros[i]);
					}
					fichero.close();
				} catch (FileNotFoundException e) {
				}
			}
		} while (igual);
	}

	/*Partida nueva*/
	private static void partidaNueva() {
		automatico.stop();
		btnAuto.setBackground(Color.WHITE);
		cantaLinea = false;
		cantaBingo = false;

		for (int i = 0; i < labels.length; i++) {
			labels[i].setBackground(Color.WHITE);
		}

		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = 0;
		}

		lblNumeroGrande.setText("00");

		try {
			File eliminarLinea = new File("ganadoLinea");
			eliminarLinea.delete();
		} catch (Exception e) {
		}

		try {
			File eliminarBingo = new File("ganadoBingo");
			eliminarBingo.delete();
		} catch (Exception e) {
		}

		try {
			File eliminarNumeros = new File("fichero");
			eliminarNumeros.delete();
		} catch (Exception e) {
		}

		cantNumeros = 0;
		pos = 0;
	}
}
