package test;
import static org.junit.Assert.*;
import logic.*;
import org.junit.Test;


public class TestMazeWithStaticDragon {

	
						//y=  0   1   2   3   4		x =
	char[][] testKill = { 	{'X','X','X','X','X'},		//0
							{'X','E',' ','D','S'},		//1	
							{'X','H','X',' ','X'},		//2	
							{'X',' ',' ',' ','X'},		//3	
							{'X','X','X','X','X'} };	//4
	
	
	
						//y=  0   1   2   3   4		x =
	char[][] test = { 		{'X','X','X','X','X'},		//0
							{'X','H',' ',' ','S'},		//1	
							{'X',' ','X','E','X'},		//2	
							{'X','D',' ',' ','X'},		//3	
							{'X','X','X','X','X'} };	//4
	
	
	@Test
	public void testKillDragon(){
		Maze maze = new Maze(testKill);
		Hero hero = new Hero(2,1,'H');
		Sword sword = new Sword(1,1,'E');
		Dragon dragon = new Dragon(1,3,'D');
		
		assertFalse(dragon.isDead());
		
		hero.moveHero('c', dragon, maze);
		hero.moveHero('d', dragon, maze);
		
		assertEquals(1,hero.getX());
		assertEquals(2,hero.getY());
		assertTrue(dragon.isDead());
	}
	
	
	@Test
	public void testEscape(){
		Maze maze = new Maze(testKill);
		Hero hero = new Hero(2,1,'H');
		Sword sword = new Sword(1,1,'E');
		Dragon dragon = new Dragon(1,3,'D');
		
		assertFalse(hero.hasEscaped());
		
		hero.moveHero('c', dragon, maze);
		hero.moveHero('d', dragon, maze);
		hero.moveHero('d', dragon, maze);
		hero.moveHero('d', dragon, maze);

		
		assertTrue(hero.hasEscaped());
	
	}
	
	@Test
	public void testEscapeNoSword(){
		Maze maze = new Maze(test);
		Hero hero = new Hero(1,1,'H');
		Sword sword = new Sword(2,3,'E');
		Dragon dragon = new Dragon(3,1,'D');
		
		assertFalse(hero.hasEscaped());
		
		hero.moveHero('d', dragon, maze);
		hero.moveHero('d', dragon, maze);
		hero.moveHero('d', dragon, maze);
		
		assertFalse(hero.hasEscaped());
		assertEquals(1,hero.getX());
		assertEquals(3,hero.getY());
		
	}
	
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
