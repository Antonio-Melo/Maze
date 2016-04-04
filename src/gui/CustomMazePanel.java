package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CustomMazePanel extends JPanel implements MouseListener {
	private BufferedImage door;
	private BufferedImage floor;
	private BufferedImage box;
	private BufferedImage hero1;
	private BufferedImage lightsaber;
	private BufferedImage stormtropper;
	private char[][] grid;
	private int x = 0, y = 0, width, height;
	private char currentChar;
	private int nDoors, nSwords, nHeroes;
	private int nDragons;

	public CustomMazePanel(char[][] grid) {

		this.grid = grid;
		// Box
		try {
			box = ImageIO.read(new File("res\\box.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Door
		try {
			door = ImageIO.read(new File("res\\door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Floor
		try {
			floor = ImageIO.read(new File("res\\floor_tex.png"));
		} catch (IOException e) {
			e.printStackTrace();

		}

		// Luke
		try {
			hero1 = ImageIO.read(new File("res\\luke.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Stormtropper
		try {
			stormtropper = ImageIO.read(new File("res\\stormtrooper_front.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Lightsaber
		try {
			lightsaber = ImageIO.read(new File("res\\lightsaber.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		width = 480 / grid.length;
		height = 480 / grid.length;

		addMouseListener(this);
		requestFocus();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j] == 'X') {
					g.drawImage(box, j * width, i * width, x + width, y + height, null);
				}
				if (grid[i][j] == ' ') {
					g.drawImage(floor, j * width, i * width, x + width, y + height, null);
				}

				if (grid[i][j] == 'S') {
					g.drawImage(door, j * width, i * width, x + width, y + height, null);
				}
				
				if (grid[i][j] == 'H') {
					g.drawImage(hero1, j * width, i * width, x + width, y + height, null);
				}
				
				if (grid[i][j] == 'D') {
					g.drawImage(stormtropper, j * width, i * width, x + width, y + height, null);
				}
				
				if (grid[i][j] == 'E') {
					g.drawImage(lightsaber, j * width, i * width, x + width, y + height, null);
				}

			}
		}
	}

	public void setCurrentChar(char c) {
		currentChar = c;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point mousePos = getMousePosition();
		double x = mousePos.getX();
		double y = mousePos.getY();

		int xToEdit = (int) y / height;
		int yToEdit = (int) x / width;

		if (currentChar == 'S' && ((xToEdit == 0 && yToEdit == 0) || (xToEdit == 0 && yToEdit == grid.length - 1)
				|| (xToEdit == grid.length - 1 && yToEdit == 0)
				|| (xToEdit == grid.length - 1 && yToEdit == grid.length - 1))) {
			JOptionPane.showMessageDialog(this, "Doors cannot be placed at corners");
			return;
		}/*

		if (currentChar == 'S')
			nDoors++;

		if (currentChar != 'S' && grid[xToEdit][yToEdit] == 'S')
			nDoors--;
		
		if (currentChar == 'E')
			nSwords++;

		if (currentChar != 'E' && grid[xToEdit][yToEdit] == 'E')
			nSwords--;
		
		if (currentChar == 'H')
			nHeroes++;

		if (currentChar != 'H' && grid[xToEdit][yToEdit] == 'H')
			nHeroes--;
		
		if (currentChar == 'D')
			nDragons++;

		if (currentChar != 'D' && grid[xToEdit][yToEdit] == 'D')
			nDragons--;*/

		grid[xToEdit][yToEdit] = currentChar;
		repaint();

	}
/*
	public void setnDoors(int n) {
		this.nDoors = n;
	}
	
	public void setnSwords(int n) {
		this.nSwords = n;
	}
	
	public void setnHeroes(int n) {
		this.nHeroes = n;
	}	
	
	public void setnDragons(int n) {
		this.nDragons = n;
	}
	
	public int getnSwords(){
		return nSwords;
	}
	
	public int getnDoors(){
		return nDoors;
	}

	public int getnHeroes(){
		return nHeroes;
	}
	
	public int getnDragons(){
		return nDragons;
	}*/
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
