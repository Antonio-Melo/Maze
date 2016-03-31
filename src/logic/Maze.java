
package logic;

import java.util.ArrayList;

public class Maze {
	private char grid[][];

	// Constructor
	public Maze(char[][] m) {
		this.grid = m;
	}

	// Transforms grid in String
	public String toString(ArrayList<Dragon> dragons, Sword mySword) {
		String maze = "";
		for (int i = 0; i < dragons.size(); i++) {

			Dragon myDragon = dragons.get(i);
			if (myDragon.getX() == mySword.getX() && myDragon.getY() == mySword.getY())
				grid[myDragon.getX()][mySword.getY()] = 'F';
			
			else grid[myDragon.getX()][myDragon.getY()] = myDragon.getC();
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				maze = maze + grid[i][j] + " ";
			}
			maze = maze + "\n";
		}

		return maze;
	}

	/**
	 * Places any actor in the maze
	 * 
	 * @param a Actor to place
	 */
	public void placeActor(Actor a) {
		if (!a.isDead())
			grid[a.getX()][a.getY()] = a.getC();
	}

	public void removeActor(Actor a) {
		grid[a.getX()][a.getY()] = ' ';
	}
	
	public char [][] getGrid(){
		return grid;
	}
}
