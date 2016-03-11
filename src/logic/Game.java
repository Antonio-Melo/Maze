package logic;

import java.util.Random;

public class Game {
	public Maze myMaze;
	public Hero myHero;
	public Sword mySword;
	public Dragon myDragon;
	char type;
	
	//Constructor
	public Game(char type) {
		char [][] grid;
		grid = new char[10][10];

		// Linha 0

		for (int i = 0; i < 10; i++)
			grid[0][i] = 'X';

		// Linha 9
		for (int i = 0; i < 10; i++)
			grid[9][i] = 'X';

		// Coluna 1

		for (int i = 0; i < 10; i++) {
			grid[i][0] = 'X';
		}

		// Coluna 9

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
		myHero = new Hero(1,1,'H');
		mySword = new Sword(8,1,'E');
		myDragon = new Dragon(3,1,'D');
		this.type = type;
	}
	
	//Starts Game
	public void play(char m){
		
		
		boolean enableSleep;
		myMaze.removeActor(myHero);
		myHero.moveHero(m,myDragon,myMaze);
		myMaze.placeActor(myHero);
		
		myMaze.removeActor(myDragon);
		if(type == '1' || type == '2'){
			
			if(type == '1')
				enableSleep = false;
			else
				enableSleep = true;
			
			Random r = new Random();
			int moveDragon = r.nextInt() % 4 + 1;
			switch(moveDragon){
			case 1:
				myDragon.moveDragon('d',myHero,myMaze,enableSleep);
				break;
			case 2:
				myDragon.moveDragon('e',myHero,myMaze,enableSleep);
				break;
			case 3:
				myDragon.moveDragon('c',myHero,myMaze,enableSleep);
				break;
			case 4:
				myDragon.moveDragon('b',myHero,myMaze,enableSleep);
				break;
			}
		}

		myMaze.placeActor(myDragon);
		myMaze.placeActor(myHero);
	}
}
