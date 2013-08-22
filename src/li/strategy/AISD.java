package li.strategy;

import java.util.LinkedList;
import java.util.Scanner;

import li.data.BoardData;
import li.data.InputData;
import li.output.BoardOutput;
import li.output.MsgOutput;
import li.rule.NextStepCheck;

public class AISD {
	private static Scanner in = new Scanner(System.in);
	public static String AIInput(BoardData boarddata, InputData inputdata){
		LinkedList<Integer[]> nextlist = NextStepCheck.getAvailablePosition(boarddata, inputdata.isNextBlack());
		String AIinput;
		if(nextlist.size() != 0){
			EvaluationEngineSD find = new EvaluationEngineSD();
			Integer[] next = find.nextStep(boarddata, inputdata.isNextBlack());
			BoardOutput.printBoard(boarddata, inputdata.isNextBlack(), true);
			System.out.println("AIは、" + (next[0]+1) + "-" + (next[1]+1) + "に置きます。");
			System.out.println("メニューに入る場合はmenuを入力してください。それ以外の入力は次のターンをシフトします。");
			AIinput = in.nextLine();
			if(AIinput.equals("menu")){
				return "MENU";
			}else{
				return (next[0]+1)+"-"+(next[1]+1);
			}
		}else{
			MsgOutput.passMsg(inputdata);
			AIinput = in.nextLine();
			if(AIinput.equals("menu")){
				return "MENU";
			}else{
				return "PASS";
			}
		}
	}
}
