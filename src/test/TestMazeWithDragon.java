package test;

import java.util.Random;

import org.junit.Test;
import logic.*;

public class TestMazeWithDragon {

	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', ' ', 'S' }, { 'X', ' ', 'D', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X' } };

	@Test(timeout = 1000)
	public void testDragonMoves() {
		Game myGame = new Game('1', m1, 1);
		
		myGame.getDragons().get(0).setX(2);
		myGame.getDragons().get(0).setY(2);

		boolean move_left = false;
		boolean move_right = false;
		boolean move_down = false;
		boolean move_up = false;

		while (!move_left || !move_right || !move_down || !move_up) {
			int tmp_x = myGame.getDragons().get(0).getX();
			int tmp_y = myGame.getDragons().get(0).getY();

			Random r = new Random();
			int moveDragon = r.nextInt() % 4 + 1;
			switch (moveDragon) {
			case 1:
				myGame.getDragons().get(0).moveDragon('d', myGame.getMyHero(), myGame.getMyMaze(), false);
				break;
			case 2:
				myGame.getDragons().get(0).moveDragon('e', myGame.getMyHero(), myGame.getMyMaze(), false);
				break;
			case 3:
				myGame.getDragons().get(0).moveDragon('c', myGame.getMyHero(), myGame.getMyMaze(), false);
				break;
			case 4:
				myGame.getDragons().get(0).moveDragon('b', myGame.getMyHero(), myGame.getMyMaze(), false);
				break;
			}

			if (myGame.getDragons().get(0).getX() - tmp_x == 1) {
				move_right = true;
			} else if (myGame.getDragons().get(0).getX() - tmp_x == -1) {
				move_left = true;
			} else if (myGame.getDragons().get(0).getY() - tmp_y == 1) {
				move_down = true;
			} else if (myGame.getDragons().get(0).getY() - tmp_y == -1) {
				move_up = true;
			}
		}
	}

	@Test(timeout = 1000)
	public void testDragonSleep() {
		Game myGame = new Game('2', m1, 1);
		myGame.getDragons().get(0).setX(2);
		myGame.getDragons().get(0).setY(2);

		boolean sleeping = false;
		boolean awake = false;

		while (!sleeping || !awake) {
			Random r = new Random();
			int moveDragon = r.nextInt() % 4 + 1;

			switch (moveDragon) {
			case 1:
				myGame.getDragons().get(0).moveDragon('d', myGame.getMyHero(), myGame.getMyMaze(), true);
				break;
			case 2:
				myGame.getDragons().get(0).moveDragon('e', myGame.getMyHero(), myGame.getMyMaze(), true);
				break;
			case 3:
				myGame.getDragons().get(0).moveDragon('c', myGame.getMyHero(), myGame.getMyMaze(), true);
				break;
			case 4:
				myGame.getDragons().get(0).moveDragon('b', myGame.getMyHero(), myGame.getMyMaze(), true);
				break;
			}
			
			if (!myGame.getDragons().get(0).isAsleep()) {
				awake = true;
			}

			if (myGame.getDragons().get(0).isAsleep()) {
				sleeping = true;
			}
		
		
		}
	}

}