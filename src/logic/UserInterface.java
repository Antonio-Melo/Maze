package logic;

import cli.Game;
import java.util.Scanner;

public class UserInterface {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		char type = '*';
		
		//MODE GAME
		while(type != '0' && type != '1'){
			System.out.println("Type of game:  0-Normal(Dragon still) 1-Move(Dragon moving)");
			type = s.next().charAt(0);
		}
		
		//NEW GAME
		Game game = new Game(type);
		
		
		game.myMaze.placeDragon(game.myDragon);
		game.myMaze.placeHero(game.myHero);
		game.myMaze.placeSword(game.sword);
		System.out.print(game.myMaze.toString(game.myDragon, game.sword));
		
		//UNTIL WINS OR DIES
		while (!game.myHero.isDead() && !game.myHero.asEscaped()) {
			System.out.println("Direita - d Esquerda -e Cima -c Baixo-b");
			char m = s.next().charAt(0);
			game.play(m);
			System.out.print(game.myMaze.toString(game.myDragon, game.sword));
			
		}
		//WINS
		if (game.myHero.asEscaped()) {
			System.out.println("HERO ESCAPED!!");
		}
		s.close();
	}
}
