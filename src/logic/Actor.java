package logic;
import java.awt.Point;
/**
 * Class that represents a actor in the game
 * @author António Melo  Edgar Passos
 * @version 1.0
 * @see Hero Dragon Sword
 */

public class Actor {
	/**
	 *  Actor coordinates 
	 */
	protected int x,y;
	/**
	 *   Actor character
	 */
	protected char c;
	/**
	 * Boolean that shows if actor is dead
	 */
	protected boolean dead;
	
	/**
	 * @return x coordinate
	 */
	public int getX(){
		return x;
	}
	/**
	 * @return y coordinate
	 */
	public int getY(){
		return y;
	}
	/**
	 * @return actor's character 
	 */
	public char getC(){
		return c;
	}
	/**
	 * Sets the new x coordinate
	 * @param x coordinate
	 */
	public void setX(int x){
		this.x =x;
	}
	/**
	 * Sets the new y coordinate
	 * @param y coordinate
	 */
	public void setY(int y){
		this.y =y;
	}
	/**
	 * Returns Actor's coordinates in Point object
	 * @return Point 
	 */
	public Point getActorPosition(){
		return  (new Point(y, x));
	}
	/**
	 * Checks if the actor is dead
	 * @return dead
	 */
	public boolean isDead(){
		return dead;
	}
	/**
	 * Kills the actor
	 */
	public void isKilled(){
		dead=true;
	}
	/**
	 * Constructor
	 * Creates a Actor 
	 * @param x coordinate
	 * @param y coordinate
	 * @param c character
	 */
	public Actor(int x, int y, char c){
		this.x = x;
		this.y = y;
		this.c = c;
		this.dead = false;
	}
	
}
