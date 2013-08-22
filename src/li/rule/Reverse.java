package li.rule;

import li.data.BoardData;

public class Reverse {
	final static int HORIZONTAL = 0;
	final static int VERTICAL = 1;
	//top left to button right
	final static int DIAGONAL1 = 2;
	//top right to button left
	final static int DIAGONAL2 = 3;

	public static BoardData reverse(BoardData boarddata, boolean isblack, int x, int y){
		int[] indexlist = NextStepCheck.getIndexList(boarddata, x, y);
		int[] horizontal = reverseSeq(isblack, boarddata.getSeqHorizontal(x, y), indexlist[HORIZONTAL]);
		int[] vertical = reverseSeq(isblack, boarddata.getSeqVertical(x, y), indexlist[VERTICAL]);
		int[] diagonal1 = reverseSeq(isblack, boarddata.getSeqDiagonal1(x, y), indexlist[DIAGONAL1]);
		int[] diagonal2 = reverseSeq(isblack, boarddata.getSeqDiagonal2(x, y), indexlist[DIAGONAL2]);
		boarddata.setSeqHorizontal(horizontal, x, y);
		boarddata.setSeqVertical(vertical, x, y);
		boarddata.setSeqDiagonal1(diagonal1, x, y);
		boarddata.setSeqDiagonal2(diagonal2, x, y);
		return boarddata;
	}

	public static int[] reverseSeq(boolean isblacknext, int[]seq, int index){
		int nextvalue = NextStepCheck.getNextValue(isblacknext);
		int[] extendseq = NextStepCheck.getExtendSeq(seq);
		extendseq[index+1] = nextvalue;
		if(extendseq[index] == -nextvalue){
			for(int i = index; i >= 0; i--){
				if(extendseq[i] == nextvalue){
					for(int j = index; j > i; j--){
						extendseq[j] = nextvalue;
					}
					break;
				}else if(extendseq[i] == 0){
					break;
				}
			}
		}
		if(extendseq[index+2] == -nextvalue){
			for(int i = index+2; i < extendseq.length; i++){
				if(extendseq[i] == nextvalue){
					for(int j = index+2; j < i; j++){
						extendseq[j] = nextvalue;
					}
					break;
				}else if(extendseq[i] == 0){
					break;
				}
			}
		}

		for(int i = 0; i < seq.length; i++){
			seq[i] = extendseq[i+1];
		}
		return seq;
	}
}
