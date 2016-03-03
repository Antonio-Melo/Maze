package cli;
public class Maze {
	
	private Hero myHero;
	private Sword sword;
	private Dragon myDragon;
	char grid[][];

	public Maze(Hero h, Sword s, Dragon d) {
		
		this.myHero = h;
		this.sword = s;
		this.myDragon = d;
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

	public void printMaze() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void placeDragon(Dragon d) {
		grid[d.getX()][d.getY()] = 'D';
	}
	public void removeDragon(Dragon d){
		grid[d.getX()][d.getY()] = ' ';
	}

	public void placeHero(Hero h) {
		grid[h.getX()][h.getY()] = h.getChar();
	}
	
	public void removeHero(Hero h){
		grid[h.getX()][h.getY()] = ' ';
	}

	public void placeSword(Sword s) {
		grid[s.getX()][s.getY()] = 'E';
	}

	public static char getInput(){
		java.util.Scanner s = new java.util.Scanner(System.in);
		char c = s.next().charAt(0);
		return c;
	}

	public boolean move(char m) {
		int x=0, y=0;

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

		if (grid[myHero.getX()+x][myHero.getY() + y] == 'X') {
			return false;
		}
		if (grid[myHero.getX()+x][myHero.getY() + y] == 'S') {
			if (myDragon.isDead()) {
				removeHero(myHero);
				myHero.escape();
				return true;
			}
		}
		if(grid[myHero.getX()+x+x][myHero.getY() +y+y] == 'D'){
			if(myHero.hasSword()){
				myDragon.killDragon();
				removeDragon(myDragon);
				removeHero(myHero);
				myHero.moveHero(myHero.getX()+x, myHero.getY() + y);
				placeHero(myHero);
				return true;
			}else{
				removeHero(myHero);
				myHero.isKilled();
				return true;
			}
		}
		
		if (grid[myHero.getX()+x][myHero.getY() + y] == ' ') {
			removeHero(myHero);
			myHero.moveHero(myHero.getX()+x, myHero.getY() +y);
			placeHero(myHero);
			return true;
		}
		if (grid[myHero.getX()+x][myHero.getY() +y] == 'E') {
			removeHero(myHero);
			myHero.pickUpSword();
			myHero.moveHero(myHero.getX()+x, myHero.getY() + y);
			placeHero(myHero);
			return true;
		}
		return false;
	}



	public void game(){		

		//Inicial 
		placeDragon(myDragon);
		placeHero(myHero);
		placeSword(sword);
		printMaze();

		while(!myHero.isDead() && !myHero.asEscaped()){
			System.out.println("Direita - d Esquerda -e Cima -c Baixo-b");
			char m = getInput();
			boolean x =move(m);
			if(x == true){
				printMaze();
			}
		}
		if(myHero.asEscaped()){
			System.out.println("HERO ESCAPED!!");
		}

	}

	public static void main(String[] args) {
		Maze myMaze = new Maze(new Hero(1,1), new Sword(8,1), new Dragon(3, 1));
		myMaze.game();
	}


}

