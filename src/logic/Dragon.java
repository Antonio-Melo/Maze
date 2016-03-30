package logic;

import java.util.Random;
/**
 * Class that represents a Dragon in the game
 * @author António Melo  Edgar Passos
 * @version 1.0
 * @see Actor
 */
public class Dragon extends Actor {
	/**
	 * Boolean that tells us if the Dragon is sleeping
	 */
	private boolean sleep;
	/**
	 * Random seed associated with the Dragon
	 * Important to generate random moves
	 */
	private Random rng;
	/**
	 * He though that was better if the dragon sleeps for 5 turns instead of having the chance to wake up/sleep every time
	 */
	private int sleepCount;

	/**
	 * Constructor
	 * Creates a dragon with a certain character in the Position(x,y)
	 * @param x coordinate
	 * @param y coordinate
	 * @param c character
	 */
	public Dragon(int x, int y, char c) {
		super(x, y, c);
		sleep = false;
		this.sleepCount = 0;
	}
	/** 
	 * @return boolean showing if dragon is sleeping
	 */
	public boolean isAsleep() {
		return sleep;
	}
	/**
	 * Makes dragon fall a sleep for 5 turns
	 * Changes is character to lower case
	 */
	public void fallAsleep() {
		sleep = true;
		sleepCount = 5;
		c = 'd';
	}
	/**
	 * Makes dragon wake up
	 * Changes is character to upper case
	 */
	public void wakeUp() {
		sleep = false;
		c = 'D';
	}
	/**
	 * Moves the dragon to a position
	 * @param x new coordinate
	 * @param y new coordinate
	 */
	public void cmoveDragon(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves the dragon based on one character
	 * Checks if is possible
	 * @param m character that represents the move
	 * @param myHero Hero in the maze
	 * @param myMaze The maze
	 * @param enableSleep boolean that shows if dragons can sleep or not(modes)
	 */
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
			return;
		}
		// Fights Hero with sword
		if (((getX() + x + 1) == myHero.getX() && (getY() + y) == myHero.getY())
				|| ((getX() + x - 1) == myHero.getX() && (getY() + y) == myHero.getY())
				|| ((getX() + x) == myHero.getX() && (getY() + y + 1) == myHero.getY())
				|| ((getX() + x) == myHero.getX() && (getY() + y - 1) == myHero.getY())) {
			
			if(myHero.hasSword()){
				this.isKilled();
				return;
			}else{
				myHero.isKilled();

				cmoveDragon(getX() + x, getY() + y);
				return;
			}
		}
		// Space clear
		if (myMaze.getGrid()[getX() + x][getY() + y] == ' ') {
			cmoveDragon(getX() + x, getY() + y);
			return;
		}

		// Moves to sword
		if (myMaze.getGrid()[getX() + x][getY() + y] == 'E') {
			cmoveDragon(getX() + x, getY() + y);
			return;
		}
		// Default
		return;

	}

}
