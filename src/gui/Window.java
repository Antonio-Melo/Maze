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

import logic.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;

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
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Exit Button
		JButton btnEndGame = new JButton("Exit");
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnEndGame.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEndGame.setBounds(700, 80, 127, 44);
		frame.getContentPane().add(btnEndGame);
		
		//Dimension Maze Label
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
		
		//Type of Dragons Label
		JLabel lblTypeOfDragons = new JLabel("Type of Dragons");
		lblTypeOfDragons.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTypeOfDragons.setBounds(21, 80, 127, 23);
		frame.getContentPane().add(lblTypeOfDragons);
		
		//Type of Dragon ComboBox
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Static", "Moving", "Moving/Sleeping"}));
		comboBox.setBounds(164, 77, 104, 20);
		frame.getContentPane().add(comboBox);
		
		//Type of Dragons Label
		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNumberOfDragons.setBounds(21, 39, 127, 24);
		frame.getContentPane().add(lblNumberOfDragons);
		
		JTextArea MazeArea = new JTextArea();
		MazeArea.setEditable(false);
		MazeArea.setForeground(Color.BLACK);
		MazeArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		MazeArea.setBackground(Color.WHITE);
		MazeArea.setBounds(21, 128, 552, 389);
		frame.getContentPane().add(MazeArea);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.setBounds(626, 247, 102, 44);
		frame.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(738, 247, 89, 44);
		frame.getContentPane().add(btnRight);
		
		JButton btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setBounds(679, 192, 109, 44);
		frame.getContentPane().add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(679, 302, 109, 44);
		frame.getContentPane().add(btnDown);
		
		JLabel lblAntnioMelo = new JLabel("Ant\u00F3nio Melo & Edgar Passos");
		lblAntnioMelo.setBounds(679, 536, 195, 14);
		frame.getContentPane().add(lblAntnioMelo);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(Color.RED);
		progressBar.setForeground(Color.WHITE);
		progressBar.setBounds(409, 11, 164, 20);
		frame.getContentPane().add(progressBar);
		
		JLabel lblState = new JLabel("State ");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblState.setBounds(353, 11, 48, 23);
		frame.getContentPane().add(lblState);
		
		
		JButton btnGenerateMaze = new JButton("Start");
		btnGenerateMaze.setFont(new Font("Arial", Font.PLAIN, 14));
		btnGenerateMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				
				MazeBuilder m = new MazeBuilder();
				Game g = new Game('0',m.buildMaze(20), 4);
				MazeArea.setText(g.myMaze.toString(g.dragons,g.mySword));
			}
		});
		btnGenerateMaze.setBounds(700, 11, 127, 44);
		frame.getContentPane().add(btnGenerateMaze);
	}
}
