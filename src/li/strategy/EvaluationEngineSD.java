package li.strategy;

import java.util.LinkedList;

//TODO 怎样让threshold的值继承?？
import li.data.BoardData;
import li.data.CopyBoardData;
import li.rule.NextStepCheck;
import li.rule.Reverse;

public class EvaluationEngineSD {
	int depth = 5;
	Integer threshold = null;
	Integer[] next = {0, 0};
	
	public EvaluationEngineSD(){
	}
	
	public EvaluationEngineSD(Integer depth){
		this.depth = depth;
	}

	public Integer[] nextStep(BoardData boarddata, boolean isnodeMAX){
		threshold = null;
		Integer score = null;
		if(NextStepCheck.isGameEnd(boarddata)){
			return null;
		}
		LinkedList<Integer[]> nextstep = NextStepCheck.getAvailablePosition(boarddata, isnodeMAX);
		if(nextstep.size() == 0){
			return null;
		}
		for(Integer[] step: nextstep){
			BoardData nextboarddata = CopyBoardData.copyBoardData(boarddata);
			Reverse.reverse(nextboarddata, isnodeMAX, step[0], step[1]);
			int newscore = searchEngine(depth - 1, score, nextboarddata, !isnodeMAX, !isnodeMAX);
			if(isnodeMAX){
				if(score == null){
					score = -1000000;
				}
				if(newscore > score){
					score = newscore;
					next[0] = step[0];
					next[1] = step[1];
				}
			}else{
				if(score == null){
					score = 1000000;
				}
				if(newscore < score){
					score = newscore;
					next[0] = step[0];
					next[1] = step[1];
				}
			}
		}
		return next;
	}
	
	public Integer searchEngine(Integer depth, Integer threshold, BoardData boarddata, boolean isthresholdMIN, boolean isnodeMAX){
		Integer newscore = null, nodescore = null;
		//終局
		if(NextStepCheck.isGameEnd(boarddata) || depth == 0){
			return evaluationNow(boarddata, isnodeMAX);
		}
		LinkedList<Integer[]> nextstep = NextStepCheck.getAvailablePosition(boarddata, isnodeMAX);
		if(nextstep.size() == 0){
			nextstep = NextStepCheck.getAvailablePosition(boarddata, !isnodeMAX);
		}
		for(Integer[] step: nextstep){
			BoardData nextboarddata = CopyBoardData.copyBoardData(boarddata);
			Reverse.reverse(nextboarddata, isnodeMAX, step[0], step[1]);
			//pass or not
			if(isthresholdMIN == isnodeMAX){
				newscore = searchEngine(depth - 1, threshold, nextboarddata, !isthresholdMIN, !isnodeMAX);
			}else{
				newscore = searchEngine(depth - 2, threshold, nextboarddata, !isthresholdMIN, isnodeMAX);
			}
			//renew the nodescore
			if(nodescore == null){
				nodescore = newscore;
			}else{
				if(isnodeMAX){
					if(newscore > nodescore){
						nodescore = newscore;
					}
				}else{
					if(newscore < nodescore){
						nodescore = newscore;
					}
				}
			}
			if(threshold != null){
				if(isthresholdMIN){
					if(nodescore > threshold){
						return nodescore;
					}
				}else{
					if(nodescore < threshold){
						return nodescore;
					}
				}
			}
		}
		threshold = nodescore;
		return nodescore;
	}

	public Integer evaluationNow(BoardData boarddata, boolean isnextblack){
		int situation = (boarddata.getBlackSum() + boarddata.getWhiteSum()) + 10*Mobility.situation(boarddata) + 1000 * StableDisc.situation(boarddata);
		if(NextStepCheck.isGameEnd(boarddata)){
			situation += 100*(boarddata.getBlackSum() + boarddata.getWhiteSum());
			if(boarddata.getBlackSum() == 0 || boarddata.getWhiteSum() == 0){
				situation += 1000*(boarddata.getBlackSum() + boarddata.getWhiteSum());
			}
		}
		return situation;
	}
}
