
public class Hero {
	
	int x, y;
	boolean sword;
	char c;
	
	public Hero(int x, int y){
		this.x = x;
		this.y = y;
		this.c = 'H';
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
	
	public void moveHero(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public static void main(String[] args) {
		

	}

}
