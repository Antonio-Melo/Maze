package logic;

public class Sword extends Actor {
	private boolean hidden;
	//Constructor
	public Sword(int x,int y, char c){
		super(x,y,c);
		hidden = false;
	}
	public void sethidden(boolean b){
		hidden = b;
	}
	public boolean isHidden(){
		return hidden;
	}
}