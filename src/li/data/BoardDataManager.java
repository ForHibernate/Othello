package li.data;

import java.util.LinkedList;

public class BoardDataManager {
	private  LinkedList<BoardData> boarddatamanager = new LinkedList<BoardData>();

	public void addLastData(BoardData boarddata){
		boarddatamanager.addLast(boarddata);
	}

	public void deleteLastData(){
		boarddatamanager.removeLast();
	}

	public BoardData getLastData(){
		return boarddatamanager.getLast();
	}

	public void resetManager(){
		boarddatamanager = new LinkedList<BoardData>();
	}

	public void addPass(){
		BoardData boarddata = boarddatamanager.getLast();
		boarddatamanager.addLast(boarddata);
	}

	public int getManagerSize(){
		return boarddatamanager.size();
	}
}
