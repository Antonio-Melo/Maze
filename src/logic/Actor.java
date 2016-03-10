package logic;

public class Actor {

	int x,y;
	char c;
	boolean dead;
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public char getC(){
		return c;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	
	public void isKilled(){
		dead=true;
	}
	
	public Actor(int x, int y, char c){
		this.x = x;
		this.y = y;
		this.c = c;
		this.dead = false;
	}
	
}
