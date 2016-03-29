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

import javafx.scene.shape.Rectangle;
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
		
		//Exit Button
		JButton btnEndGame = new JButton("Exit");
		btnEndGame.setBounds(34, 476, 234, 44);
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		frame.getContentPane().setLayout(null);
		btnEndGame.setFont(new Font("Arial", Font.PLAIN, 14));
		frame.getContentPane().add(btnEndGame);
		
		//Dimension Maze Label
		JLabel lblDimension = new JLabel("Dimension");
		lblDimension.setBounds(21, 11, 116, 23);
		lblDimension.setFont(new Font("Arial", Font.PLAIN, 14));
		frame.getContentPane().add(lblDimension);
		//Dimension Maze TextField
		MazeDimension = new JTextField();
		MazeDimension.setBounds(164, 11, 104, 20);
		frame.getContentPane().add(MazeDimension);
		MazeDimension.setColumns(10);
		
		//Number of Dragons Label
		JLabel lblNumberOfDragons = new JLabel("Number of Dragons");
		lblNumberOfDragons.setBounds(21, 39, 127, 24);
		lblNumberOfDragons.setFont(new Font("Arial", Font.PLAIN, 14));
		frame.getContentPane().add(lblNumberOfDragons);
		//Number of Dragons TextField
		MazeNumberOfDragons = new JTextField();
		MazeNumberOfDragons.setBounds(164, 42, 104, 20);
		frame.getContentPane().add(MazeNumberOfDragons);
		MazeNumberOfDragons.setColumns(10);
		
		//Type of Dragons Label
		JLabel lblTypeOfDragons = new JLabel("Type of Dragons");
		lblTypeOfDragons.setBounds(21, 74, 127, 23);
		lblTypeOfDragons.setFont(new Font("Arial", Font.PLAIN, 14));
		frame.getContentPane().add(lblTypeOfDragons);
		
		//Type of Dragon ComboBox
		JComboBox<String> comboBoxTypeDragons = new JComboBox<String>();
		comboBoxTypeDragons.setBounds(164, 73, 104, 20);
		comboBoxTypeDragons.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Static", "Moving", "Moving/Sleeping"}));
		frame.getContentPane().add(comboBoxTypeDragons);
		
		//Maze Area (print)
		MazeArea = new JTextArea();
		MazeArea.setBounds(387, 43, 451, 451);
		MazeArea.setEditable(false);
		MazeArea.setForeground(Color.BLACK);
		MazeArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		MazeArea.setBackground(Color.WHITE);
		frame.getContentPane().add(MazeArea);
		
		
		//Game State label
		JLabel lblState = new JLabel("State ");
		lblState.setBounds(387, 10, 48, 23);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblState);
		//Game progress bar
		GameState = new JProgressBar();
		GameState.setBounds(445, 11, 164, 20);
		GameState.setStringPainted(true);
		GameState.setBackground(Color.RED);
		GameState.setForeground(Color.WHITE);
		frame.getContentPane().add(GameState);
		
		
		//Buttons
		
		//Left
		btnLeft = new JButton("Left");
		btnLeft.setBounds(39, 320, 109, 44);
		btnLeft.setEnabled(false);
		frame.getContentPane().add(btnLeft);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('e');
			}});
		//Right
		btnRight = new JButton("Right");
		btnRight.setBounds(164, 320, 109, 44);
		btnRight.setEnabled(false);
		frame.getContentPane().add(btnRight);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('d');
			}});
		//Up
		btnUp = new JButton("Up");
		btnUp.setBounds(107, 265, 109, 44);
		btnUp.setEnabled(false);
		frame.getContentPane().add(btnUp);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('c');
			}});
		//Down
		btnDown = new JButton("Down");
		btnDown.setBounds(107, 375, 109, 44);
		btnDown.setEnabled(false);
		frame.getContentPane().add(btnDown);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('b');
			}});
		//Authors
		JLabel lblAntnioMelo = new JLabel("Ant\u00F3nio Melo & Edgar Passos");
		lblAntnioMelo.setBounds(92, 531, 195, 14);
		frame.getContentPane().add(lblAntnioMelo);
		
		//Start button
		JButton btnGenerateMaze = new JButton("Start");
		btnGenerateMaze.setBounds(39, 156, 234, 44);
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
				
				if(size > 43){
					JOptionPane.showMessageDialog(MazeDimension, "Invalid maze dimension!\nThe maximum size is 43. Size 43 will be used.");
					size = 43;
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
				
				
				MazeArea.setBounds(MazeArea.getX(),MazeArea.getY(),16*size, 15*size);
				
				MazeBuilder m = new MazeBuilder();
				g = new Game(dragonType.toCharArray()[0],m.buildMaze(size), ndragons);
				MazeArea.setText(g.getMyMaze().toString(g.getDragons(),g.getMySword()));
				GameState.setBackground(Color.GREEN);
				GameState.setValue(10);
			}
		});
		frame.getContentPane().add(btnGenerateMaze);
		
	}
	private void move(char d){
		g.play(d);
		MazeArea.setText(g.getMyMaze().toString(g.getDragons(),g.getMySword()));
		if(g.getMyHero().hasEscaped() || g.getMyHero().isDead()){
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			GameState.setValue(100);
		}
	}
}
