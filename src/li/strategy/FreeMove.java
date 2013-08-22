package li.strategy;

import java.util.LinkedList;

import li.data.BoardData;
import li.rule.NextStepCheck;

public class FreeMove {
	public static LinkedList<Integer[]> freemovelist = new LinkedList<Integer[]>();
	public static int getFreeMove(BoardData boarddata, boolean isnextblack){
		int freemove = 0;
		for(int x = 0; x < boarddata.getSize(); x++){
			for(int y = 0; y < boarddata.getSize(); y++){
				boolean is_player_avail = NextStepCheck.isNextAvailable(boarddata, isnextblack, x, y) > 0;
				boolean is_oppo_avail = NextStepCheck.isNextAvailable(boarddata, !isnextblack, x, y) > 0;
				if(is_player_avail && !is_oppo_avail){
					freemove++;
				}
			}
		}
		return freemove;
	}

	public static boolean isSameBoard(BoardData boarddata1,BoardData boarddata2){
		if(boarddata1.getSize() != boarddata2.getSize()){
			return false;
		}else{
			int size = boarddata1.getSize();
			for(int i = 0;  i < size; i++){
				for (int j = 0; j < size; j++){
					if(boarddata1.getData(i, j) != boarddata2.getData(i, j)){
						return false;
					}
				}
			}
		}
		return true;
	}





}
