package cli;

public class Dragon extends Actor {

	boolean asleep;

	//Constructor
	public Dragon(int x, int y){
		super(x,y);
		this.c = 'D';
	}

	public boolean isAsleep(){
		return asleep;
	}

	public void awake(){
		asleep = false;
	}

	public void fallAsleep(){
		asleep = true;
	}

}
