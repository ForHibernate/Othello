package li.data;

public class ExtendStableBoardData extends BoardData {
	protected int size = 10;
	private final int STABLE = 1;
	private final int BOUND = 2;

	public ExtendStableBoardData(){
		exboarddata = new int[size][size];
		for(int i = 1; i < size - 2; i++){
			for(int j = 1; j < size - 2; j++){
				exboarddata[i][j] = NA;
			}
		}
		for(int i = 0; i <= size - 1; i++){
			exboarddata[i][0] = BOUND;
			exboarddata[0][i] = BOUND;
			exboarddata[i][size-1] = BOUND;
			exboarddata[size-1][i] = BOUND;
		}
	}

	public ExtendStableBoardData(int size){
		this.size = size;
		exboarddata = new int[size][size];
	}
	
	public void setStableDisc(int x, int y){
		exboarddata[x][y] = STABLE;
	}

	public BoardData trimToSize(){
		BoardData boarddataslim = new BoardData(size-2);
		for(int i = 0; i < size - 2; i++){
			for(int j=0; j < size - 2; j++){
				boarddataslim.setData(i, j, exboarddata[i+1][j+1]);
			}
		}
		return boarddataslim;
	}
}
