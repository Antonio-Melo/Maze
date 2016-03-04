package logic;

import cli.Game;
import java.util.Scanner;

public class UserInterface {
	public static void main(String[] args){
		Game game = new Game();
		Scanner s = new Scanner(System.in);
		
		
		game.myMaze.placeDragon(game.myDragon);
		game.myMaze.placeHero(game.myHero);
		game.myMaze.placeSword(game.sword);
		game.myMaze.printMaze();

		while (!game.myHero.isDead() && !game.myHero.asEscaped()) {
			System.out.println("Direita - d Esquerda -e Cima -c Baixo-b");
			char m = s.next().charAt(0);
			boolean x = game.play(m);
			if (x == true) {
				game.myMaze.printMaze();
			}
		}
		if (game.myHero.asEscaped()) {
			System.out.println("HERO ESCAPED!!");
		}
		s.close();
	}
}
