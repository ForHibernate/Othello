package li;

import li.data.BoardData;
import li.data.BoardDataManager;
import li.data.CopyBoardData;
import li.data.InputData;
import li.input.PlayerInput;
import li.output.BoardOutput;
import li.output.MsgOutput;
import li.rule.NextStepCheck;
import li.rule.Reverse;
import li.strategy.AISD;
import li.strategy.StableDisc;

public class Othello {
	private boolean ishinton = true;
	private InputData inputdata = new InputData();
	private String playerinput;
	private boolean isblackAI = false;
	private boolean iswhiteAI = false;
	private BoardDataManager boarddatamanager = new BoardDataManager();

	public Othello(){
		boarddatamanager.addLastData(new BoardData());
		while(true){
			round();
			if(NextStepCheck.isGameEnd(boarddatamanager.getLastData())){
				BoardOutput.printBoard(boarddatamanager.getLastData(), inputdata.isNextBlack(), ishinton);
				System.out.println(StableDisc.countStableDisc(boarddatamanager.getLastData(), true));
				System.out.println(StableDisc.countStableDisc(boarddatamanager.getLastData(), false));
				MsgOutput.endMsg(boarddatamanager.getLastData());
				menu();
			}
		}
	}

	private void round(){
		BoardData boarddata2 = CopyBoardData.copyBoardData(boarddatamanager.getLastData());
		BoardOutput.printBoard(boarddata2, inputdata.isNextBlack(), ishinton);
//		System.out.println("Mobility B = " + Mobility.getMobility(boarddata2, true)); 
//		System.out.println("Mobility W = " + Mobility.getMobility(boarddata2, false)); 
//		System.out.println(StableDisc.countStableDisc(boarddata2, true));
//		System.out.println(StableDisc.countStableDisc(boarddata2, false));
		if((!inputdata.isNextBlack()) && iswhiteAI){
			System.out.println("次は白です。");
			playerinput = AISD.AIInput(boarddata2, inputdata);
		}else if(inputdata.isNextBlack() && isblackAI){
			System.out.println("次は黒です。");
			playerinput = AISD.AIInput(boarddata2, inputdata);
		}else{
			playerinput = PlayerInput.playerInput(boarddatamanager.getLastData(), inputdata);
		}
		if(playerinput.equals("MENU")){
			menu();
			round();
		}else if(playerinput.equals("PASS")){
			Integer[] pass = {-1, -1};
			boarddatamanager.addPass();
			inputdata.addPlayerInput(pass);
			round();
		}else{
			inputdata = PlayerInput.writeInputData(boarddata2, inputdata, playerinput);
			Integer[] input = inputdata.getLastInput();
			boolean isblack = !inputdata.isNextBlack();
			boarddata2 = Reverse.reverse(boarddata2, isblack, input[0], input[1]);
			boarddatamanager.addLastData(boarddata2);
		}
	}

	private void menu(){
		String menuselect = PlayerInput.menuInput(inputdata);
		if(menuselect.equals("EXIT")){
			System.exit(0);
		}else if(menuselect.equals("HINTSWITCH")){
			ishinton = !ishinton;
			MsgOutput.isHintOn(ishinton);
		}else if(menuselect.equals("RESET")){
			boarddatamanager.resetManager();
			boarddatamanager.addLastData(new BoardData());
			inputdata.resetInput();
			isblackAI = false;
			iswhiteAI = false;
		}else if(menuselect.equals("UNDO")){
			inputdata.deleteLastInput();
			boarddatamanager.deleteLastData();
		}else if(menuselect.equals("AI-SWITCH-BLACK")){
			isblackAI = !isblackAI;
			MsgOutput.isBlackAI(isblackAI);
		}else if(menuselect.equals("AI-SWITCH-WHITE")){
			iswhiteAI = !iswhiteAI;
			MsgOutput.isWhiteAI(iswhiteAI);
		}
	}
}
