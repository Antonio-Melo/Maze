package logic;
/**
 * Interface
 * @author Ant�nio Melo Edgar Passos
 * @see  Maze MazeBuilder
 */
public interface IMazeBuilder {
	/**
	 * Interface to TestMazeBuilder
	 * @param size of Maze
	 * @return Maze builded
	 * @throws IllegalArgumentException Exception
	 */
	public char[][] buildMaze(int size) throws IllegalArgumentException;
}
