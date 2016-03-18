package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnGenerateMaze = new JButton("Start");
		btnGenerateMaze.setFont(new Font("Arial", Font.PLAIN, 14));
		btnGenerateMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGenerateMaze.setBounds(621, 11, 127, 44);
		frame.getContentPane().add(btnGenerateMaze);
		
		JButton btnEndGame = new JButton("Exit");
		btnEndGame.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEndGame.setBounds(621, 77, 127, 44);
		frame.getContentPane().add(btnEndGame);
		
		JLabel lblDimension = new JLabel("Dimension");
		lblDimension.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDimension.setBounds(21, 11, 116, 23);
		frame.getContentPane().add(lblDimension);
		
		textField = new JTextField();
		textField.setBounds(164, 11, 104, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(164, 42, 104, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTypeOfDragons = new JLabel("Type of Dragons");
		lblTypeOfDragons.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTypeOfDragons.setBounds(21, 80, 127, 23);
		frame.getContentPane().add(lblTypeOfDragons);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Static", "Moving", "Moving/Sleeping"}));
		comboBox.setBounds(164, 77, 104, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNumberOfDragons.setBounds(21, 39, 127, 24);
		frame.getContentPane().add(lblNumberOfDragons);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setBounds(37, 131, 568, 374);
		frame.getContentPane().add(textArea);
	}
}
