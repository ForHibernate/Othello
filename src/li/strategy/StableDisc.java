package li.strategy;

import li.data.BoardData;
import li.data.ExtendStableBoardData;

public class StableDisc {
	//TODO
	static int boardsize = 8;
	protected final static int BLACK = 1;
	protected final static int WHITE = -1;
	private final static int STABLE = 1;

	public static int situation(BoardData boarddata){
		BoardData stableboard = getStableBoard(boarddata).trimToSize();
		int count = 0;
		int size = boarddata.getSize();
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(stableboard.getData(i, j) == STABLE){
					count += boarddata.getData(i, j);
				}
			}
		}
		return count;
	}

	public static int countStableDisc(BoardData boarddata, boolean isblack){
		BoardData stableboard = getStableBoard(boarddata).trimToSize();
		int count = 0;
		int size = boarddata.getSize();
		if(isblack){
			for(int i = 0; i < size; i++){
				for(int j = 0; j < size; j++){
					if(boarddata.getData(i, j) == BLACK && stableboard.getData(i, j) == STABLE){
						count++;
					}
				}
			}
		}else{
			for(int i = 0; i < size; i++){
				for(int j = 0; j < size; j++){
					if(boarddata.getData(i, j) == WHITE && stableboard.getData(i, j) == STABLE){
						count++;
					}
				}
			}
		}
		return count;
	}

	public static ExtendStableBoardData getStableBoard(BoardData boarddata){
		boolean isstable;
		int size = boarddata.getSize();
		ExtendStableBoardData exstableboard = new ExtendStableBoardData();
		for(int i = 0; i < 2*size - 1; i++){
			for(int j = 0; j < 2*size - 1; j++){
				int x = size - Math.abs(size - 1 - i);
				int y = size - Math.abs(size - 1 - j);
				isstable = isStable(boarddata, exstableboard, x-1, y-1);
				if(exstableboard.getData(x, y) != 2){
					if(isstable){
						exstableboard.setData(x, y, 1);
					}else{
						exstableboard.setData(x, y, 0);
					}
				}
				int a = Math.abs(size - 1 - i) + 1;
				int b = Math.abs(size - 1 - j) + 1;
				isstable = isStable(boarddata, exstableboard, a-1, b-1);
				if(exstableboard.getData(a, b) != 2){
					if(isstable){
						exstableboard.setData(a, b, 1);
					}else{
						exstableboard.setData(a, b, 0);
					}
				}
			}
		}
		return exstableboard;
	}

	public static boolean isStable(BoardData boarddata, ExtendStableBoardData exstableboard, int x, int y){
		if(boarddata.getData(x, y) == 0){
			return false;
		}
		if(exstableboard.getData(x+1, y+1) != 0){
			return true;
		}
		BoardData exboard = getExtendBoard(boarddata);
		boolean checkhor = isHorizontalStable(exboard, exstableboard, x, y);
		boolean checkver = isVerticalStable(exboard, exstableboard, x, y);
		boolean checkdia1 = isDiagonal1Stable(exboard, exstableboard, x, y);
		boolean checkdia2 = isDiagonal2Stable(exboard, exstableboard, x, y);
		if(checkhor && checkver && checkdia1 && checkdia2){
			return true;
		}
		return false;
	}

	private static boolean isHorizontalStable(BoardData exboard, ExtendStableBoardData exstableboard, int x, int y){
		if(exboard.getData(x+1, y+1) == 0){
			return false;
		}
		if(isSeqFull(exboard.getSeqHorizontal(x+1, y+1))){
			return true;
		}
		if(exboard.getData(x, y+1) == 2 || exboard.getData(x+2, y+1) == 2){
			return true;
		}else if(exstableboard.getData(x, y+1) == 1 && exboard.getData(x, y+1) == exboard.getData(x+1, y+1)){
			return true;
		}else if(exstableboard.getData(x+2, y+1) == 1 && exboard.getData(x+2, y+1) == exboard.getData(x+1, y+1)){
			return true;
		}
		return false;
	}

	private static boolean isVerticalStable(BoardData exboard, ExtendStableBoardData exstableboard, int x, int y){
		if(exboard.getData(x+1, y+1) == 0){
			return false;
		}
		if(isSeqFull(exboard.getSeqVertical(x+1, y+1))){
			return true;
		}
		if(exboard.getData(x+1, y) == 2 || exboard.getData(x+1, y+2) == 2){
			return true;
		}else if(exstableboard.getData(x+1, y) == 1 && exboard.getData(x+1, y) == exboard.getData(x+1, y+1)){
			return true;
		}else if(exstableboard.getData(x+1, y+2) == 1 && exboard.getData(x+1, y+2) == exboard.getData(x+1, y+1)){
			return true;
		}
		return false;
	}

	private static boolean isDiagonal1Stable(BoardData exboard, ExtendStableBoardData exstableboard, int x, int y){
		if(exboard.getData(x+1, y+1) == 0){
			return false;
		}
		if(isSeqFull(exboard.getSeqDiagonal1(x+1, y+1))){
			return true;
		}
		if(exboard.getData(x, y) == 2 || exboard.getData(x+2, y+2) == 2){
			return true;
		}else if(exstableboard.getData(x, y) == 1 && exboard.getData(x, y) == exboard.getData(x+1, y+1)){
			return true;
		}else if(exstableboard.getData(x+2, y+2) == 1 && exboard.getData(x+2, y+2) == exboard.getData(x+1, y+1)){
			return true;
		}
		return false;
	}

	private static boolean isDiagonal2Stable(BoardData exboard, ExtendStableBoardData exstableboard, int x, int y){
		if(exboard.getData(x+1, y+1) == 0){
			return false;
		}
		if(isSeqFull(exboard.getSeqDiagonal2(x+1, y+1))){
			return true;
		}
		if(exboard.getData(x, y+2) == 2 || exboard.getData(x+2, y) == 2){
			return true;
		}else if(exstableboard.getData(x, y+2) == 1 && exboard.getData(x, y+2) == exboard.getData(x+1, y+1)){
			return true;
		}else if(exstableboard.getData(x+2, y) == 1 && exboard.getData(x+2, y) == exboard.getData(x+1, y+1)){
			return true;
		}
		return false;
	}

	private static boolean isSeqFull(int[] seq){
		for(int i = 0; i < seq.length; i++){
			if(seq[i] == 0){
				return false;
			}
		}
		return true;
	}

	private static BoardData getExtendBoard(BoardData boarddata){
		int size = boarddata.getSize();
		BoardData exboarddata = new BoardData(size + 2);
		for(int i = 1; i <= size; i++){
			for(int j = 1; j <= size; j++){
				exboarddata.setData(i, j, boarddata.getData(i-1, j-1));
			}
		}
		for(int i = 0; i <= size+1; i++){
			exboarddata.setData(i, 0, 2);
			exboarddata.setData(0, i, 2);
			exboarddata.setData(i, size+1, 2);
			exboarddata.setData(size+1, i, 2);
		}
		return exboarddata;
	}
}