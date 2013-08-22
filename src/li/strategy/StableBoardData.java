package li.strategy;

import li.data.BoardData;

public class StableBoardData extends BoardData{
	protected int size = 10;
	protected final int STABLE = 1;
	protected int[][] boarddata = new int[size][size];

	public StableBoardData(){
		for(int i = 1; i < size-1; i++){
			for(int j = 1; j < size-1; j++){
				boarddata[i][j] = NA;
			}
		}
		for(int i = 0; i < size; i++){
			boarddata[i][0] = STABLE;
			boarddata[0][i] = STABLE;
			boarddata[i][size-1] = STABLE;
			boarddata[size-1][i] = STABLE;
		}
	}

	public StableBoardData(int size) {
		this.size = size;
	}

	public BoardData trimToSize(){
		BoardData boarddata2 = new BoardData(size-2);
		for(int i = 0; i < size-2; i++){
			for(int j = 0; j < size-2; j++){
				boarddata2.setData(i, j, boarddata[i+1][j+1]);
			}
		}
		return boarddata2;
	}

}
