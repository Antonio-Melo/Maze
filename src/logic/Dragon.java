package logic;

import java.util.Random;

public class Dragon extends Actor {

	boolean sleep;
	Random rng;
	int sleepCount;

	// Constructor
	public Dragon(int x, int y, char c) {
		super(x, y, c);
		sleep = false;
		this.sleepCount = 0;
	}

	public boolean isAsleep() {
		return sleep;
	}

	public void fallAsleep() {
		sleep = true;
		sleepCount = 5;		//Sleeps for 5 turns
		c = 'd';
	}

	public void wakeUp() {
		sleep = false;
		c = 'D';
	}

	public void cmoveDragon(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// From input m,calculates if the Dragon can me moved
	public void moveDragon(char m, Hero myHero, Maze myMaze, boolean enableSleep) {
		int x = 0, y = 0;
		this.rng = new Random(System.currentTimeMillis());

		if (enableSleep) {

			sleepCount--;
			if(sleepCount>0)
				return;
			
			int sleepChance = rng.nextInt(10) + 1;

			if (sleepChance == 1) {
				this.fallAsleep();
				return;
			} else
				wakeUp();
		}

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
		if (myMaze.grid[getX() + x][getY() + y] == 'X') {
			return;
		}
		// Exit
		if (myMaze.grid[getX() + x][getY() + y] == 'S') {
			return;
		}
		// Fights Hero with sword
		if (myMaze.grid[getX() + x + x][getY() + y + y] == 'A') {
			this.isKilled();
			return;
		}

		// Fights Hero without sword
		if (myMaze.grid[getX() + x + x][getY() + y + y] == 'H') {
			myHero.isKilled();

			cmoveDragon(getX() + x, getY() + y);
			return;
		}
		// Space clear
		if (myMaze.grid[getX() + x][getY() + y] == ' ') {
			cmoveDragon(getX() + x, getY() + y);
			return;
		}

		// Picks up the sword
		if (myMaze.grid[getX() + x][getY() + y] == 'E') {
			cmoveDragon(getX() + x, getY() + y);
			return;
		}
		// Default
		return;

	}

}
