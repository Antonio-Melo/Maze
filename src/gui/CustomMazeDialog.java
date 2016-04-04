package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CustomMazeDialog extends JDialog {

	private Window parentWindow;
	private char[][] grid;
	private JPanel customPanel;
	private int size = 4;
	private boolean doorPlaced = false;
	int nDoors, nDragons, nHeroes, nSwords;

	public void setParent(Window w1) {
		this.parentWindow = w1;
	}

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomMazeDialog dialog = new CustomMazeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomMazeDialog() {
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBounds(0, 0, 884, 529);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JButton minusBtn = new JButton("-");
			minusBtn.setBackground(Color.ORANGE);
			minusBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (size == 4) {
						JOptionPane.showMessageDialog(minusBtn, "Size cannot be lower than 4");
						return;
					}

					size--;
					grid = new char[size][size];

					for (int i = 0; i < size; i++) {
						for (int j = 0; j < size; j++) {
							grid[i][j] = ' ';
						}
					}

					for (int i = 0; i < size; i++)
						grid[i][0] = 'X';

					for (int i = 0; i < size; i++)
						grid[i][size - 1] = 'X';

					for (int i = 0; i < size; i++)
						grid[size - 1][i] = 'X';

					for (int i = 0; i < size; i++)
						grid[0][i] = 'X';

					if (customPanel != null) {
						contentPanel.remove(customPanel);
					}

					contentPanel.repaint();

					customPanel = new CustomMazePanel(grid);
					customPanel.setBounds(170, 40, 480, 480);
					contentPanel.add(customPanel);
					customPanel.repaint();

				}
			});
			minusBtn.setBounds(10, 36, 64, 23);
			contentPanel.add(minusBtn);
		}
		{
			JButton plusBtn = new JButton("+");
			plusBtn.setBackground(Color.ORANGE);
			plusBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (size == 25) {
						JOptionPane.showMessageDialog(plusBtn, "Size cannot be greater than 25");
						return;
					}
					size++;
					grid = new char[size][size];

					for (int i = 0; i < size; i++) {
						for (int j = 0; j < size; j++) {
							grid[i][j] = ' ';
						}
					}
					for (int i = 0; i < size; i++)
						grid[i][0] = 'X';

					for (int i = 0; i < size; i++)
						grid[i][size - 1] = 'X';

					for (int i = 0; i < size; i++)
						grid[size - 1][i] = 'X';

					for (int i = 0; i < size; i++)
						grid[0][i] = 'X';

					if (customPanel != null) {
						contentPanel.remove(customPanel);
					}

					contentPanel.repaint();

					customPanel = new CustomMazePanel(grid);
					customPanel.setBackground(Color.LIGHT_GRAY);
					customPanel.setBounds(170, 40, 480, 480);// 40 * size, 40 *
																// size);
					contentPanel.add(customPanel);
					customPanel.repaint();
				}
			});
			plusBtn.setBounds(85, 36, 64, 23);
			contentPanel.add(plusBtn);
		}
		{
			JButton boxBtn = new JButton();
			boxBtn.setBackground(Color.ORANGE);
			boxBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CustomMazePanel) customPanel).setCurrentChar('X');
				}
			});
			boxBtn.setIcon(new ImageIcon("res\\box.png"));
			boxBtn.setBounds(10, 140, 64, 69);
			contentPanel.add(boxBtn);
		}
		{
			JButton doorBtn = new JButton();
			doorBtn.setBackground(Color.ORANGE);
			doorBtn.setIcon(new ImageIcon("res\\door.png"));
			doorBtn.setBounds(85, 140, 64, 69);
			doorBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CustomMazePanel) customPanel).setCurrentChar('S');

				}
			});
			contentPanel.add(doorBtn);
		}

		JButton floorBtn = new JButton();
		floorBtn.setBackground(Color.ORANGE);
		floorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CustomMazePanel) customPanel).setCurrentChar(' ');
			}
		});
		floorBtn.setIcon(new ImageIcon("res\\floor_tex.png"));
		floorBtn.setBounds(10, 220, 64, 69);
		contentPanel.add(floorBtn);

		JButton heroBtn = new JButton();
		heroBtn.setBackground(Color.ORANGE);
		heroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CustomMazePanel) customPanel).setCurrentChar('H');
			}
		});
		heroBtn.setIcon(new ImageIcon("res\\luke.png"));
		heroBtn.setBounds(85, 220, 64, 69);
		contentPanel.add(heroBtn);

		JButton strooperBtn = new JButton();
		strooperBtn.setBackground(Color.ORANGE);

		strooperBtn.setIcon(new ImageIcon("res\\stormtrooper_front.png"));
		strooperBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CustomMazePanel) customPanel).setCurrentChar('D');
			}
		});
		strooperBtn.setBounds(10, 300, 64, 69);
		contentPanel.add(strooperBtn);

		JButton swordButton = new JButton();
		swordButton.setBackground(Color.ORANGE);
		swordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CustomMazePanel) customPanel).setCurrentChar('E');
			}
		});
		swordButton.setIcon(new ImageIcon("res\\lightsaber.png"));
		swordButton.setBounds(85, 300, 64, 69);
		contentPanel.add(swordButton);

		JLabel label = new JLabel("Ant\u00F3nio Melo & Edgar Passos");
		label.setBounds(10, 537, 195, 14);
		getContentPane().add(label);
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setBackground(Color.ORANGE);
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setBounds(10, 405, 139, 51);
			contentPanel.add(btnCancel);
		}
		{
			JButton button = new JButton("OK");
			button.setBackground(Color.ORANGE);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					nHeroes = 0;
					nDoors = 0;
					nDragons = 0;
					nSwords = 0;

					for (int i = 0; i < grid.length; i++) {
						for (int j = 0; j < grid.length; j++) {

						}
					}

					for (int i = 0; i < size; i++) {
						if (grid[i][0] == 'S')
							doorPlaced = true;
						if (grid[i][0] != 'S' && grid[i][0] != 'X') {
							JOptionPane.showMessageDialog(button, "Borders must contain walls or doors only!");
							return;
						}

					}

					for (int i = 0; i < size; i++) {
						if (grid[i][size - 1] == 'S')
							doorPlaced = true;

						if (grid[i][size - 1] != 'S' && grid[i][size - 1] != 'X') {
							JOptionPane.showMessageDialog(button, "Borders must contain walls or doors only!");
							return;
						}
					}

					for (int i = 0; i < size; i++) {
						if (grid[size - 1][i] == 'S')
							doorPlaced = true;

						if (grid[size - 1][i] != 'S' && grid[size - 1][i] != 'X') {
							JOptionPane.showMessageDialog(button, "Borders must contain walls or doors only!");
							return;
						}
					}

					for (int i = 0; i < size; i++) {
						if (grid[0][i] == 'S')
							doorPlaced = true;

						if (grid[0][i] != 'S' && grid[0][i] != 'X') {
							JOptionPane.showMessageDialog(button, "Borders must contain walls or doors only!");
							return;
						}

					}

					if (!doorPlaced) {
						JOptionPane.showMessageDialog(button, "Door must be placed at border!");
						return;
					}

					for (int i = 0; i < size; i++) {
						for (int j = 0; j < size; j++) {
							if (grid[i][j] == 'S')
								nDoors++;
							else if (grid[i][j] == 'H')
								nHeroes++;
							else if (grid[i][j] == 'E')
								nSwords++;
							else if (grid[i][j] == 'D')
								nDragons++;
						}
					}

					if (nSwords != 1) {
						JOptionPane.showMessageDialog(button, "You must (only) have one lightsaber! You have "
								+ nSwords + " lightsabers");
						return;
					}

					if (nHeroes != 1) {
						JOptionPane.showMessageDialog(button, "You must (only) have one hero! You have "
								+ nHeroes + " heroes");
						return;
					}

					if (nDragons < 1) {
						JOptionPane.showMessageDialog(button, "You must place enemies!");
						return;
					}

					if (nDoors != 1) {
						JOptionPane.showMessageDialog(button, "Invalid number of doors!");
						return;
					}

					/*
					 * 
					 * 
					 * if (((CustomMazePanel) customPanel).getnDoors() != 1) {
					 * JOptionPane.showMessageDialog(button,
					 * "Invalid number of doors!"); return; }
					 * 
					 * if(((CustomMazePanel)customPanel).getnSwords() != 1) {
					 * JOptionPane.showMessageDialog(button,
					 * "You must (only) have one lightsaber! You have " +
					 * ((CustomMazePanel)customPanel).getnSwords() +
					 * " lightsabers"); return; }
					 * 
					 * if(((CustomMazePanel)customPanel).getnHeroes() != 1) {
					 * JOptionPane.showMessageDialog(button,
					 * "You must (only) have one hero! You have " +
					 * ((CustomMazePanel)customPanel).getnHeroes() + " heroes");
					 * return; }
					 * 
					 * if(((CustomMazePanel)customPanel).getnDragons() < 1) {
					 * JOptionPane.showMessageDialog(button,
					 * "You must place enemies!"); return; }
					 */
					parentWindow.setSize(grid.length);
					parentWindow.setGrid(grid);
					dispose();
				}
			});
			button.setBounds(10, 467, 139, 51);
			contentPanel.add(button);
		}
		{
			JButton buttonNew = new JButton("New");
			buttonNew.setBackground(Color.ORANGE);
			buttonNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					grid = new char[size][size];

					for (int i = 0; i < size; i++) {
						for (int j = 0; j < size; j++) {
							grid[i][j] = ' ';
						}
					}
					for (int i = 0; i < size; i++)
						grid[i][0] = 'X';

					for (int i = 0; i < size; i++)
						grid[i][size - 1] = 'X';

					for (int i = 0; i < size; i++)
						grid[size - 1][i] = 'X';

					for (int i = 0; i < size; i++)
						grid[0][i] = 'X';

					if (customPanel != null) {
						getContentPane().remove(customPanel);
					}

					contentPanel.repaint();

					customPanel = new CustomMazePanel(grid);
					customPanel.setBounds(170, 40, 480,
							480);/*
									 * ((CustomMazePanel)customPanel).setnDoors(
									 * 0);
									 * ((CustomMazePanel)customPanel).setnSwords
									 * (0);
									 * ((CustomMazePanel)customPanel).setnHeroes
									 * (0); ((CustomMazePanel)customPanel).
									 * setnDragons(0);
									 */
					nDoors = 0;
					nDragons = 0;
					nHeroes = 0;
					nSwords = 0;
					contentPanel.add(customPanel);
					customPanel.repaint();
				}
			});
			buttonNew.setBounds(10, 70, 139, 23);
			contentPanel.add(buttonNew);
		}

	}

}
