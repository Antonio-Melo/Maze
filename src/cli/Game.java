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
	
	//Constructor
	public Game() {
		myMaze = new Maze();
		myHero = new Hero(1,1);
		sword = new Sword(8,1);
		myDragon = new Dragon(3, 1);
	}
	
	//Starts Game
	public boolean play(char m){
		return myHero.moveHero(m,myDragon,myMaze);
	}
}
