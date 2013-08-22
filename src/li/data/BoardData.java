package li.data;

public class BoardData {
	protected int size = 8;
	protected final int BLACK = 1;
	protected final int WHITE = -1;
	protected final int NA = 0;
	protected int[][] exboarddata;

	public BoardData(){
		exboarddata = new int[size][size];
		//盤面が基本的に石なし
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				exboarddata[i][j] = NA;
			}
		}
		//中心部の４個の石
		exboarddata[size/2-1][size/2-1] = WHITE;
		exboarddata[size/2][size/2] = WHITE;
		exboarddata[size/2-1][size/2] = BLACK;
		exboarddata[size/2][size/2-1] = BLACK;
	}

	public BoardData(int size){
		this.size = size;
		exboarddata = new int[size][size];
	}

	public void setData(int x, int y, int data){
		exboarddata[x][y] = data;
	}

	public int getData(int x, int y){
		return exboarddata[x][y];
	}

	public int getSize(){
		return size;
	}

	public int[] getSeqHorizontal(int x, int y){
		int[] horizontal = new int[size];
		for(int i = 0; i < size; i++){
			horizontal[i] = exboarddata[i][y];
		}
		return horizontal;
	}

	public void setSeqHorizontal(int[] horizontal, int x, int y){
		for(int i = 0; i < size; i++){
			exboarddata[i][y] = horizontal[i];
		}
	}

	public int[] getSeqVertical(int x, int y){
		int[] vertical = new int[size];
		for(int i = 0; i < size; i++){
			vertical[i] = exboarddata[x][i];
		}
		return vertical;
	}

	public void setSeqVertical(int[] vertical, int x, int y){
		for(int i = 0; i < size; i++){
			exboarddata[x][i] = vertical[i];
		}
	}

	//top left to button right
	public int[] getSeqDiagonal1(int x, int y){
		int sized1 = size - Math.abs(x-y);
		int[] diagonal1 = new int[sized1];
		for(int i = 0; i < sized1; i++){
			if((x-y) >= 0){
				diagonal1[i] = exboarddata[x-y+i][i];
			}else{
				diagonal1[i] = exboarddata[i][y-x+i];
			}
		}
		return diagonal1;
	}

	public void setSeqDiagonal1(int[] diagonal1, int x, int y){
		int sized1 = size - Math.abs(x-y);
		for(int i = 0; i < sized1; i++){
			if((x-y) >= 0){
				exboarddata[x-y+i][i] = diagonal1[i];
			}else{
				exboarddata[i][y-x+i] = diagonal1[i];
			}
		}
	}

	//top right to button left
	public int[] getSeqDiagonal2(int x, int y){
		int sized2 = size - Math.abs(size-1-x-y);
		int[] diagonal2 = new int[sized2];
		for(int i = 0; i < sized2; i++){
			if((x+y) < size){
				diagonal2[i] = exboarddata[x+y-i][i];
			}else{
				diagonal2[i] = exboarddata[size-1-i][x+y+1-size+i];
			}
		}
		return diagonal2;
	}

	public void setSeqDiagonal2(int[] diagonal2, int x, int y){
		int sized2 = size - Math.abs(size-1-x-y);
		for(int i = 0; i < sized2; i++){
			if((x+y) < size){
				exboarddata[x+y-i][i] = diagonal2[i];
			}else{
				exboarddata[size-1-i][x+y+1-size+i] = diagonal2[i];
			}
		}
	}

	public int getBlackSum(){
		int sum = 0;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(exboarddata[i][j] > 0){
					sum += exboarddata[i][j];
				}
			}
		}
		return sum;
	}

	public int getWhiteSum(){
		int sum = 0;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(exboarddata[i][j] < 0){
					sum += exboarddata[i][j];
				}
			}
		}
		return sum;
	}
}

