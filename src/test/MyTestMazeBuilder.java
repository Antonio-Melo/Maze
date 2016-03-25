package test;

import logic.*;

public class MyTestMazeBuilder {

	public static void main(String[] args) {
		MazeBuilder m = new MazeBuilder();

		// Place hero/dragons and sword
		Game g = new Game('0', m.buildMaze(200), 10);
		m.printMaze(m.getGrid());

	}

}