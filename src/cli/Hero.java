package cli;

public class Hero extends Actor {
	
	boolean sword;
	boolean escape;S
	
	//Constructor
	public Hero(int x, int y){
		super(x,y);
		this.c = 'H';
		this.escape = false;
	}
	
	//Checks if has Sword
	public boolean hasSword(){
		return sword;
	}
	//Picks up sword 
	public void pickUpSword(){
		sword = true;
		c = 'A';
	}
	//Gets Hero char
	public char getChar(){
		return c;
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
	public boolean moveHero(char m,Dragon myDragon,Maze myMaze){
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
			return false;
		}
		
		//Exit
		if (myMaze.grid[getX()+x][getY() + y] == 'S') {
			if (myDragon.isDead()) {
				myMaze.removeActor(this);
				escape();
				return true;
			}
			
			return false;
		}
		//Fights Dragon
		if(myMaze.grid[getX()+x+x][getY() +y+y] == 'D'){
			if(hasSword()){
				myDragon.killDragon();
				myMaze.removeActor(myDragon);
				myMaze.removeActor(this);
				cmoveHero(getX()+x, getY() + y);
				myMaze.placeActor(this);
				return true;
			}else{
				myMaze.removeActor(this);
				isKilled();
				return true;
			}
		}
		//Space clear
		if (myMaze.grid[getX()+x][getY() + y] == ' ') {
			myMaze.removeActor(this);
			cmoveHero(getX()+x, getY() +y);
			myMaze.placeActor(this);
			return true;
		}
		//Picks up the sword
		if (myMaze.grid[getX()+x][getY() +y] == 'E') {
			myMaze.removeActor(this);
			pickUpSword();
			cmoveHero(getX()+x,getY() + y);
			myMaze.placeActor(this);
			return true;
		}
		//Default
		return false;
	}
		
		
}


