package cli;

import java.util.Random;

public class Game {
	public Maze myMaze;
	public Hero myHero;
	public Sword mySword;
	public Dragon myDragon;
	char type;
	
	//Constructor
	public Game(char type) {
		myMaze = new Maze();
		myHero = new Hero(1,1,'H');
		mySword = new Sword(8,1,'E');
		myDragon = new Dragon(3,1,'D');
		this.type = type;
	}
	
	//Starts Game
	public void play(char m){
		
		boolean enableSleep;
		myMaze.removeActor(myHero);
		myHero.moveHero(m,myDragon,myMaze);
		myMaze.placeActor(myHero);
		
		myMaze.removeActor(myDragon);
		if(type == '1' || type == '2'){
			
			if(type == '1')
				enableSleep = false;
			else
				enableSleep = true;
			
			Random r = new Random();
			int moveDragon = r.nextInt() % 4 + 1;
			switch(moveDragon){
			case 1:
				myDragon.moveDragon('d',myHero,myMaze,enableSleep);
				break;
			case 2:
				myDragon.moveDragon('e',myHero,myMaze,enableSleep);
				break;
			case 3:
				myDragon.moveDragon('c',myHero,myMaze,enableSleep);
				break;
			case 4:
				myDragon.moveDragon('b',myHero,myMaze,enableSleep);
				break;
			}
		}

		myMaze.placeActor(myDragon);
		myMaze.placeActor(myHero);
	}
}
