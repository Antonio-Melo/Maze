package logic;
import java.awt.Point;


public class Actor {

	protected int x,y;
	protected char c;
	protected boolean dead;
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public char getC(){
		return c;
	}
	public void setX(int x){
		this.x =x;
	}
	public void setY(int y){
		this.y =y;
	}
	public Point getHeroPosition(){
		return  (new Point(y, x));
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
