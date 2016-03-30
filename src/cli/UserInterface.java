package cli;

import java.util.Scanner;
import logic.*;


public class UserInterface {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		char type = '*';

		// MODE GAME
		while (type != '0' && type != '1' && type != '2') {
			System.out.println("Type of game:  0-Normal(Dragon still) 1-Move(Dragon moving) 2-Move + Sleep");
			type = s.next().charAt(0);
		}

		// NEW GAME
		System.out.println("Number of dragons?");
		int nDragons = s.nextInt();
		Game game = new Game(type, nDragons);

		for (int i = 0; i < game.getDragons().size(); i++)
			if (!game.getDragons().get(i).isDead())
				game.getMyMaze().placeActor(game.getDragons().get(i));
		game.getMyMaze().placeActor(game.getMyHero());
		game.getMyMaze().placeActor(game.getMySword());

		System.out.print(game.getMyMaze().toString(game.getDragons(), game.getMySword()));

		// UNTIL WINS OR DIES
		while (!game.getMyHero().isDead() && !game.getMyHero().hasEscaped()) {
			System.out.println("Right -r Left -l Up -u Down -d");
			char m = s.next().charAt(0);
			game.play(m);
			System.out.print(game.getMyMaze().toString(game.getDragons(), game.getMySword()));

		}
		// WINS
		if (game.getMyHero().hasEscaped()) {
			game.getMyMaze().removeActor(game.getMyHero());
			System.out.print(game.getMyMaze().toString(game.getDragons(), game.getMySword()));
			System.out.println("HERO ESCAPED!!");
		}
		s.close();
	}
}


