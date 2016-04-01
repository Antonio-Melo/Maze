package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MazeGraphicsPanel extends JPanel implements KeyListener{
	
	
		public MazeGraphicsPanel(){
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
}


