package cli;

public class Hero {
	
	int x, y;
	boolean sword;
	boolean dead;
	boolean escape;
	char c;
	
	public Hero(int x, int y){
		this.x = x;
		this.y = y;
		this.c = 'H';
		this.dead = false;
		this.escape = false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean hasSword(){
		return sword;
	}
	
	public void pickUpSword(){
		sword = true;
		c = 'A';
	}
	
	public char getChar(){
		return c;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void isKilled(){
		dead = true;
	}
	
	public boolean asEscaped(){
		return escape;
	}
	
	public void escape(){
		escape = true;
	}
	
	public void moveHero(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public static void main(String[] args) {
		

	}

}
