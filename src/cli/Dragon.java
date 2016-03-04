package cli;

public class Dragon {

	int x, y;
	boolean dead;

	// Constructor
	public Dragon(int x, int y) {
		this.x = x;
		this.y = y;
		this.dead = false;
	}

	// Coord X
	public int getX() {
		return x;
	}

	// Coord Y
	public int getY() {
		return y;
	}

	// Checks if Dragon is dead
	public boolean isDead() {
		return dead;
	}

	// Kills Dragon
	public void killDragon() {
		dead = true;
	}
	
	public void cmoveDragon(int x, int y){
		this.x = x;
		this.y =y;
	}

	// From input m,calculates if the Dragon can me moved
	public void moveDragon(char m, Hero myHero, Maze myMaze) {
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
		if (myMaze.grid[getX() + x][getY() + y] == 'X') {
			return;
		}
		// Exit
		if (myMaze.grid[getX() + x][getY() + y] == 'S') {
			return;
		}
		// Fights Hero with sword
		if (myMaze.grid[getX() + x + x][getY() + y + y] == 'A') {
			killDragon();
			return;
		}

		// Fights Hero without sword
		if (myMaze.grid[getX() + x + x][getY() + y + y] == 'H') {
			myHero.isKilled();
			myMaze.removeHero(myHero);
			
			cmoveDragon(getX()+x,getY()+y);
			return;
		}
		// Space clear
		if (myMaze.grid[getX() + x][getY() + y] == ' ') {
			cmoveDragon(getX()+x,getY()+y);
			return;
		}
		// Picks up the sword
		if (myMaze.grid[getX() + x][getY() + y] == 'E') {
			cmoveDragon(getX()+x,getY()+y);
			return;
		}
		// Default
		return;

	}

}
