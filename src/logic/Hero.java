
package logic;

import java.util.ArrayList;
/**
 * Representing the hero in the maze
 * @author António Melo Edgar Passos
 * @see Actor
 */
public class Hero extends Actor {
	/**
	 * Boolean that shows if the hero has escaped from the maze
	 */
	private boolean escape;
	/**
	 * Boolean that shows if the hero has the sword
	 */
	private boolean sword;

	/**
	 * Constructor
	 * Creates a hero with a certain character in the Position(x,y)
	 * @param x coordinate
	 * @param y coordinate
	 * @param c character
	 */
	public Hero(int x, int y, char c) {
		super(x, y, c);
		this.escape = false;
		this.sword = false;
	}

	/**
	 * Checks if hero has the sword
	 * @return boolean
	 */
	public boolean hasSword() {
		return sword;
	}

	/**
	 * Picks up the sword
	 */
	public void pickUpSword() {
		sword = true;
		c = 'A';
	}
	/**
	 * Checks if the Hero has already escaped the maze
	 * @return boolean
	 */
	public boolean hasEscaped() {
		return escape;
	}

	/**
	 * When the hero finds the exit after killing all the dragons
	 */
	public void escape() {
		escape = true;
	}

	/**
	 * Moves hero to a certain Position(x,y)
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void cmoveHero(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves the Hero based on one character
	 * Checks if is possible
	 * @param m character
	 * @param dragons
	 * @param myMaze
	 */
	public void moveHero(char m, ArrayList<Dragon> dragons, Maze myMaze) {
		int x = 0, y = 0;

		if (m == 'r' || m == 'R') {
			y = 1;
			x = 0;
		} else if (m == 'l' || m == 'L') {
			y = -1;
			x = 0;
		} else if (m == 'u' || m == 'U') {
			x = -1;
			y = 0;
		} else if (m == 'd' || m == 'D') {
			x = 1;
			y = 0;
		}

		// Wall
		if (myMaze.getGrid()[getX() + x][getY() + y] == 'X') {
			return;
		}

		// Exit
		if (myMaze.getGrid()[getX() + x][getY() + y] == 'S') {

			for (int i = 0; i < dragons.size(); i++) {
				if (!dragons.get(i).isDead())
					return;
			}

			escape();
			return;
		}
		
		if (myMaze.getGrid()[getX() + x][getY() + y] == 'd') {
			return;
		}
		
		// Fights Dragon
		for (int i = 0; i < dragons.size(); i++) {
			if (((getX() + x + 1) == dragons.get(i).getX() && (getY() + y) == dragons.get(i).getY())
					|| ((getX() + x - 1) == dragons.get(i).getX() && (getY() + y) == dragons.get(i).getY())
					|| ((getX() + x) == dragons.get(i).getX() && (getY() + y + 1) == dragons.get(i).getY())
					|| ((getX() + x) == dragons.get(i).getX() && (getY() + y - 1) == dragons.get(i).getY())) {
				if (hasSword()) {
					dragons.get(i).isKilled();
					myMaze.removeActor(dragons.get(i));
					dragons.remove(i--);
					cmoveHero(getX() + x, getY() + y);
					return;
				} else{
					if(dragons.get(i).isAsleep()){
						cmoveHero(getX() + x, getY() + y);	
					}else{
						isKilled();
					}
					return;
				}
			}
		}
		

		// Picks up the sword
		if (myMaze.getGrid()[getX() + x][getY() + y] == 'E') {
			pickUpSword();
			cmoveHero(getX() + x, getY() + y);
			return;
		}

		// Space is clear
		else {
			cmoveHero(getX() + x, getY() + y);
			return;
		}
	}
}
