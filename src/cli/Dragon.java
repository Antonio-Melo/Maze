package cli;

public class Dragon {

	int x, y;
	boolean dead;
	
	//Constructor
	public Dragon(int x, int y){
		this.x = x;
		this.y = y;
		this.dead = false;
	}
	//Coord X
	public int getX(){
		return x;
	}
	//Coord Y
	public int getY(){
		return y;
	}
	//Checks if Dragon is dead
	public boolean isDead(){
		return dead;
	}
	//Kills Dragon
	public void killDragon(){
		dead = true;
	}

}
