package logic;

import java.awt.Point;
import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder{
	
	protected char grid[][];
	protected char visited[][];
	protected Stack<Point> s;
	
	
	//Constructor
	public MazeBuilder(int size){
		grid = new char [size][size];
		visited = new char [size][size];
		s = new Stack<Point>();
		
		//Fills grid with 'X's
		for(int i =0; i < size;i++){
			for(int j=0; j < size;j++){
				grid[i][j] = 'X';	
			}
		}
		//Fills the visited array with '1'(visited) or '0'(not visited) 
		for(int i =0; i < size;i++){
			//Fills the first and last line with '1'(visited)
			if(i == 0 || i == size -1){
				for(int j = 0;j < visited[i].length;j++){
					visited[i][j] = '1';
				}
			}
			//Fills the rest of the lines
			//First the first and last with '1'(visited)
			//and the rest with '0'(not visited)
			else{
				visited[i][0] = '1';
				visited[i][visited[i].length-1] ='1';
				for(int j = 1; j < visited[i].length-1;j++){
					visited[i][j] = '0';
				}
			}
		}
		
	}
	//Get function grid
	public char[][] getGrid(){
		return grid;
	}
	//Get function visited
	public char[][] getVisited(){
		return visited;
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
		Random r = new Random();
		//Random position for exit
		int start = r.nextInt(4) +1;
		Point exitpos = new Point();
		
		
		//Generates random exit in the borders
		switch(start){
			//First line
			case 1:
				exitpos.x =0;
				exitpos.y =r.nextInt(size-2) +1;
				break;
			//Last line
			case 2:
				exitpos.x =size-1;
				exitpos.y =r.nextInt(size-2) +1;
				break;
			//First column 
			case 3:
				exitpos.y = 0;
				exitpos.x =r.nextInt(size-2) +1;
				break;
			//Last column
			case 4:
				exitpos.y = size-1;
				exitpos.x = r.nextInt(size-2) +1;
			default:
				break;
		}
		
		//Creates exit in the grid
		grid[(int)exitpos.getX()][(int)exitpos.getY()]= 'S';
		//First position in the stack, next to the exit
		
		
		return null;
	}
}
