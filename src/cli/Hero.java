package cli;

public class Hero {
	
	int x, y;
	boolean sword;
	boolean dead;
	boolean escape;
	char c;
	//Constructor
	public Hero(int x, int y){
		this.x = x;
		this.y = y;
		this.c = 'H';
		this.dead = false;
		this.escape = false;
	}
	//Coord X
	public int getX(){
		return x;
	}
	//Coord Y
	public int getY(){
		return y;
	}
	//Checks if has Sword
	public boolean hasSword(){
		return sword;
	}
	//Picks sword 
	public void pickUpSword(){
		sword = true;
		c = 'A';
	}
	//Gets Hero char
	public char getChar(){
		return c;
	}
	//Checks is Hero is dead
	public boolean isDead(){
		return dead;
	}
	//When Dragon kills Hero
	public void isKilled(){
		dead = true;
	}
	//Checks if the Hero as already escaped the maze
	public boolean asEscaped(){
		return escape;
	}
	//When the hero finds the exit
	public void escape(){
		escape = true;
	}
	//Change coord of Hero
	public void cmoveHero(int x, int y){
		this.x = x;
		this.y = y;
	}
	//From input m,calculates if the Hero can me moved
	public void moveHero(char m,Dragon myDragon,Maze myMaze){
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
		
		//Wall
		if (myMaze.grid[getX()+x][getY() + y] == 'X') {
			return;
		}
		
		//Exit
		if (myMaze.grid[getX()+x][getY() + y] == 'S') {
			if (myDragon.isDead()) {
				escape();
				return;
			}else{
				return;
			}
		}
		//Fights Dragon
		if(myMaze.grid[getX()+x+x][getY() +y+y] == 'D'){
			if(hasSword()){
				myDragon.killDragon();
				myMaze.removeDragon(myDragon);
				cmoveHero(getX()+x, getY() + y);
				return;
			}else{
				isKilled();
				return;
			}
		}
		//Space clear
		if (myMaze.grid[getX()+x][getY() + y] == ' ') {
			cmoveHero(getX()+x, getY() +y);
			return;
		}
		//Picks up the sword
		if (myMaze.grid[getX()+x][getY() +y] == 'E') {
			pickUpSword();
			cmoveHero(getX()+x,getY() + y);
			return;
		}
	}
		
		
}


