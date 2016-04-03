package logic;
/**
 * Represents the sword in the maze
 * @author António Melo Edgar Passos
 * @see Actor Dragon
 */
public class Sword extends Actor {
	/**
	 * Creates a sword with a certain character in the Position(x,y)
	 * @param x coordinate
	 * @param y coordinate
	 * @param c character
	 */
	public Sword(int x,int y, char c){
		super(x,y,c);
	}
}