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

import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private JPanel contentPane;
	private static int[] numeros = new int[90];
	private static JLabel[] labels = new JLabel[90];
	private static int pos = 0;

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
		setBounds(100, 100, 605, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNumeroGrande = new JPanel();
		layeredPane.add(panelNumeroGrande, BorderLayout.EAST);
		
		JPanel panelOpciones = new JPanel();
		layeredPane.add(panelOpciones, BorderLayout.SOUTH);
		
		JButton btnGenerarNumero = new JButton("Sacar Bola");
		btnGenerarNumero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarNumeroJ();
			}
		});
		panelOpciones.add(btnGenerarNumero);
		
		JPanel panelNumeros = new JPanel();
		layeredPane.add(panelNumeros, BorderLayout.CENTER);
		panelNumeros.setLayout(new GridLayout(9, 10, 0, 0));
		
		for (int i = 0; i < 90; i++) {
			JLabel lbl = new JLabel(String.valueOf((i + 1)));
			panelNumeros.add(lbl);
			labels[i] = lbl;
		}
	}
	
	private static void generarNumeroJ() {
		boolean igual = false;
		do {
			igual = true;
			int num = (int)(Math.random()*90+1);
			System.out.println(num);
			if(labels[num - 1].getBackground() != Color.GREEN) {
				System.out.println("Entra");
				labels[num - 1].setBackground(Color.GREEN);
				igual = false;
					
			}

		} while (igual);
	}
}
