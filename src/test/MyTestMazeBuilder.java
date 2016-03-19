package test;

import logic.MazeBuilder;

public class MyTestMazeBuilder {

	public static void main(String[] args) {
		MazeBuilder m = new MazeBuilder();
		m.buildMaze(20);
		
		m.printMaze(m.getGrid());
	}

}
