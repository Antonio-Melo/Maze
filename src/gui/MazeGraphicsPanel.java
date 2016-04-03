package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logic.*;

public class MazeGraphicsPanel extends JPanel implements KeyListener {
	private BufferedImage hero1;
	private BufferedImage hero1l;
	private BufferedImage lightsaber;
	private BufferedImage box;
	private BufferedImage floor;
	private BufferedImage stormtropper;
	private BufferedImage stormtroppers;
	private BufferedImage door;
	private BufferedImage stormtrooper_sword;
	private int x = 0, y = 0, width = 40, height = 40;
	private Game game;

	public MazeGraphicsPanel(Game g) {
		// Luke
		try {
			hero1 = ImageIO.read(new File("res\\luke_front.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Luke with lightsaber
		try {
			hero1l = ImageIO.read(new File("res\\luke_front_sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Lightsaber
		try {
			lightsaber = ImageIO.read(new File("res\\lightsaber.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Box
		try {
			box = ImageIO.read(new File("res\\box.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Floor
		try {
			floor = ImageIO.read(new File("res\\floor_tex.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Stormtropper
		try {
			stormtropper = ImageIO.read(new File("res\\stormtrooper_front.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Stormtropper sleeping
		try {
			stormtroppers = ImageIO.read(new File("res\\stormtrooper_sleep.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Door
		try {
			door = ImageIO.read(new File("res\\door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Stormtropper with sword
		try {
			stormtrooper_sword = ImageIO.read(new File("res\\stormtrooper_sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		game = g;
		addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			Window.window.move('l');
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			Window.window.move('r');
			break;

		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			Window.window.move('u');
			break;

		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			Window.window.move('d');
			break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int f = 0; f < game.getDragons().size(); f++) {

			Dragon myDragon = game.getDragons().get(f);
			if (myDragon.getX() == game.getMySword().getX() && myDragon.getY() == game.getMySword().getY()) {
				game.getMyMaze().getGrid()[myDragon.getX()][game.getMySword().getY()] = 'F';
			} else
				game.getMyMaze().getGrid()[myDragon.getX()][myDragon.getY()] = myDragon.getC();
		}


		for (int i = 0; i < game.getMyMaze().getGrid().length; i++) {
			for (int j = 0; j < game.getMyMaze().getGrid()[i].length; j++) {
				if (game.getMyMaze().getGrid()[i][j] == 'X') {
					g.drawImage(box, j * 40, i * 40, x + width, y + height, null);
				}
				if (game.getMyMaze().getGrid()[i][j] == ' ') {
					g.drawImage(floor, j * 40, i * 40, x + width, y + height, null);
				}
				if (game.getMyMaze().getGrid()[i][j] == 'H') {
					g.drawImage(hero1, j * 40, i * 40, x + width, y + height, null);
				}
				if (game.getMyMaze().getGrid()[i][j] == 'D') {
					g.drawImage(stormtropper, j * 40, i * 40, x + width, y + height, null);
				}
				if (game.getMyMaze().getGrid()[i][j] == 'E') {
					g.drawImage(lightsaber, j * 40, i * 40, x + width, y + height, null);
				}
				if (game.getMyMaze().getGrid()[i][j] == 'A') {
					g.drawImage(hero1l, j * 40, i * 40, x + width, y + height, null);
				}
				if (game.getMyMaze().getGrid()[i][j] == 'd') {
					g.drawImage(stormtroppers, j * 40, i * 40, x + width, y + height, null);
				}
				if (game.getMyMaze().getGrid()[i][j] == 'S') {
					g.drawImage(door, j * 40, i * 40, x + width, y + height, null);
				}
				if (game.getMyMaze().getGrid()[i][j] == 'F') {
					g.drawImage(stormtrooper_sword, j * 40, i * 40, x + width, y + height, null);
				}

			}
		}
	}
}
