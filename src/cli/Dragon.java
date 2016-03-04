package cli;

public class Dragon extends Actor {

	boolean dead, asleep;

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

	public boolean isAsleep(){
		return asleep;
	}

	public void awake(){
		asleep = false;
	}

	public void fallAsleep(){
		asleep = true;
	}

}
