package li.data;

public class CopyBoardData {
	public static BoardData copyBoardData(BoardData boarddata){
		BoardData boarddata2 = new BoardData();
		for(int i = 0; i < boarddata.getSize(); i++){
			for(int j = 0; j < boarddata.getSize(); j++){
					boarddata2.setData(i, j, boarddata.getData(i, j));
			}
		}
		return boarddata2;
	}

	public static ExtendStableBoardData copyBoardData(ExtendStableBoardData boarddata){
		ExtendStableBoardData boarddata2 = new ExtendStableBoardData();
		for(int i = 0; i < boarddata.getSize(); i++){
			for(int j = 0; j < boarddata.getSize(); j++){
					boarddata2.setData(i, j, boarddata.getData(i, j));
			}
		}
		return boarddata2;
	}
}
