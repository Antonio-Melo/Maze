package test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import cli.*;

public class TestMazeWithStaticDragon {
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	@Test
	public void testMoveHeroToFreeCell() {
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1,3,'H');
		Dragon myDragon = new Dragon(3,3,'D');
		assertEquals(new Point(3, 1), myHero.getHeroPosition());
		myHero.moveHero('e',myDragon,myMaze);
		assertEquals(new Point(2, 1), myHero.getHeroPosition());
	}
	
	@Test
	public void testHeroDies() {
		//Maze maze = new Maze(m1);
		//assertEquals(MazeStatus.HeroUnarmed, maze.getStatus());
		//maze.moveHeroDown();
		//assertEquals(MazeStatus.HeroDied, maze.getStatus());
	}

}
