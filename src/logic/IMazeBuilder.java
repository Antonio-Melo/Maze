package logic;
/**
 * Interface
 * @author António Melo Edgar Passos
 * @see  Maze MazeBuilder
 */
public interface IMazeBuilder {
	/**
	 * Interface to TestMazeBuilder
	 * @param size
	 * @return
	 * @throws IllegalArgumentException
	 */
	public char[][] buildMaze(int size) throws IllegalArgumentException;
}
