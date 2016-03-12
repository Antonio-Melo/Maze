package test;
import java.awt.Point;

import static org.junit.Assert.*;
import logic.*;
import org.junit.Test;

public class TestMazeWithStaticDragon {

	// y= 0 1 2 3 4 x =
	char[][] testKill = { { 'X', 'X', 'X', 'X', 'X' }, // 0
			{ 'X', 'E', ' ', 'D', 'S' }, // 1
			{ 'X', 'H', 'X', ' ', 'X' }, // 2
			{ 'X', ' ', ' ', ' ', 'X' }, // 3
			{ 'X', 'X', 'X', 'X', 'X' } }; // 4

	// y= 0 1 2 3 4 x =
	char[][] test = { { 'X', 'X', 'X', 'X', 'X' }, // 0
			{ 'X', 'H', ' ', ' ', 'S' }, // 1
			{ 'X', ' ', 'X', 'E', 'X' }, // 2
			{ 'X', 'D', ' ', ' ', 'X' }, // 3
			{ 'X', 'X', 'X', 'X', 'X' } }; // 4

	// y 0 1 2 3 4 //x
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, // 0
			{ 'X', ' ', ' ', ' ', 'S' }, // 1
			{ 'X', ' ', 'X', ' ', 'X' }, // 2
			{ 'X', ' ', ' ', ' ', 'X' }, // 3
			{ 'X', 'X', 'X', 'X', 'X' } };// 4
	// a)

	@Test
	public void testMoveHeroToFreeCell() {
		Game myGame = new Game('1');
		
		//Starts here
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1, 3, 'H');
		myMaze.placeActor(myHero);
		Dragon myDragon = new Dragon(3, 3, 'D');
		myMaze.placeActor(myDragon);
		assertEquals(new Point(3, 1), myHero.getHeroPosition());

		// Moves to the left
		myMaze.removeActor(myHero);
		myHero.moveHero('e', myDragon, myMaze);
		myMaze.placeActor(myHero);
		assertEquals(new Point(2, 1), myHero.getHeroPosition());

		// Move to the right
		myMaze.removeActor(myHero);
		myHero.moveHero('d', myDragon, myMaze);
		myMaze.placeActor(myHero);
		assertEquals(new Point(3, 1), myHero.getHeroPosition());
	}

	// b)
	@Test
	public void testMoveHeroToWall() {
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1, 3, 'H');
		Dragon myDragon = new Dragon(3, 3, 'D');

		myMaze.placeActor(myHero);

		assertEquals(new Point(3, 1), myHero.getHeroPosition());
		myHero.moveHero('c', myDragon, myMaze);
		assertEquals(new Point(3, 1), myHero.getHeroPosition());
	}

	// c)
	@Test
	public void testMoveHeroPickSword() {
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1, 3, 'H');
		myMaze.placeActor(myHero);
		Dragon myDragon = new Dragon(3, 3, 'D');
		myMaze.placeActor(myDragon);
		Sword mySword = new Sword(3, 1, 'E');
		myMaze.placeActor(mySword);

		// Checks if has sword in the beginning
		assertEquals(false, myHero.hasSword());
		myMaze.removeActor(myHero);
		myHero.moveHero('e', myDragon, myMaze);
		myMaze.placeActor(myHero);
		myMaze.removeActor(myHero);
		myHero.moveHero('e', myDragon, myMaze);
		myMaze.placeActor(myHero);
		myMaze.removeActor(myHero);
		myHero.moveHero('b', myDragon, myMaze);
		myMaze.placeActor(myHero);
		myMaze.removeActor(myHero);
		myHero.moveHero('b', myDragon, myMaze);
		myMaze.placeActor(myHero);
		assertEquals(true, myHero.hasSword());
	}

	// d)
	@Test
	public void testDiesUnarmed() {
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1, 3, 'H');
		myMaze.placeActor(myHero);
		Dragon myDragon = new Dragon(3, 3, 'D');
		myMaze.placeActor(myDragon);
		// Checks if has sword in the beginning
		assertEquals(false, myHero.hasSword());
		myMaze.removeActor(myHero);
		myHero.moveHero('b', myDragon, myMaze);
		myMaze.placeActor(myHero);
		assertEquals(true, myHero.isDead());
	}
	// e)
	@Test
	public void testKillDragon() {
		Maze maze = new Maze(testKill);
		Hero hero = new Hero(2, 1, 'H');
		Sword sword = new Sword(1, 1, 'E');
		Dragon dragon = new Dragon(1, 3, 'D');

		assertFalse(dragon.isDead());

		hero.moveHero('c', dragon, maze);
		hero.moveHero('d', dragon, maze);

		assertEquals(1, hero.getX());
		assertEquals(2, hero.getY());
		assertTrue(dragon.isDead());
	}
    // f)
	@Test
	public void testEscape() {
		Maze maze = new Maze(testKill);
		Hero hero = new Hero(2, 1, 'H');
		Sword sword = new Sword(1, 1, 'E');
		Dragon dragon = new Dragon(1, 3, 'D');

		assertFalse(hero.hasEscaped());

		hero.moveHero('c', dragon, maze);
		hero.moveHero('d', dragon, maze);
		hero.moveHero('d', dragon, maze);
		hero.moveHero('d', dragon, maze);

		assertTrue(hero.hasEscaped());

	}
	// g)
	@Test
	public void testEscapeNoSword() {
		Maze maze = new Maze(test);
		Hero hero = new Hero(1, 1, 'H');
		Sword sword = new Sword(2, 3, 'E');
		Dragon dragon = new Dragon(3, 1, 'D');

		assertFalse(hero.hasEscaped());

		hero.moveHero('d', dragon, maze);
		hero.moveHero('d', dragon, maze);
		hero.moveHero('d', dragon, maze);

		assertFalse(hero.hasEscaped());
		assertEquals(1, hero.getX());
		assertEquals(3, hero.getY());

	}
	// h)
	@Test
	public void testEscapeSwordDragonAlive(){
		Maze maze = new Maze(test);
		Hero hero = new Hero(1,1,'H');
		Sword sword = new Sword(2,3,'E');
		Dragon dragon = new Dragon(3,1,'D');
		
		assertFalse(hero.hasEscaped());
		
		hero.moveHero('d', dragon, maze);
		hero.moveHero('d', dragon, maze);
		hero.moveHero('b', dragon, maze);
		hero.moveHero('c', dragon, maze);
		hero.moveHero('d', dragon, maze);
		
		assertFalse(hero.hasEscaped());
		assertEquals(1,hero.getX());
		assertEquals(3,hero.getY());
		
	}

	
}
