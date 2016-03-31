package test;
import java.awt.Point;

import static org.junit.Assert.*;

import java.util.ArrayList;

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

					// y 0 1 2 3 4 				 //x
	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, // 0
					{ 'X', ' ', ' ', ' ', 'S' }, // 1
					{ 'X', ' ', 'X', ' ', 'X' }, // 2
					{ 'X', ' ', ' ', ' ', 'X' }, // 3
					{ 'X', 'X', 'X', 'X', 'X' } };// 4
	// a)

	@Test
	
	public void testMoveHeroToFreeCell() {

		
		//Starts here
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1, 3, 'H');
		myMaze.placeActor(myHero);
		Dragon myDragon = new Dragon(3, 3, 'D');
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(myDragon);
		myMaze.placeActor(myDragon);
		assertEquals(new Point(3, 1), myHero.getActorPosition());

		// Moves to the left
		myMaze.removeActor(myHero);
		myHero.moveHero('e', dragons, myMaze);
		myMaze.placeActor(myHero);
		assertEquals(new Point(2, 1), myHero.getActorPosition());

		// Move to the right
		myMaze.removeActor(myHero);
		myHero.moveHero('d', dragons, myMaze);
		myMaze.placeActor(myHero);
		assertEquals(new Point(3, 1), myHero.getActorPosition());
	}

	// b)
	@Test
	public void testMoveHeroToWall() {
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1, 3, 'H');
		Dragon myDragon = new Dragon(3, 3, 'D');
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(myDragon);

		myMaze.placeActor(myHero);

		assertEquals(new Point(3, 1), myHero.getActorPosition());
		myHero.moveHero('c', dragons, myMaze);
		assertEquals(new Point(3, 1), myHero.getActorPosition());
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
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(myDragon);

		// Checks if has sword in the beginning
		assertEquals(false, myHero.hasSword());
		myMaze.removeActor(myHero);
		myHero.moveHero('e', dragons, myMaze);
		myMaze.placeActor(myHero);
		myMaze.removeActor(myHero);
		myHero.moveHero('e', dragons, myMaze);
		myMaze.placeActor(myHero);
		myMaze.removeActor(myHero);
		myHero.moveHero('b', dragons, myMaze);
		myMaze.placeActor(myHero);
		myMaze.removeActor(myHero);
		myHero.moveHero('b', dragons, myMaze);
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
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(myDragon);
		// Checks if has sword in the beginning
		assertEquals(false, myHero.hasSword());
		myMaze.removeActor(myHero);
		myHero.moveHero('b', dragons, myMaze);
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
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(dragon);

		assertFalse(dragon.isDead());
		
		hero.moveHero('c', dragons, maze);
		hero.moveHero('d', dragons, maze);
		
		assertEquals(1,hero.getX());
		assertEquals(2,hero.getY());
		assertTrue(dragon.isDead());
	}
    // f)
	@Test
	public void testEscape() {
		Maze maze = new Maze(testKill);

		Hero hero = new Hero(2,1,'H');
		Sword sword = new Sword(1,1,'E');
		Dragon dragon = new Dragon(1,3,'D');
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(dragon);
		
		assertFalse(hero.hasEscaped());
		
		hero.moveHero('c', dragons, maze);
		hero.moveHero('d', dragons, maze);
		hero.moveHero('d', dragons, maze);
		hero.moveHero('d', dragons, maze);

		assertTrue(hero.hasEscaped());

	}
	// g)
	@Test
	public void testEscapeNoSword() {
		Maze maze = new Maze(test);

		Hero hero = new Hero(1,1,'H');
		Sword sword = new Sword(2,3,'E');
		Dragon dragon = new Dragon(3,1,'D');
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(dragon);
		
		assertFalse(hero.hasEscaped());
		
		hero.moveHero('d', dragons, maze);
		hero.moveHero('d', dragons, maze);
		hero.moveHero('d', dragons, maze);

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
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(dragon);
		
		assertFalse(hero.hasEscaped());
		
		hero.moveHero('d', dragons, maze);
		hero.moveHero('d', dragons, maze);
		hero.moveHero('b', dragons, maze);
		hero.moveHero('c', dragons, maze);
		hero.moveHero('d', dragons, maze);
		
		assertFalse(hero.hasEscaped());
		assertEquals(1,hero.getX());
		assertEquals(3,hero.getY());
		
	}
	
	@Test
	public void testDragonSwordChar(){
		Maze maze= new Maze(test);
		Hero hero = new Hero(1,1,'H');
		Sword sword = new Sword(2,3,'E');
		Dragon dragon = new Dragon(2,3,'D');
		ArrayList<Dragon> dragons = new ArrayList<Dragon>();
		dragons.add(dragon);
		
		maze.toString(dragons, sword);
		assertEquals('F',maze.getGrid()[2][3]);
	}

	
}
