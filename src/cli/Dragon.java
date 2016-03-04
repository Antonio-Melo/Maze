package cli;

public class Dragon {

	int x, y;
	boolean dead;
	
	public Dragon(int x, int y){
		this.x = x;
		this.y = y;
		this.dead = false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isDead(){
		return dead;
	}
	public void killDragon(){
		dead = true;
	}

}
