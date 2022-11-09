import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private JPanel contentPane;

	private static JButton[] labels = new JButton[90];
	private static int cantNumeros = 0, pos = 0;
	private static int[] numeros = new int[90];
	private static JLabel lblNumeroGrande;
	

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		try {
			File eliminar_ganado = new File("ganadoLinea");
			eliminar_ganado.delete();
		} catch (Exception e) {
			
		}
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNumeroGrande = new JPanel();
		layeredPane.add(panelNumeroGrande, BorderLayout.EAST);
		panelNumeroGrande.setLayout(new BorderLayout(0, 0));
		
		lblNumeroGrande = new JLabel("XX");
		lblNumeroGrande.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panelNumeroGrande.add(lblNumeroGrande);
		
		JPanel panelOpciones = new JPanel();
		layeredPane.add(panelOpciones, BorderLayout.SOUTH);
		
		JButton btnGenerarNumero = new JButton("Sacar Bola");
		btnGenerarNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cantNumeros < 90) {
					generarNumeroJ();
					cantNumeros++;
				}
			}
		});
		panelOpciones.add(btnGenerarNumero);
		
		JPanel panelNumeros = new JPanel();
		layeredPane.add(panelNumeros, BorderLayout.CENTER);
		panelNumeros.setLayout(new GridLayout(9, 10, 0, 0));
		
		for (int i = 0; i < 90; i++) {
			JButton btn = new JButton(String.valueOf((i + 1)));
			panelNumeros.add(btn);
			labels[i] = btn;
		}
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
					lblNumeroGrande.setText("_" + String.valueOf(num));
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
}
