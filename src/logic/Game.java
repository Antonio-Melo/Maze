package logic;

import java.util.ArrayList;
import java.util.Random;
/**
 * Represents the Game
 * @author Ant�nio Melo Edgar Passos
 * @see Maze MazeBuilder
 */
public class Game {
	/**
	 * The maze
	 */
	private Maze myMaze;
	/**
	 * Hero in the game
	 */
	private Hero myHero;
	/**
	 * Sword in the game
	 */
	private Sword mySword;
	/**
	 * Type of the game
	 */
	private char type;
	/**
	 * Dragons in the Maze
	 */
	private ArrayList<Dragon> dragons;

	/**
	 * Constructor
	 * Creates a new game with size 10
	 * @param type of game
	 * @param nDragons number of dragons
	 */
	public Game(char type, int nDragons) {
		char[][] grid;
		Random rng = new Random(System.currentTimeMillis());
		grid = new char[10][10];

		// Line 0

		for (int i = 0; i < 10; i++)
			grid[0][i] = 'X';

		// Line 9
		for (int i = 0; i < 10; i++)
			grid[9][i] = 'X';

		// Column 1

		for (int i = 0; i < 10; i++) {
			grid[i][0] = 'X';
		}

		// Column 9

		for (int i = 0; i < 10; i++) {
			grid[i][9] = 'X';
		}

		grid[2][2] = 'X';
		grid[2][3] = 'X';
		grid[2][5] = 'X';
		grid[2][7] = 'X';

		grid[3][2] = 'X';
		grid[3][3] = 'X';
		grid[3][5] = 'X';
		grid[3][7] = 'X';

		grid[4][2] = 'X';
		grid[4][3] = 'X';
		grid[4][5] = 'X';
		grid[4][7] = 'X';

		grid[6][2] = 'X';
		grid[6][3] = 'X';
		grid[6][5] = 'X';
		grid[6][7] = 'X';

		grid[7][2] = 'X';
		grid[7][3] = 'X';
		grid[7][5] = 'X';
		grid[7][7] = 'X';

		grid[5][7] = 'X';

		grid[8][2] = 'X';
		grid[8][3] = 'X';

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (grid[i][j] != 'X')
					grid[i][j] = ' ';
			}
		}
		grid[5][9] = 'S';

		myMaze = new Maze(grid);
		myHero = new Hero(1, 1, 'H');
		mySword = new Sword(8, 1, 'E');
		this.type = type;
		dragons = new ArrayList<Dragon>();
		for (int i = 0; i < nDragons; i++) {

			int x = rng.nextInt(grid.length-1); //Support for differently sized grids
			int y = rng.nextInt(grid.length-1);

			while (grid[x][y] != ' ') {
				x = rng.nextInt(grid.length-1);
				y = rng.nextInt(grid.length-1);
			}

			Dragon newDragon = new Dragon(x, y, 'D');
			dragons.add(newDragon);
		}
	}

	/**
	 * Constructor 2
	 * Creates a new game based on a grid made 
	 * @param type of game
	 * @param grid 
	 * @param nDragons number of dragons
	 */
	public Game(char type, char[][] grid,int nDragons) {
		myMaze = new Maze(grid);
		dragons = new ArrayList<Dragon>();
		
		Random rng = new Random(System.currentTimeMillis());
		this.type = type;
		
		//Random Dragons position
		for (int i = 0; i < nDragons; i++) {

			int x = rng.nextInt(grid.length); // TODO support for differently sized grids
			int y = rng.nextInt(grid.length);

			while (grid[x][y] != ' ') {
				x = rng.nextInt(grid.length);
				y = rng.nextInt(grid.length);
			}

			Dragon newDragon = new Dragon(x, y, 'D');
			dragons.add(newDragon);
			myMaze.placeActor(newDragon);
		}
		
		//Random Hero position
		int x = rng.nextInt(grid.length-2)+1; 
		int y = rng.nextInt(grid.length-2)+1;
		
		while(grid[x][y] != ' ' || grid[x+1][y] =='D'|| grid[x-1][y] =='D'|| grid[x][y+1] =='D'|| grid[x][y-1] =='D'|| grid[x+1][y+1] =='D'|| grid[x+1][y-1] =='D'|| grid[x-1][y+1] =='D'|| grid[x-1][y-1] =='D' ){
			 x = rng.nextInt(grid.length-2)+1; 
			 y = rng.nextInt(grid.length-2)+1;
		}
		myHero = new Hero(x, y, 'H');
		myMaze.placeActor(myHero);
		
		//Random sword position
		x = rng.nextInt(grid.length-2)+1; 
		y = rng.nextInt(grid.length-2)+1;
		while(grid[x][y] != ' '){
			 x = rng.nextInt(grid.length-2)+1; 
			 y = rng.nextInt(grid.length-2)+1;
		}
		mySword = new Sword(x, y, 'E');
		myMaze.placeActor(mySword);
	}

	
	
	/**
	 * Plays a turn
	 * @param m character
	 */
	public void play(char m) {

		boolean enableSleep;
		myMaze.removeActor(myHero);
		myHero.moveHero(m, dragons, myMaze);

		if (type == '1' || type == '2') {

			if (type == '1')
				enableSleep = false;
			else
				enableSleep = true;

			Random r = new Random();
			for (int i = 0; i < dragons.size(); i++) {
				myMaze.removeActor(dragons.get(i));
				Dragon myDragon = dragons.get(i);
				int moveDragon = r.nextInt() % 4 + 1;
				switch (moveDragon) {
				case 1:
					myDragon.moveDragon('r', myHero, myMaze, enableSleep);
					break;
				case 2:
					myDragon.moveDragon('l', myHero, myMaze, enableSleep);
					break;
				case 3:
					myDragon.moveDragon('u', myHero, myMaze, enableSleep);
					break;
				case 4:
					myDragon.moveDragon('d', myHero, myMaze, enableSleep);
					break;
				}
				
				if(!myDragon.isDead()){
					myMaze.placeActor(dragons.get(i));
				}else
					dragons.remove(i--);
			}
		}
		myMaze.placeActor(myHero);
	}
	/**
	 * @return myMaze
	 */
	public  Maze getMyMaze(){
		return myMaze;
	}
	/**
	 * @return Hero
	 */
	public Hero getMyHero(){
		return myHero;
	}
	/**
	 * @return Sword
	 */
	public Sword getMySword(){
		return mySword;
	}
	/**
	 * @return dragons
	 */
	public ArrayList<Dragon> getDragons(){
		return dragons;
	}
}
