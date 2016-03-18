package test;

import logic.MazeBuilder;

public class MyTestMazeBuilder {

	public static void main(String[] args) {
		MazeBuilder m = new MazeBuilder(6);
		m.buildMaze(6);
		
		m.printMaze(m.getGrid());
		System.out.println();
		m.printMaze(m.getVisited());
	}

}
