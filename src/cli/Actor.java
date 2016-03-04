package cli;

public class Actor {
	int x,y;
	char c;
	boolean dead;
	
	public Actor(int x, int y){
		this.x = x;
		this.y = y;
		dead = false;
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
	
}
