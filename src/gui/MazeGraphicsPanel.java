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
import javax.swing.JTextArea;

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
	private int x = 0, y = 0, width, height;
	private Game game;

	public MazeGraphicsPanel(Game g,String hero) {
		// Luke
		try {
			hero1 = ImageIO.read(new File("res\\"+hero+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Luke with lightsaber
		try {
			hero1l = ImageIO.read(new File("res\\"+ hero+"_lightsaber"+".png"));
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
		
		
		width = 600/g.getMyMaze().getGrid().length;
		height = 600/g.getMyMaze().getGrid().length;
		
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
			Window.window.setInstructionText("You moved to the Left!");
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			Window.window.move('r');
			Window.window.setInstructionText("You moved to the Right!");
			break;

		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			Window.window.move('u');
			Window.window.setInstructionText("You moved Up!");
			break;

		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			Window.window.move('d');
			Window.window.setInstructionText("You moved Down!");
			break;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int f = 0; f < game.getDragons().size(); f++) {

			Dragon myDragon = game.getDragons().get(f);
			if (!game.getMySword().isDead()) {
				if (myDragon.getX() == game.getMySword().getX() && myDragon.getY() == game.getMySword().getY()) {
					game.getMyMaze().getGrid()[myDragon.getX()][game.getMySword().getY()] = 'F';
				} else
					game.getMyMaze().getGrid()[myDragon.getX()][myDragon.getY()] = myDragon.getC();
			}
		}

		for (int i = 0; i < game.getMyMaze().getGrid().length; i++) {
			for (int j = 0; j < game.getMyMaze().getGrid()[i].length; j++) {
				switch (game.getMyMaze().getGrid()[i][j]) {
				case 'X':
					g.drawImage(box, j * width, i * width, x + width, y + height, null);
					break;
				case ' ':
					g.drawImage(floor, j * width, i * width, x + width, y + height, null);
					break;
				case 'H':
					g.drawImage(hero1, j * width, i * width, x + width, y + height, null);
					break;
				case 'D':
					g.drawImage(stormtropper, j * width, i * width, x + width, y + height, null);
					break;
				case 'E':
					g.drawImage(lightsaber, j * width, i * width, x + width, y + height, null);
					break;
				case 'A':
					g.drawImage(hero1l, j * width, i * width, x + width, y + height, null);
					break;
				case 'd':
					g.drawImage(stormtroppers, j * width, i * width, x + width, y + height, null);
					break;
				case 'S':
					g.drawImage(door, j * width, i * width, x + width, y + height, null);
					break;
				case 'F':
					g.drawImage(stormtrooper_sword, j * width, i * width, x + width, y + height, null);
					break;
				default:
					break;

				}
			}
		}
	}
}
