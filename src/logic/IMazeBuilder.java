package logic;
/**
 * Interface
 * @author António Melo Edgar Passos
 * @see  Maze MazeBuilder
 */
public interface IMazeBuilder {
	public char[][] buildMaze(int size) throws IllegalArgumentException;
}
