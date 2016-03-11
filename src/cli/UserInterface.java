package cli;

import java.util.Scanner;
import logic.*;


public class UserInterface {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		char type = '*';
		
		//MODE GAME
		while(type != '0' && type != '1' && type != '2'){
			System.out.println("Type of game:  0-Normal(Dragon still) 1-Move(Dragon moving) 2-Move + Sleep");
			type = s.next().charAt(0);
		}
		
		//NEW GAME
		Game game = new Game(type);
		
		
		game.myMaze.placeActor(game.myDragon);
		game.myMaze.placeActor(game.myHero);
		game.myMaze.placeActor(game.mySword);
		System.out.print(game.myMaze.toString(game.myDragon, game.mySword));
		
		//UNTIL WINS OR DIES
		while (!game.myHero.isDead() && !game.myHero.hasEscaped()) {
			System.out.println("Direita - d Esquerda -e Cima -c Baixo-b");
			char m = s.next().charAt(0);
			game.play(m);
			System.out.print(game.myMaze.toString(game.myDragon, game.mySword));
			
		}
		//WINS
		if (game.myHero.hasEscaped()) {
			game.myMaze.removeActor(game.myHero);
			System.out.print(game.myMaze.toString(game.myDragon, game.mySword));
			System.out.println("HERO ESCAPED!!");
		}
		s.close();
	}
}


