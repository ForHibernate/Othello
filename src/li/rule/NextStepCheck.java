package li.rule;

import java.util.LinkedList;

import li.data.BoardData;

public class NextStepCheck{
	private final static int HORIZONTAL = 0;
	private final static int VERTICAL = 1;
	//top left to button right
	private final static int DIAGONAL1 = 2;
	//top right to button left
	private final static int DIAGONAL2 = 3;

	public static int isNextAvailable(BoardData boarddata, boolean isblacknext, int x, int y){
		int rev = 0;
		int size = boarddata.getSize();
		if((x >= size || x < 0) || (y >= size || y < 0)){
			return rev;
		}
		LinkedList<int[]> seqlist = getSeqList(boarddata, x, y);
		int[] indexlist = getIndexList(boarddata, x, y);

		for(int i = 0; i < 4; i++){
			if(isNextAvailableSeq(isblacknext, seqlist.get(i), indexlist[i])){
				rev++;
			}
		}

		return rev;
	}

	public static LinkedList<Integer[]> getAvailablePosition(BoardData boarddata, boolean isnextblack){
		LinkedList<Integer[]> next = new LinkedList<Integer[]>();
		int size = boarddata.getSize();
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(isNextAvailable(boarddata, isnextblack, i, j) > 0){
					Integer[] nextco = {i,j};
					next.addLast(nextco);
				}
			}
		}
		return next;
	}

	public static boolean isGameEnd(BoardData boarddata){
		int black = getAvailablePosition(boarddata,true).size();
		if(black != 0){
			return false;
		}
		int white = getAvailablePosition(boarddata,false).size();
		if(white != 0){
			return false;
		}
		return true;
	}

	public static LinkedList<int[]> getSeqList(BoardData boarddata, int x, int y){
		LinkedList<int[]> seqlist = new LinkedList<int[]>();
		seqlist.addLast(boarddata.getSeqHorizontal(x, y));
		seqlist.addLast(boarddata.getSeqVertical(x, y));
		seqlist.addLast(boarddata.getSeqDiagonal1(x, y));
		seqlist.addLast(boarddata.getSeqDiagonal2(x, y));
		return seqlist;
	}

	public static int[] getIndexList(BoardData boarddata, int x, int y){
		int[] indexlist = new int[4];
		int size = boarddata.getSize();
		indexlist[HORIZONTAL] = x;
		indexlist[VERTICAL] = y;
		indexlist[DIAGONAL1] = Math.min(x,y);
		indexlist[DIAGONAL2] = Math.min(size-1-x, y);
		return indexlist;
	}

	public static boolean isNextAvailableSeq(boolean isblacknext, int[] seq, int index){
		if(seq[index] != 0){
			return false;
		}
		int nextvalue = getNextValue(isblacknext);
		int[] extendseq = getExtendSeq(seq);
		if(extendseq[index] == -nextvalue){
			for(int i = index; i >=0; i--){
				if(extendseq[i] == nextvalue){
					return true;
				}else if(extendseq[i] == 0){
					return false;
				}
			}
		}
		if(extendseq[index+2] == -nextvalue){
			for(int i = index+2; i < extendseq.length; i++){
				if(extendseq[i] == nextvalue){
					return true;
				}else if(extendseq[i] == 0){
					return false;
				}
			}
		}
		return false;
	}

	public static int getNextValue(boolean isblacknext){
		if(isblacknext){
			return 1;
		}else{
			return -1;
		}
	}

	public static int[] getExtendSeq(int[] seq){
		int length = seq.length;
		int[] extendseq = new int[length+2];
		for(int i = 0; i < length; i++){
			extendseq[i+1] = seq[i];
		}
		extendseq[0] = 0;
		extendseq[length+1] = 0;
		return extendseq;
	}
}