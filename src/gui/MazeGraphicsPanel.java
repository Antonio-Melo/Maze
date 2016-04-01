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

public class MazeGraphicsPanel extends JPanel implements KeyListener{
	private BufferedImage hero1;
	private int x=500, y=500, width=200, height=200;
	
		public MazeGraphicsPanel(){
			//Luke 
			try {
				hero1 =  ImageIO.read(new File("res\\luke_front.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			addKeyListener(this);
		}
	
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyReleased(KeyEvent e) {}
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {

			case KeyEvent.VK_LEFT:
				Window.window.move('l'); 
				break;

			case KeyEvent.VK_RIGHT:
				Window.window.move('r');
				break;

			case KeyEvent.VK_UP:
				Window.window.move('u');
				break;

			case KeyEvent.VK_DOWN:
				Window.window.move('d');
				break;
			}
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);		
			g.drawImage(hero1, x, y, x + width - 1, y + height - 1, 0, 0, hero1.getWidth(), hero1.getHeight(), null);
		}
}


