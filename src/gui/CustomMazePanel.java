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
import javax.swing.JPanel;

public class CustomMazePanel extends JPanel {
	private BufferedImage door;
	private BufferedImage floor;
	private BufferedImage box;
	private char[][] grid;
	private int x = 0, y = 0, width = 40, height = 40;

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
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j] == 'X') {
					g.drawImage(box, j * 40, i * 40, x + width, y + height, null);
				}
				if (grid[i][j] == ' ') {
					g.drawImage(floor, j * 40, i * 40, x + width, y + height, null);
				}

				if (grid[i][j] == 'S') {
					g.drawImage(door, j * 40, i * 40, x + width, y + height, null);
				}

			}
		}
	}
}
