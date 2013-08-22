package li.output;
import java.util.LinkedList;

import li.data.BoardData;
import li.data.CopyBoardData;
import li.rule.NextStepCheck;

public class BoardOutput {
	public static void printBoard(BoardData board_data, boolean isblacknext, boolean hinton){
		String[][] output = null;
		if(!hinton){
			output = getBoardOutputData(board_data);
		}else{
			output = getBoardOutputData(BoardOutput.getHintBoardData(board_data, isblacknext));
		}
		int size = board_data.getSize();
		System.out.println("");
		for(int y = 0; y <= 2*size+1; y++){
			for(int x = 0; x < 2*size+1; x++){
				System.out.print(output[x][y]);
			}
			System.out.println(output[2*size+1][y]);
		}
	}

	private static String[][] getBoardOutputData(BoardData board_data){
		int size = board_data.getSize();
		String[][] board_output = new String[2*size+2][2*size+2];

		//draw axis;
		for(int x = 0; x <= size; x++){
			board_output[2*x][0] = String.valueOf(x) + " ";
			board_output[2*x+1][0] = "  ";
			board_output[0][2*x] = " " + String.valueOf(x);
			board_output[0][2*x+1] = "  ";
		}
		board_output[0][0] = "  ";

		//draw lines;
		for(int x = 1; x < size; x++){
			for(int y = 1; y < size; y++){
				board_output[2*x+1][2*y+1] = "┼";
			}
			board_output[2*x+1][1] = "┬";
			board_output[2*x+1][2*size+1] = "┴";
			board_output[1][2*x+1] = "├";
			board_output[2*size+1][2*x+1] = "┤";
		}
		for(int x = 0; x < size; x++){
			for(int y = 0; y <= size; y++){
				board_output[2*y+1][2*x+2] = "│";
				board_output[2*x+2][2*y+1] = "─";
			}
		}
		board_output[1][1] = "┌";
		board_output[1][2*size+1] = "└";
		board_output[2*size+1][1] = "┐";
		board_output[2*size+1][2*size+1] = "┘";

		//draw stones;
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				switch(board_data.getData(x,y)){
					case -1: board_output[2*x+2][2*y+2] = "●";
							 break;
					case 0:  board_output[2*x+2][2*y+2] = "  ";
							 break;
					case 1:  board_output[2*x+2][2*y+2] = "○";
							 break;
					case 2:  board_output[2*x+2][2*y+2] = "＊";
							 break;
				}
			}
		}
		return board_output;
	}

	public static BoardData getHintBoardData(BoardData boarddata, boolean isnextblack){
		BoardData boarddata2 = CopyBoardData.copyBoardData(boarddata);
		LinkedList<Integer[]> next = NextStepCheck.getAvailablePosition(boarddata2, isnextblack);
		for(int i = 0; i < next.size(); i++){
			Integer[] nextco = next.get(i);
			boarddata2.setData(nextco[0], nextco[1], 2);
		}
		return boarddata2;
	}
}