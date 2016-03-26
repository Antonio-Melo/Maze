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
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import logic.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;

public class Window {

	private JFrame frame;
	private JTextField MazeDimension;
	private JTextField MazeNumberOfDragons;
	private JTextArea MazeArea;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnUp;
	private JButton btnDown;
	private JProgressBar GameState;
	private Game g;

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
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
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
		//Dimension Maze TextField
		MazeDimension = new JTextField();
		MazeDimension.setBounds(164, 11, 104, 20);
		frame.getContentPane().add(MazeDimension);
		MazeDimension.setColumns(10);
		
		//Number of Dragons Label
		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNumberOfDragons.setBounds(21, 39, 127, 24);
		frame.getContentPane().add(lblNumberOfDragons);
		//Number of Dragons TextField
		MazeNumberOfDragons = new JTextField();
		MazeNumberOfDragons.setBounds(164, 42, 104, 20);
		frame.getContentPane().add(MazeNumberOfDragons);
		MazeNumberOfDragons.setColumns(10);
		
		//Type of Dragons Label
		JLabel lblTypeOfDragons = new JLabel("Type of Dragons");
		lblTypeOfDragons.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTypeOfDragons.setBounds(21, 80, 127, 23);
		frame.getContentPane().add(lblTypeOfDragons);
		
		//Type of Dragon ComboBox
		JComboBox<String> comboBoxTypeDragons = new JComboBox<String>();
		comboBoxTypeDragons.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Static", "Moving", "Moving/Sleeping"}));
		comboBoxTypeDragons.setBounds(164, 77, 104, 20);
		frame.getContentPane().add(comboBoxTypeDragons);
		
		//Maze Area (print)
		MazeArea = new JTextArea();
		MazeArea.setEditable(false);
		MazeArea.setForeground(Color.BLACK);
		MazeArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		MazeArea.setBackground(Color.WHITE);
		MazeArea.setBounds(21, 128, 552, 394);
		frame.getContentPane().add(MazeArea);
		
		
		//Game State label
		JLabel lblState = new JLabel("State ");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblState.setBounds(353, 11, 48, 23);
		frame.getContentPane().add(lblState);
		//Game progress bar
		GameState = new JProgressBar();
		GameState.setStringPainted(true);
		GameState.setBackground(Color.RED);
		GameState.setForeground(Color.WHITE);
		GameState.setBounds(409, 11, 164, 20);
		frame.getContentPane().add(GameState);
		
		
		//Buttons
		
		//Left
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.setBounds(626, 247, 102, 44);
		frame.getContentPane().add(btnLeft);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('e');
			}});
		//Right
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(738, 247, 89, 44);
		frame.getContentPane().add(btnRight);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('d');
			}});
		//Up
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setBounds(679, 192, 109, 44);
		frame.getContentPane().add(btnUp);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('c');
			}});
		//Down
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(679, 302, 109, 44);
		frame.getContentPane().add(btnDown);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('b');
			}});
		//Authors
		JLabel lblAntnioMelo = new JLabel("Ant\u00F3nio Melo & Edgar Passos");
		lblAntnioMelo.setBounds(679, 536, 195, 14);
		frame.getContentPane().add(lblAntnioMelo);
		
		//Start button
		JButton btnGenerateMaze = new JButton("Start");
		btnGenerateMaze.setFont(new Font("Arial", Font.PLAIN, 14));
		btnGenerateMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Default numbers
				int size =10;
				int ndragons = 1;
				String dragonType;	
				//Size of Maze
				try{
					size = Integer.parseInt(MazeDimension.getText());
				}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(MazeDimension, "Invalid maze dimension!\nInsert a valid integer or 10 will be used as default.");
				}
				//Number of Dragons
				try{
					ndragons = Integer.parseInt(MazeNumberOfDragons.getText());
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(MazeDimension, "Invalid number of dragons!\nInsert a valid integer or 1 will be used as default.");
				}
				
				//type of Dragons
				dragonType = (String) comboBoxTypeDragons.getSelectedItem();
				switch (dragonType){
				case "":
					dragonType = "0";
					break;
				case "Static":
					dragonType = "0";
					break;
					
				case "Moving":
					dragonType = "1";
					break;
					
				case "Moving/Sleeping":
					dragonType = "2";
					break;
				}
				
				
				//Enable buttons
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				//Starting game
				MazeBuilder m = new MazeBuilder();
				g = new Game(dragonType.toCharArray()[0],m.buildMaze(size), ndragons);
				MazeArea.setText(g.myMaze.toString(g.dragons,g.mySword));
				GameState.setBackground(Color.GREEN);
				GameState.setValue(10);
			}
		});
		btnGenerateMaze.setBounds(700, 11, 127, 44);
		frame.getContentPane().add(btnGenerateMaze);
		
	}
	private void move(char d){
		g.play(d);
		MazeArea.setText(g.myMaze.toString(g.dragons,g.mySword));
		if(g.myHero.hasEscaped() || g.myHero.isDead()){
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			GameState.setValue(100);
		}
	}
}
