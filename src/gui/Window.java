package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import javafx.scene.shape.Rectangle;
import logic.*;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.imageio.ImageIO;
import javax.net.ssl.SSLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Window {

	private JFrame frmStarWars;
	private JTextField MazeDimension;
	private JTextField MazeNumberOfDragons;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnUp;
	private JButton btnDown;
	private JProgressBar GameState;
	private JTextArea Instructions;
	private Game g;
	private int perc;
	public static Window window;
	public JPanel gpanel;
	public CustomMazeDialog customDialog;
	private boolean customMaze = false;
	private int size = 10;
	private char[][] gameGrid;
	private BufferedImage windowicon;
	private BufferedImage settings;
	private char[][] customGrid;
	private char[][] activeGrid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Window();
					window.frmStarWars.setVisible(true);
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
		try {
			windowicon = ImageIO.read(new File("res\\windowicon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			settings = ImageIO.read(new File("res\\settings.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Border b = new LineBorder(Color.DARK_GRAY,2);

		frmStarWars = new JFrame();
		frmStarWars.setTitle("Star Wars - LPOO 2016");
		frmStarWars.setIconImage(windowicon);
		frmStarWars.setResizable(false);
		frmStarWars.getContentPane().setBackground(Color.DARK_GRAY);
		frmStarWars.setBackground(Color.WHITE);
		frmStarWars.setBounds(0, 0, 1200, 700);
		frmStarWars.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Exit Button
		JButton btnEndGame = new JButton("Exit");
		btnEndGame.setForeground(new Color(255, 255, 255));
		btnEndGame.setBackground(Color.RED);
		btnEndGame.setBounds(39, 526, 234, 44);
		btnEndGame.setBorder(b);
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		frmStarWars.getContentPane().setLayout(null);
		btnEndGame.setFont(new Font("Arial", Font.PLAIN, 14));
		frmStarWars.getContentPane().add(btnEndGame);

		// Custom Maze Button
		JButton btnNewButton = new JButton("Create Custom Maze");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.setBorder(b);
		Window parent = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customDialog = new CustomMazeDialog();
				customDialog.setIconImage(settings);
				customDialog.setParent(parent);
				customDialog.setVisible(true);
			}
		});
		btnNewButton.setBounds(21, 152, 252, 23);
		frmStarWars.getContentPane().add(btnNewButton);

		// CustomMaze Checkbox
		JCheckBox chckbxUseCustomMaze = new JCheckBox("Use Custom Maze");
		chckbxUseCustomMaze.setFont(new Font("Arial", Font.PLAIN, 17));
		chckbxUseCustomMaze.setForeground(Color.WHITE);
		chckbxUseCustomMaze.setBackground(Color.DARK_GRAY);
		chckbxUseCustomMaze.setBounds(21, 197, 247, 23);
		frmStarWars.getContentPane().add(chckbxUseCustomMaze);
		chckbxUseCustomMaze.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (chckbxUseCustomMaze.isSelected()) {
					customMaze = true;
					MazeDimension.setEditable(false);
					MazeNumberOfDragons.setEditable(false);
				}

				else {
					customMaze = false;
					MazeDimension.setEditable(true);
					MazeNumberOfDragons.setEditable(true);
				}

			}
		});

		// Dimension Maze Label
		JLabel lblDimension = new JLabel("Dimension");
		lblDimension.setForeground(Color.WHITE);
		lblDimension.setBounds(10, 8, 116, 23);
		lblDimension.setFont(new Font("Arial", Font.PLAIN, 17));
		frmStarWars.getContentPane().add(lblDimension);
		// Dimension Maze TextField
		MazeDimension = new JTextField();
		MazeDimension.setBounds(164, 11, 104, 20);
		MazeDimension.setBorder(b);
		frmStarWars.getContentPane().add(MazeDimension);
		MazeDimension.setColumns(10);

		// Number of Dragons Label
		JLabel lblNumberOfDragons = new JLabel("Number of Enemies");
		lblNumberOfDragons.setForeground(Color.WHITE);
		lblNumberOfDragons.setBounds(10, 42, 164, 24);
		lblNumberOfDragons.setFont(new Font("Arial", Font.PLAIN, 17));
		frmStarWars.getContentPane().add(lblNumberOfDragons);
		// Number of Dragons TextField
		MazeNumberOfDragons = new JTextField();
		MazeNumberOfDragons.setBounds(164, 42, 104, 20);
		MazeNumberOfDragons.setBorder(b);
		frmStarWars.getContentPane().add(MazeNumberOfDragons);
		MazeNumberOfDragons.setColumns(10);

		// Type of Dragons Label
		JLabel lblTypeOfDragons = new JLabel("Type of Enemies");
		lblTypeOfDragons.setForeground(Color.WHITE);
		lblTypeOfDragons.setBounds(10, 71, 127, 23);
		lblTypeOfDragons.setFont(new Font("Arial", Font.PLAIN, 17));
		frmStarWars.getContentPane().add(lblTypeOfDragons);

		// Type of Dragon ComboBox
		JComboBox<String> comboBoxTypeDragons = new JComboBox<String>();
		comboBoxTypeDragons.setBounds(164, 73, 104, 23);
		comboBoxTypeDragons
				.setModel(new DefaultComboBoxModel(new String[] {"Static", "Moving", "Moving/Sleeping"}));
		comboBoxTypeDragons.setBorder(b);
		frmStarWars.getContentPane().add(comboBoxTypeDragons);
		
		//Type of Hero
		JComboBox<String> chooseHero = new JComboBox<String>();
		chooseHero.setModel(new DefaultComboBoxModel(new String[] {"Luke Skywalker", "Obi-Wan Kenobi", "Master Yoda"}));
		chooseHero.setBounds(164, 104, 104, 23);
		chooseHero.setBorder(b);
		frmStarWars.getContentPane().add(chooseHero);
		
		// Game State label
		JLabel lblState = new JLabel("State ");
		lblState.setForeground(Color.WHITE);
		lblState.setBounds(387, 10, 48, 23);
		lblState.setFont(new Font("Arial", Font.PLAIN, 17));
		frmStarWars.getContentPane().add(lblState);

		// Game progress bar
		GameState = new JProgressBar();
		GameState.setBounds(445, 11, 164, 20);
		GameState.setStringPainted(true);
		GameState.setBackground(new Color(178, 34, 34));
		GameState.setForeground(Color.WHITE);
		GameState.setBorder(b);
		frmStarWars.getContentPane().add(GameState);

		// Buttons

		// Left
		btnLeft = new JButton("Left");
		btnLeft.setForeground(new Color(255, 255, 255));
		btnLeft.setBackground(new Color(0, 0, 255));
		btnLeft.setBounds(45, 389, 109, 44);
		btnLeft.setEnabled(false);
		btnLeft.setBorder(b);
		frmStarWars.getContentPane().add(btnLeft);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('l');
				gpanel.requestFocus();
				setInstructionText("You moved to the Left!");
			}
		});
		// Right
		btnRight = new JButton("Right");
		btnRight.setForeground(new Color(255, 255, 255));
		btnRight.setBackground(new Color(0, 0, 255));
		btnRight.setBounds(164, 389, 109, 44);
		btnRight.setEnabled(false);
		btnRight.setBorder(b);
		frmStarWars.getContentPane().add(btnRight);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('r');
				gpanel.requestFocus();
				setInstructionText("You moved to the Right!");
			}
		});
		// Up
		btnUp = new JButton("Up");
		btnUp.setForeground(new Color(255, 255, 255));
		btnUp.setBackground(new Color(0, 0, 255));
		btnUp.setBounds(107, 334, 109, 44);
		btnUp.setEnabled(false);
		btnUp.setBorder(b);
		frmStarWars.getContentPane().add(btnUp);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('u');
				gpanel.requestFocus();
				setInstructionText("You moved Up!");
			}
		});
		// Down
		btnDown = new JButton("Down");
		btnDown.setForeground(new Color(255, 255, 255));
		btnDown.setBackground(new Color(0, 0, 255));
		btnDown.setBounds(107, 444, 109, 44);
		btnDown.setEnabled(false);
		btnDown.setBorder(b);
		frmStarWars.getContentPane().add(btnDown);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move('d');
				gpanel.requestFocus();
				setInstructionText("You moved Down!");
			}

		});

		// Instructions area
		Instructions = new JTextArea();
		Instructions.setForeground(Color.WHITE);
		Instructions.setFont(new Font("Arial", Font.PLAIN, 17));
		Instructions.setBackground(Color.DARK_GRAY);
		Instructions.setEditable(false);
		Instructions.setBounds(632, 11, 298, 23);
		Instructions.setText("Fill the boxes and click Start to play !!");
		frmStarWars.getContentPane().add(Instructions);

		// Image shown when the player loses
		JLabel loseImage = new JLabel("");
		loseImage.setIcon(new ImageIcon("res\\LoseImage.png"));
		loseImage.setBounds(327, 250, 860, 300);
		frmStarWars.getContentPane().add(loseImage);
		loseImage.setVisible(false);

		// Image shown when the player wins
		JLabel winImage = new JLabel("");
		winImage.setIcon(new ImageIcon("res\\WinImage.jpeg"));
		winImage.setBounds(327, 250, 860, 300);
		frmStarWars.getContentPane().add(winImage);
		winImage.setVisible(false);

		// Authors
		JLabel lblAntnioMelo = new JLabel("Ant\u00F3nio Melo & Edgar Passos");
		lblAntnioMelo.setForeground(Color.WHITE);
		lblAntnioMelo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAntnioMelo.setBounds(49, 581, 236, 23);
		frmStarWars.getContentPane().add(lblAntnioMelo);

		// Start button
		JButton btnGenerateMaze = new JButton("Start");
		btnGenerateMaze.setBackground(new Color(0, 128, 0));
		btnGenerateMaze.setForeground(Color.WHITE);
		btnGenerateMaze.setBounds(39, 250, 234, 44);
		btnGenerateMaze.setFont(new Font("Arial", Font.PLAIN, 14));
		btnGenerateMaze.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				winImage.setVisible(false);
				loseImage.setVisible(false);

				// Default numbers
				int ndragons = 1;
				String dragonType;
				String heroType;
				String hero = "";

				// Size of Maze
				if (MazeDimension.isEditable())
					try {
						size = Integer.parseInt(MazeDimension.getText());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(MazeDimension,
								"Invalid maze dimension!\nInsert a valid integer or 10 will be used as default.");
					}

				if (size > 50 && !customMaze) {
					JOptionPane.showMessageDialog(MazeDimension,
							"Invalid maze dimension!\nThe maximum size is 50. Size 50 will be used.");
					size = 50;
				}

				else if (size < 4 && !customMaze) {
					JOptionPane.showMessageDialog(MazeDimension,
							"Invalid maze dimension!\nThe minimum size is 4. Size 4 will be used, with 1 dragon");
					size = 4;
					ndragons = 1;
				}
				// Number of Dragons
				if (MazeNumberOfDragons.isEditable()) {
					try {
						ndragons = Integer.parseInt(MazeNumberOfDragons.getText());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(MazeDimension,
								"Invalid number of enemies!\nInsert a valid integer or 1 will be used as default.");
					}

					if (ndragons <= 0 || ndragons > size) {
						JOptionPane.showMessageDialog(MazeDimension,
								"Invalid number of enemies!\nInsert a valid integer or 1 will be used as default.");
						ndragons = 1;
					}
				}

				// type of Dragons
				dragonType = (String) comboBoxTypeDragons.getSelectedItem();
				switch (dragonType) {
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
				
				//type of hero
				heroType = (String) chooseHero.getSelectedItem();
				switch(heroType){
				case "Luke Skywalker":
					hero = "luke";
					break;
				case "Obi-Wan Kenobi":
					hero = "obiwan";
					break;
				case "Master Yoda":
					hero = "yoda";
					break;
				}

				

				// Enable buttons
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);

				// Starting game

				if (gpanel != null)
					frmStarWars.remove(gpanel);

				frmStarWars.repaint();

				if (!customMaze) {
					MazeBuilder m = new MazeBuilder();
					gameGrid = m.buildMaze(size);
					g = new Game(dragonType.toCharArray()[0], gameGrid, ndragons);
				}

				else {

					
					activeGrid = new char[customGrid.length][];
					for(int i = 0; i < customGrid.length; i++){
						activeGrid[i] = customGrid[i].clone();
					}

					
					g = new Game(dragonType.toCharArray()[0], activeGrid);

				}

				GameState.setBackground(Color.GREEN);
				perc = 100 / (g.getDragons().size());

				GameState.setValue(100 - (perc * g.getDragons().size()));
				setInstructionText("Move to start playing!");

				gpanel = new MazeGraphicsPanel(g,hero);
				gpanel.setBackground(Color.DARK_GRAY);
				gpanel.setBounds(385, 40, 600, 600);
				frmStarWars.getContentPane().add(gpanel);

				// graf
				gpanel.repaint();
				// keys
				gpanel.requestFocus();
			}
		});
		btnGenerateMaze.setBorder(b);
		frmStarWars.getContentPane().add(btnGenerateMaze);

		// Github icon
		JButton github = new JButton();
		github.setBackground(Color.DARK_GRAY);
		github.setIcon(new ImageIcon("res\\githubicon.png"));
		github.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				URL u = null;
				try {
					u = new URL("https://github.com/Antonio-Melo/Maze");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				openWebpage(u);
			}
		});
		github.setBounds(120, 606, 67, 54);
		github.setBorder(b);
		frmStarWars.getContentPane().add(github);
		
		JLabel lblHero = new JLabel("Hero");
		lblHero.setForeground(Color.WHITE);
		lblHero.setFont(new Font("Arial", Font.PLAIN, 17));
		lblHero.setBounds(10, 98, 127, 30);
		frmStarWars.getContentPane().add(lblHero);
		
	}

	public void move(char d) {
		g.play(d);
		GameState.setValue(100 - (perc * g.getDragons().size()));
		if (g.getMyHero().hasEscaped()) {
			gpanel.repaint();
			disableBtn();
			GameState.setValue(100);
			frmStarWars.remove(gpanel);
			frmStarWars.repaint();
			setGrid(null);
			frmStarWars.getContentPane().getComponent(18).setVisible(true);
		}

		if (g.getMyHero().isDead()) {
			gpanel.repaint();
			try {
			    Thread.sleep(1000);                
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			disableBtn();
			GameState.setBackground(Color.RED);
			frmStarWars.remove(gpanel);
			frmStarWars.repaint();
			setGrid(null);
			frmStarWars.getContentPane().getComponent(17).setVisible(true);
		}

		gpanel.repaint();
	}

	public void setGrid(char[][] grid) {
		this.customGrid = grid;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void disableBtn(){
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
	}

	public void setInstructionText(String s) {
		if(g.getMyHero() == null){
			Instructions.setText("");
			return;
		}
		
		if (g.getMyHero().isDead()) {
			Instructions.setText("You lost!");
			return;
		}
		if (g.getMyHero().hasEscaped()) {
			Instructions.setText("You won!");
			return;
		}

		Instructions.setText(s);
	}

	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void openWebpage(URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
