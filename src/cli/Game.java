package cli;

import cli.Maze;

import java.util.Random;

import cli.Dragon;
import cli.Hero;
import cli.Sword;

public class Game {
	public Maze myMaze;
	public Hero myHero;
	public Sword sword;
	public Dragon myDragon;
	char type;
	
	//Constructor
	public Game(char type) {
		myMaze = new Maze();
		myHero = new Hero(1,1);
		sword = new Sword(8,1);
		myDragon = new Dragon(3, 1);
		this.type = type;
	}
	
	//Starts Game
	public void play(char m){
		
		
		myMaze.removeHero(myHero);
		myHero.moveHero(m,myDragon,myMaze);
		myMaze.placeHero(myHero);
		
		myMaze.removeDragon(myDragon);
		if(type == '1'){
			Random r = new Random();
			int moveDragon = r.nextInt() % 4 + 1;
			switch(moveDragon){
			case 1:
				myDragon.moveDragon('d',myHero,myMaze);
				break;
			case 2:
				myDragon.moveDragon('e',myHero,myMaze);
				break;
			case 3:
				myDragon.moveDragon('c',myHero,myMaze);
				break;
			case 4:
				myDragon.moveDragon('b',myHero,myMaze);
				break;
			}
		}
		myMaze.placeDragon(myDragon);
	}
}
