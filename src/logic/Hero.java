
package logic;

public class Hero extends Actor {
	
	
	boolean escape, sword;
	
	
	//Constructor
	public Hero(int x, int y, char c){
		super(x,y,c);
		this.escape = false;
		this.sword = false;
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
	//Checks if the Hero as already escaped the maze
	public boolean hasEscaped(){
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
		if(myMaze.grid[getX()+x+1][getY()+y] == 'D' || myMaze.grid[getX()+x-1][getY()+y] == 'D' || 
				myMaze.grid[getX()+x][getY()+y+1] == 'D' || myMaze.grid[getX()+x][getY()+y-1] == 'D'){
			if(hasSword()){
				myDragon.isKilled();
				cmoveHero(getX()+x, getY() + y);
				return;
			}else{
				isKilled();
				return;
			}
		}
		
		//Picks up the sword
		if (myMaze.grid[getX()+x][getY()+y] == 'E') {
			pickUpSword();
			cmoveHero(getX()+x,getY() + y);
			return;
		}
		
		
		//Space is clear
		else{
		cmoveHero(getX()+x, getY() +y);
		return;
		}
	}
		
		
}

