
package logic;

import java.util.ArrayList;
/**
 * Represents the maze in the game
 * @author António Melo Edgar Passos
 * @see MazeBuilder IMazeBuilder
 */
public class Maze {
	private char grid[][];

	/**
	 * Creates a Maze based on a given grid
	 * @param m grid
	 */
	public Maze(char[][] m) {
		this.grid = m;
	}

	/**
	 * Transforms grid in String
	 * @param dragons ArrayList
	 * @param mySword Sword
	 * @return string
	 */
	public String toString(ArrayList<Dragon> dragons, Sword mySword) {
		String maze = "";
	
		for (int i = 0; i < dragons.size(); i++) {

			Dragon myDragon = dragons.get(i);
			if (myDragon.getX() == mySword.getX() && myDragon.getY() == mySword.getY()){
				grid[myDragon.getX()][mySword.getY()] = 'F';
			}else grid[myDragon.getX()][myDragon.getY()] = myDragon.getC();
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
	 * Places a living actor in the maze
	 * @param a Actor to place
	 */
	public void placeActor(Actor a) {
		if (!a.isDead() || (a instanceof Hero))
			grid[a.getX()][a.getY()] = a.getC();
	}
	/**
	 * Removes a actor from the maze
	 * @param a Actor to remove
	 */
	public void removeActor(Actor a) {
		grid[a.getX()][a.getY()] = ' ';
	}
	/**
	 * @return grid
	 */
	public char [][] getGrid(){
		return grid;
	}
}
