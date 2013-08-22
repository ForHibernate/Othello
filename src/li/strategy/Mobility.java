package li.strategy;

import li.data.BoardData;
import li.rule.NextStepCheck;

public class Mobility {
	public static int getMobility(BoardData boarddata, boolean color){
		return NextStepCheck.getAvailablePosition(boarddata, color).size();
	}

	public static int situation(BoardData boarddata){
		return getMobility(boarddata, true) - getMobility(boarddata, false);
	}

}





