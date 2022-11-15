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

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private JPanel contentPane;

	private static JButton[] labels = new JButton[90];
	private static int cantNumeros = 0, pos = 0;
	private static int[] numeros = new int[90];
	private static JLabel lblNumeroGrande;
	private static Timer reloj, automatico;
	private static boolean cantaLinea = false, cantaBingo = false;
	private static String ganadorLinea, ganadorBingo;


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
		setIconImage(Toolkit.getDefaultToolkit().getImage("bingoVentana.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		/*Elimina los archivos creados*/
		try {
			File eliminar_linea = new File("ganadoLinea");
			eliminar_linea.delete();
		} catch (Exception e) {
		}

		try {
			File eliminar_bingo = new File("ganadoBingo");
			eliminar_bingo.delete();
		} catch (Exception e) {
		}

		try {
			File eliminar_numeros = new File("fichero");
			eliminar_numeros.delete();
		} catch (Exception e) {
		}

		/*Panel principal*/
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNumeroGrande = new JPanel();
		layeredPane.add(panelNumeroGrande, BorderLayout.EAST);
		panelNumeroGrande.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblNumeroGrande = new JLabel("XX");
		lblNumeroGrande.setFont(new Font("Tahoma", Font.PLAIN, 99));
		panelNumeroGrande.add(lblNumeroGrande);

		JPanel panelOpciones = new JPanel();
		layeredPane.add(panelOpciones, BorderLayout.SOUTH);

		/*Boton generar bolas*/
		JButton btnGenerarNumero = new JButton("Sacar Bola");
		btnGenerarNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cantNumeros < 90) {
					generarNumeroJ();
					cantNumeros++;
				}
			}
		});

		//Generamos un Timer para automatizar la salida de las bolas.
		automatico=new Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cantNumeros < 90) {
					generarNumeroJ();
					cantNumeros++;
				} else {
					automatico.stop();				}
			}
		});

		//Creamos el boton y le indicamos que cuando le demos se inicie y si volvemos a pulsar se pare.
		JButton btnAuto = new JButton("Modo automático");
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(automatico.isRunning()) {
					automatico.stop();
				} else {
					automatico.start();
				}
			}
		});
		panelOpciones.add(btnAuto);
		panelOpciones.add(btnGenerarNumero);

		/*Boton reiniciar*/
		JButton btnReinicio = new JButton("Reiniciar");
		btnReinicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				partidaNueva();
			}
		});
		panelOpciones.add(btnReinicio);

		JPanel panelNumeros = new JPanel();
		layeredPane.add(panelNumeros, BorderLayout.CENTER);
		panelNumeros.setLayout(new GridLayout(9, 10, 0, 0));

		/*Generador de botones numero*/
		for (int i = 0; i < 90; i++) {
			JButton btn = new JButton(String.valueOf((i + 1)));
			btn.setEnabled(false);
			btn.setBackground(Color.WHITE);
			panelNumeros.add(btn);
			labels[i] = btn;
		}

		reloj = new Timer(500, new ActionListener() {

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
						JOptionPane.showMessageDialog(null, (ganadorLinea + " a cantado linea correctamente"));
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
						JOptionPane.showMessageDialog(null, (ganadorBingo + " a cantado bingo correctamente"));
					} catch (Exception e2) {
					}
				}
			}
		});

		reloj.start();
	}

	private static void generarNumeroJ() {
		boolean igual = false;
		do {
			igual = true;
			int num = (int)(Math.random()*90+1);
			if(labels[num - 1].getBackground() != Color.GREEN) {
				labels[num - 1].setBackground(Color.GREEN);
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

	private static void partidaNueva() {
		automatico.stop();

		for (int i = 0; i < labels.length; i++) {
			labels[i].setBackground(Color.WHITE);
		}

		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = 0;
		}

		lblNumeroGrande.setText("XX");

		try {
			File eliminar_linea = new File("ganadoLinea");
			eliminar_linea.delete();
		} catch (Exception e) {
		}
		
		try {
			File eliminar_numeros = new File("fichero");
			eliminar_numeros.delete();
		} catch (Exception e) {
		}

		try {
			File eliminar_bingo = new File("ganadoBingo");
			eliminar_bingo.delete();
		} catch (Exception e) {
		}

		cantNumeros = 0;
		pos = 0;
	}
}
