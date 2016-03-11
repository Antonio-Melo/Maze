package test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import logic.*;

public class TestMazeWithStaticDragon {
	             //y 0     1    2    3    4   //x
	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},//0
					{'X', ' ', ' ', ' ', 'S'},//1
					{'X', ' ', 'X', ' ', 'X'},//2
					{'X', ' ', ' ', ' ', 'X'},//3
					{'X', 'X', 'X', 'X', 'X'}};//4
	//a)
	@Test
	public void testMoveHeroToFreeCell() {
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1,3,'H');
		myMaze.placeActor(myHero);
		Dragon myDragon = new Dragon(3,3,'D');
		myMaze.placeActor(myDragon);
		assertEquals(new Point(3, 1), myHero.getHeroPosition());
		
		//Moves to the left
		myMaze.removeActor(myHero);
		myHero.moveHero('e',myDragon,myMaze);
		myMaze.placeActor(myHero);
		assertEquals(new Point(2, 1), myHero.getHeroPosition());
		
		//Move to the right
		myMaze.removeActor(myHero);
		myHero.moveHero('d',myDragon,myMaze);
		myMaze.placeActor(myHero);
		assertEquals(new Point(3, 1), myHero.getHeroPosition());
	}
	//b)
	@Test
	public void testMoveHeroToWall() {
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1,3,'H');
		Dragon myDragon = new Dragon(3,3,'D');
		
		myMaze.placeActor(myHero);
		
		assertEquals(new Point(3, 1), myHero.getHeroPosition());
		myHero.moveHero('c',myDragon,myMaze);
		assertEquals(new Point(3, 1), myHero.getHeroPosition());
	}
	//c)
	@Test
	public void testMoveHeroPickSword(){
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1,3,'H');
		myMaze.placeActor(myHero);
		Dragon myDragon = new Dragon(3,3,'D');
		myMaze.placeActor(myDragon);
		Sword mySword = new Sword(3,1,'E');
		myMaze.placeActor(mySword);
		
		//Checks if has sword in the beginning
		assertEquals(false,myHero.hasSword());
		myMaze.removeActor(myHero);
		myHero.moveHero('e',myDragon,myMaze);
		myMaze.placeActor(myHero);
		myMaze.removeActor(myHero);
		myHero.moveHero('e',myDragon,myMaze);
		myMaze.placeActor(myHero);
		myMaze.removeActor(myHero);
		myHero.moveHero('b',myDragon,myMaze);
		myMaze.placeActor(myHero);
		myMaze.removeActor(myHero);
		myHero.moveHero('b',myDragon,myMaze);
		myMaze.placeActor(myHero);
		assertEquals(true,myHero.hasSword());
	}
	//d)
	@Test
	public void testDiesUnarmed(){
		Maze myMaze = new Maze(m1);
		Hero myHero = new Hero(1,3,'H');
		myMaze.placeActor(myHero);
		Dragon myDragon = new Dragon(3,3,'D');
		myMaze.placeActor(myDragon);
		//Checks if has sword in the beginning
		assertEquals(false,myHero.hasSword());
		myMaze.removeActor(myHero);
		myHero.moveHero('b',myDragon,myMaze);
		myMaze.placeActor(myHero);
		assertEquals(true,myHero.isDead());
	}

}
