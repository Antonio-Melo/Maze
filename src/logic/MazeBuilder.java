package logic;

import java.awt.Point;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class MazeBuilder implements IMazeBuilder{
	
	protected char grid[][];
	protected Stack<Point> s;
	protected int msize;
	
	
	//Constructor
	public MazeBuilder(){
		s = new Stack<Point>();
	}
	//Get function grid
	public char[][] getGrid(){
		return grid;
	}
	//Get's stack s
	public Stack<Point> getStack(){
		return s;
	}
	//Print´s maze(test function)
	public void printMaze(char [][] g){
		for(char [] l: g){
			for(char c : l){
				System.out.print(c);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
	
	//Generates the random Maze
	public char[][] buildMaze(int size) throws IllegalArgumentException {
		msize = size;
		grid = new char [size][size];
		boolean found = false;
		
		while (!found) {
			// Fills grid with 'X's
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					grid[i][j] = 'X';
				}
			}

			// ---------------------------------------------------------------
			Random r = new Random();
			// Random position for exit
			int exit = r.nextInt(4) + 1;
			Point exitpos = new Point();

			// Generates random exit in the borders
			switch (exit) {
			// First line
			case 1:
				exitpos.x = 0;
				exitpos.y = r.nextInt(size - 2) + 1;
				break;
			// Last line
			case 2:
				exitpos.x = size - 1;
				exitpos.y = r.nextInt(size - 2) + 1;
				break;
			// First column
			case 3:
				exitpos.y = 0;
				exitpos.x = r.nextInt(size - 2) + 1;
				break;
			// Last column
			case 4:
				exitpos.y = size - 1;
				exitpos.x = r.nextInt(size - 2) + 1;
			default:
				break;
			}

			// Creates exit in the grid
			grid[(int) exitpos.getX()][(int) exitpos.getY()] = 'S';

			// First position in the stack, next to the exit
			Point start = new Point();

			// Creating start

			// First line
			if (exitpos.getX() == 0) {
				start.x = 1;
				start.y = (int) exitpos.getY();
			}
			// Last line
			else if (exitpos.getX() == (size - 1)) {
				start.x = (int) exitpos.getX() - 1;
				start.y = (int) exitpos.getY();
			}
			// First Column
			else if (exitpos.getY() == 0) {
				start.x = (int) exitpos.getX();
				start.y = (int) exitpos.getY() + 1;
			}
			// Last Column
			else if (exitpos.getY() == (size - 1)) {
				start.x = (int) exitpos.getX();
				start.y = (int) exitpos.getY() - 1;
			}
			// Pushing start to stack
			s.push(start);
			// Changing start in the grid
			grid[(int) start.getX()][(int) start.getY()] = ' ';

			// Generates the random maze
			while (!s.empty()) {
				Point nextpos = getNextPos(s.peek());

				if (nextpos.x == -1 && nextpos.y == -1) {
					s.pop();
				} else {
					grid[nextpos.x][nextpos.y] = ' ';
					 //printMaze(grid);
					 //System.out.println();
					s.push(nextpos);
				}
			}

			// Checks for badwalls
			char[][] badWalls = { { 'X', 'X', 'X' }, { 'X', 'X', 'X' }, { 'X', 'X', 'X' } };
			boolean match=true;
			for (int i = 0; i < grid.length - badWalls.length; i++) {
				for (int j = 0; j < grid.length - badWalls.length; j++) {
					match = true;
					for (int y = 0; y < badWalls.length; y++){
						for (int x = 0; x < badWalls.length; x++) {
							if (grid[i + y][j + x] != badWalls[y][x])
								match = false;
						}
					}
					if (match)
						break;
				}
				if(match)
					break;
			}
			
			if(!match){
				found =true;
			}
		}
		
		//Game g = new Game('0', grid, 5);
		
		return grid;
	}
	
	
	public Point getNextPos(Point p){
		Random r = new Random();
		
		//Ads all options to the vector
		Vector<Point> op = new Vector<Point>();
		op.addElement(new Point((int)p.getX()+1,(int)p.getY()));//Down
		op.addElement(new Point((int)p.getX()-1,(int)p.getY()));//Up
		op.addElement(new Point((int)p.getX(),(int)p.getY()+1));//Right
		op.addElement(new Point((int)p.getX(),(int)p.getY()-1));//Left
		
		//Checks all the options
		for(int i=4; i >=1;i--){
			int next = r.nextInt(i);
			
			int nextx = op.elementAt(next).x;
			int nexty = op.elementAt(next).y;
			
			
			//Walls/spaces/exit
			if(grid[nextx][nexty] == ' ' || grid[nextx][nexty] =='S' ||
			   (nextx==0)||(nextx == msize-1) ||(nexty ==0) ||(nexty == msize-1)){
				op.removeElementAt(next);
				continue;
			}
			
			//Diagonals
			Point d1 = new Point();
			Point d2 = new Point();
			//Vertical
			if(nexty == (int)p.getY()){
				d1.x = nextx+(nextx -p.x);
				d1.y = nexty+1;
				d2.x = nextx+(nextx -p.x);
				d2.y = nexty-1;
				//System.out.println("Aqui 1!");
			}
			//Horizontal
			else if(nextx == (int)p.getX()){
				d1.x = nextx-1;
				d1.y = nexty+(nexty-p.y);
				d2.x = nextx+1;
				d2.y = nexty+(nexty-p.y);
				//System.out.println("Aqui 2!");
			}
			
			//Checks if there is 2x2 with spaces in the diagonal
			if(grid[d1.x][d1.y]== ' ' || grid[d2.x][d2.y] == ' '){
				op.removeElementAt(next);
				//System.out.println("Aqui 3!");
				continue;
			}
			
			//Checks if there is 2x2 white spaces
			if((grid[nextx][nexty+1] == ' ' && grid[nextx-1][nexty]== ' ' && grid[nextx-1][nexty+1]== ' ') ||
			   (grid[nextx][nexty-1] == ' ' && grid[nextx-1][nexty]== ' ' && grid[nextx-1][nexty-1]== ' ') ||
			   (grid[nextx][nexty+1] == ' ' && grid[nextx+1][nexty]== ' ' && grid[nextx+1][nexty+1]== ' ') ||
			   (grid[nextx][nexty-1] == ' ' && grid[nextx+1][nexty]== ' ' && grid[nextx+1][nexty-1]== ' ') ){
				//System.out.println("Aqui 4!");
				op.removeElementAt(next);
				continue;
			}
			
			return op.elementAt(next);
			
		}
		return new Point(-1,-1);
	}
}
