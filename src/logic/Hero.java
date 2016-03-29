
package logic;

import java.util.ArrayList;

public class Hero extends Actor {

	private boolean escape, sword;

	// Constructor
	public Hero(int x, int y, char c) {
		super(x, y, c);
		this.escape = false;
		this.sword = false;
	}

	// Checks if has Sword
	public boolean hasSword() {
		return sword;
	}

	// Picks sword
	public void pickUpSword() {
		sword = true;
		c = 'A';
	}
	// Checks if the Hero as already escaped the maze
	public boolean hasEscaped() {
		return escape;
	}

	// When the hero finds the exit
	public void escape() {
		escape = true;
	}

	// Change coord of Hero
	public void cmoveHero(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// From input m,calculates if the Hero can me moved
	public void moveHero(char m, ArrayList<Dragon> dragons, Maze myMaze) {
		int x = 0, y = 0;

		if (m == 'd' || m == 'D') {
			y = 1;
			x = 0;
		} else if (m == 'e' || m == 'E') {
			y = -1;
			x = 0;
		} else if (m == 'c' || m == 'C') {
			x = -1;
			y = 0;
		} else if (m == 'b' || m == 'B') {
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
