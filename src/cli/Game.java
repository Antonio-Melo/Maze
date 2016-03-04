package cli;

import cli.Maze;
import cli.Dragon;
import cli.Hero;
import cli.Sword;

public class Game {
	public Maze myMaze;
	public Hero myHero;
	public Sword sword;
	public Dragon myDragon;
	
	public Game() {
		myMaze = new Maze();
		myHero = new Hero(1,1);
		sword = new Sword(8,1);
		myDragon = new Dragon(3, 1);
	}
	
	
	public boolean play(char m){
		return myMaze.move(m, myHero, myDragon);
	}
}
