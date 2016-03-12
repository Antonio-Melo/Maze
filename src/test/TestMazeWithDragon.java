package test;

import java.util.Random;

import org.junit.Test;
import logic.*;

public class TestMazeWithDragon {

	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', ' ', 'S' }, { 'X', ' ', 'D', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X' } };

	@Test(timeout = 1000)
	public void testDragonMoves() {
		Game myGame = new Game('1', m1);
		myGame.myDragon.setX(2);
		myGame.myDragon.setY(2);

		boolean move_left = false;
		boolean move_right = false;
		boolean move_down = false;
		boolean move_up = false;

		while (!move_left || !move_right || !move_down || !move_up) {
			int tmp_x = myGame.myDragon.getX();
			int tmp_y = myGame.myDragon.getY();

			Random r = new Random();
			int moveDragon = r.nextInt() % 4 + 1;
			switch (moveDragon) {
			case 1:
				myGame.myDragon.moveDragon('d', myGame.myHero, myGame.myMaze, false);
				break;
			case 2:
				myGame.myDragon.moveDragon('e', myGame.myHero, myGame.myMaze, false);
				break;
			case 3:
				myGame.myDragon.moveDragon('c', myGame.myHero, myGame.myMaze, false);
				break;
			case 4:
				myGame.myDragon.moveDragon('b', myGame.myHero, myGame.myMaze, false);
				break;
			}

			if (myGame.myDragon.getX() - tmp_x == 1) {
				move_right = true;
			} else if (myGame.myDragon.getX() - tmp_x == -1) {
				move_left = true;
			} else if (myGame.myDragon.getY() - tmp_y == 1) {
				move_down = true;
			} else if (myGame.myDragon.getY() - tmp_y == -1) {
				move_up = true;
			}
		}
	}

	@Test(timeout = 1000)
	public void testDragonSleep() {
		Game myGame = new Game('2', m1);
		myGame.myDragon.setX(2);
		myGame.myDragon.setY(2);

		boolean sleeping = false;
		boolean awake = false;

		while (!sleeping || !awake) {
			Random r = new Random();
			int moveDragon = r.nextInt() % 4 + 1;

			switch (moveDragon) {
			case 1:
				myGame.myDragon.moveDragon('d', myGame.myHero, myGame.myMaze, true);
				break;
			case 2:
				myGame.myDragon.moveDragon('e', myGame.myHero, myGame.myMaze, true);
				break;
			case 3:
				myGame.myDragon.moveDragon('c', myGame.myHero, myGame.myMaze, true);
				break;
			case 4:
				myGame.myDragon.moveDragon('b', myGame.myHero, myGame.myMaze, true);
				break;
			}
			
			if (!myGame.myDragon.isAsleep()) {
				awake = true;
			}

			if (myGame.myDragon.isAsleep()) {
				sleeping = true;
			}
		
		
		}
	}

}