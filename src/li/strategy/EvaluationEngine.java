package li.strategy;

import java.util.LinkedList;

import li.data.BoardData;
import li.data.CopyBoardData;
import li.rule.NextStepCheck;
import li.rule.Reverse;

public class EvaluationEngine {
	int depth = 5;
	Integer[] next = {0, 0};
	
	public EvaluationEngine(){
	}
	
	public EvaluationEngine(Integer depth){
		this.depth = depth;
	}

	public Integer[] nextStep(BoardData boarddata, boolean isnodeMAX){
		Integer score = null;
		if(NextStepCheck.isGameEnd(boarddata)){
			return null;
		}
		LinkedList<Integer[]> nextstep = NextStepCheck.getAvailablePosition(boarddata, isnodeMAX);
		if(nextstep.size() == 0){
			return null;
		}
		for(Integer[] step: nextstep){
//			System.out.println("Search...... Step[" + (step[0] + 1) + "][" + (step[1] + 1) + "], depth = " + depth);
			BoardData nextboarddata = CopyBoardData.copyBoardData(boarddata);
			Reverse.reverse(nextboarddata, isnodeMAX, step[0], step[1]);
//			System.out.println("score = " + score);
			int newscore = searchEngine(depth - 1, score, nextboarddata, !isnodeMAX, !isnodeMAX);
//			System.out.println("Not dealed, newscore[" + (step[0] + 1) + "][" + (step[1] + 1) + "] = " + newscore + ", score = " + score + ", isnodeMAX = " + isnodeMAX  + "\n");
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
		Integer score = null, newscore = null;
		if(NextStepCheck.isGameEnd(boarddata) || depth == 0){
//			System.out.println("result = " + evaluationNow(boarddata, isnodeMAX));
			return evaluationNow(boarddata, isnodeMAX);
		}
		LinkedList<Integer[]> nextstep = NextStepCheck.getAvailablePosition(boarddata, isnodeMAX);
		if(nextstep.size() == 0){
			nextstep = NextStepCheck.getAvailablePosition(boarddata, !isnodeMAX);
		}
		for(Integer[] step: nextstep){
//			System.out.println("Search...... Step[" + (step[0] + 1) + "][" + (step[1] + 1) + "], depth = " + depth);
			BoardData nextboarddata = CopyBoardData.copyBoardData(boarddata);
			Reverse.reverse(nextboarddata, isnodeMAX, step[0], step[1]);
			if(isthresholdMIN == isnodeMAX){
				newscore = searchEngine(depth - 1, threshold, nextboarddata, !isthresholdMIN, !isnodeMAX);
			}else{
				newscore = searchEngine(depth - 2, threshold, nextboarddata, !isthresholdMIN, isnodeMAX);
			}
//			System.out.println("Step[" + (step[0] + 1) + "][" + (step[1] + 1) + "], newscore[" + depth + "]= " + newscore + ", threshold = " + threshold + ", score = " + score + ", isthresholdMIN = "
//					+ isthresholdMIN + ", isnodeMAX = " + isnodeMAX);
			if(isthresholdMIN){
				if(threshold == null){
					threshold = 1000000;
				}
				if (newscore >= threshold){
//					System.out.println("newscore[" + depth + "]= " + newscore + ", abandon, " + isnodeMAX);
//					break;
				}
			}else{
				if(threshold == null){
					threshold = -1000000;
				}
				if(newscore <= threshold){
//					System.out.println("newscore[" + depth + "]= " + newscore + ", abandon, " + isnodeMAX);
//					break;
				}
			}
			if(isnodeMAX){
				if(score == null){
					score = -1000000;
				}
				if(newscore > score){
					score = newscore;
//					System.out.println("Renew score[" + depth + "]= " + score);
				}
			}else{
				if(score == null){
					score = 1000000;
				}
				if(newscore < score){
					score = newscore;
//					System.out.println("Renew score[" + depth + "]= " + score);
				}
			}
			if((!isthresholdMIN && score > threshold) || (isthresholdMIN && score < threshold)){
				threshold = score;
//				System.out.println("Renewed threshold = " + threshold);
			}
		}
//		System.out.println("threshold[" + depth + "]= " + threshold + ", score = " + score);
		return score;
	}

	public Integer evaluationNow(BoardData boarddata, boolean isnextblack){
		int situation = (boarddata.getBlackSum() + boarddata.getWhiteSum()) + 10*Mobility.situation(boarddata) ;
//				+ 1000 * StableDisc.situation(boarddata);
		if(NextStepCheck.isGameEnd(boarddata)){
			situation += 100*(boarddata.getBlackSum() + boarddata.getWhiteSum());
			if(boarddata.getBlackSum() == 0 || boarddata.getWhiteSum() == 0){
				situation += 1000*(boarddata.getBlackSum() + boarddata.getWhiteSum());
			}
		}
		return situation;
	}
}
