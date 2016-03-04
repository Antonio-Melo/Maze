package cli;

public class Maze {
	char grid[][];
	
	//Constructor
	public Maze() {
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
	}
	//Transforms grid in String
	public String toString(Dragon myDragon, Sword mySword){
		String maze="";
		if(myDragon.getX() == mySword.getX() && myDragon.getY() == mySword.getY())
			grid[myDragon.getX()][mySword.getY()] = 'F';
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				maze = maze +grid[i][j] + " ";
			}
			maze = maze +"\n";
		}	
		return maze;
	}
	
	//Places Dragon in the maze
	public void placeDragon(Dragon d) {
		if(!d.isDead()){
			grid[d.getX()][d.getY()] = 'D';
		}
	}
	//Removes Dragon from the maze
	public void removeDragon(Dragon d){
		grid[d.getX()][d.getY()] = ' ';
	}
	//Places Hero in the maze
	public void placeHero(Hero h) {
		if(!h.isDead() && !h.asEscaped()){
		grid[h.getX()][h.getY()] = h.getChar();
		}
	}
	//Removes Hero in the maze
	public void removeHero(Hero h){
		grid[h.getX()][h.getY()] = ' ';
	}
	//Places sword in the maze
	public void placeSword(Sword s) {
		grid[s.getX()][s.getY()] = 'E';
	}		
}

