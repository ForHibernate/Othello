package li.data;

import java.util.*;

public class InputData{
	private LinkedList<Integer[]> playerinput = new LinkedList<Integer[]>();
	
	public InputData(){
	}
	
	public void addPlayerInput(Integer[] input){
		playerinput.addLast(input);
	}
	
	public void deleteLastInput(){
		playerinput.removeLast();
	}
	
	public void resetInput(){
		playerinput = new LinkedList<Integer[]>();
	}
	
	public int getInputSize(){
		return playerinput.size();
	}
	
	public boolean isNextBlack(){
		if(playerinput.size() % 2 == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public Integer[] getLastInput(){
		return playerinput.getLast();
	}
	
}