package cli;

import java.util.Scanner;
import logic.*;


public class UserInterface {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		char type = '*';
		int size=0;
		int nDragons =0;
		char[][] grid;
		
		// MODE GAME
		while (type != '0' && type != '1' && type != '2') {
			System.out.println("Type of game:  0-Normal(Dragon still) 1-Move(Dragon moving) 2-Move + Sleep");
			type = s.next().charAt(0);
		}
		
		//DIMENSION
		while(size <4){
			System.out.println("Maze Dimension?");
			size = s.nextInt();
		}
		
		//NUMBER OF DRAGONS 
		while(nDragons <=0 || nDragons >size){
			System.out.println("Number of dragons?");
			nDragons = s.nextInt();
		}
		MazeBuilder mazeb = new MazeBuilder();
		grid = mazeb.buildMaze(size);
		
		Game game = new Game(type,grid, nDragons);
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
		//DIES
		if(game.getMyHero().isDead()){
			System.out.println("HERO DEAD!!");
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


