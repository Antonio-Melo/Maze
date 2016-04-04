package gui;

import java.awt.BorderLayout;
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
		contentPanel.setBounds(0, 0, 884, 529);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JButton minusBtn = new JButton("-");
			minusBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(size == 4){
						JOptionPane.showMessageDialog(minusBtn,"Size cannot be lower than 4");
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
					

					if (customPanel != null){
						contentPanel.remove(customPanel);
					}
					
					contentPanel.repaint();

					customPanel = new CustomMazePanel(grid);
					customPanel.setBounds(170, 40, 40 * size, 40 * size);
					contentPanel.add(customPanel);
					customPanel.repaint();

				}
			});
			minusBtn.setBounds(10, 36, 64, 23);
			contentPanel.add(minusBtn);
		}
		{
			JButton plusBtn = new JButton("+");
			plusBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(size == 12){
						JOptionPane.showMessageDialog(plusBtn,"Size cannot be greater than 12");
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

					if (customPanel != null){
						contentPanel.remove(customPanel);
					}
					
					contentPanel.repaint();

					customPanel = new CustomMazePanel(grid);
					customPanel.setBounds(170, 40, 500,800);//40 * size, 40 * size);
					contentPanel.add(customPanel);
					customPanel.repaint();
				}
			});
			plusBtn.setBounds(85, 36, 64, 23);
			contentPanel.add(plusBtn);
		}
		{
			JButton boxBtn = new JButton();
			boxBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CustomMazePanel)customPanel).setCurrentChar('X');
				}
			});
			boxBtn.setIcon(new ImageIcon("res\\box.png"));
			boxBtn.setBounds(10, 184, 64, 69);
			contentPanel.add(boxBtn);
		}
		{
			JButton doorBtn = new JButton();
			doorBtn.setIcon(new ImageIcon("res\\door.png"));
			doorBtn.setBounds(85, 184, 64, 69);
			doorBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CustomMazePanel)customPanel).setCurrentChar('S');
					
				}
			});
			contentPanel.add(doorBtn);
		}
		{
			JButton btnCancel = new JButton("Cancel");
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
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					for(int i =0; i< grid.length; i++){
						for(int j = 0; j<grid.length; j++){
							
						}
					}
					
					if(((CustomMazePanel)customPanel).getnDoors() != 1){
						JOptionPane.showMessageDialog(button, "Invalid number of doors!");
						return;
					}
					
					for (int i = 0; i < size; i++){
						if(grid[i][0] == 'S')
							doorPlaced = true;
						if(grid[i][0] != 'S' && grid[i][0] != 'X'){
							JOptionPane.showMessageDialog(button, "Borders must contain walls or doors only!");
							return;
						}
						
					}

					for (int i = 0; i < size; i++){
						if(grid[i][size - 1] == 'S')
							doorPlaced = true;
					
						if(grid[i][size - 1] != 'S' && grid[i][size - 1] != 'X'){
							JOptionPane.showMessageDialog(button, "Borders must contain walls or doors only!");
							return;
						}
					}
					
					

					for (int i = 0; i < size; i++){
						if(grid[size - 1][i] == 'S')
							doorPlaced = true;
					
						if(grid[size - 1][i] != 'S' && grid[size - 1][i] != 'X'){
							JOptionPane.showMessageDialog(button, "Borders must contain walls or doors only!");
							return;
						}
					}
					

					for (int i = 0; i < size; i++){
						if(grid[0][i] == 'S')
							doorPlaced = true;
					
						if(grid[0][i] != 'S' && grid[0][i] != 'X'){
							JOptionPane.showMessageDialog(button, "Borders must contain walls or doors only!");
							return;
						}
					}
					
					if(!doorPlaced){
						JOptionPane.showMessageDialog(button, "Door must be placed at border!");
						return;
					}
					
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

					if (customPanel != null){
						getContentPane().remove(customPanel);
					}
					
					contentPanel.repaint();
					
					customPanel = new CustomMazePanel(grid);
					customPanel.setBounds(170, 40, 40 * size, 40 * size);
					contentPanel.add(customPanel);
					customPanel.repaint();
				}
			});
			buttonNew.setBounds(10, 70, 139, 23);
			contentPanel.add(buttonNew);
		}
		
		JButton floorBtn = new JButton();
		floorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CustomMazePanel)customPanel).setCurrentChar(' ');
			}
		});
		floorBtn.setIcon(new ImageIcon("res\\floor_tex.png"));
		floorBtn.setBounds(50, 264, 64, 69);
		contentPanel.add(floorBtn);
		{
			JLabel label = new JLabel("Ant\u00F3nio Melo & Edgar Passos");
			label.setBounds(10, 537, 195, 14);
			getContentPane().add(label);
		}
	}
}
