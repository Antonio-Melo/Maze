package cli;

public class Dragon extends Actor {

	boolean dead;
	
	//Constructor
	public Dragon(int x, int y){
		super(x,y);
		this.c = 'D';
		this.dead = false;
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
