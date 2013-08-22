package li.input;

import java.util.LinkedList;
import java.util.Scanner;
import li.data.InputData;
import li.data.MenuData;
import li.output.MsgOutput;
import li.rule.NextStepCheck;
import li.check.InputCheck;
import li.data.BoardData;

public class PlayerInput{
	private static Scanner in = new Scanner(System.in);
	private static String input;
	private static String menuselect;

	//不正入力を排除し、入力の値をStringで返す
	public static String playerInput(BoardData boarddata, InputData inputdata){
		LinkedList<Integer[]> available_position = NextStepCheck.getAvailablePosition(boarddata, inputdata.isNextBlack());
		while(true){
			if(!available_position.isEmpty()){
				MsgOutput.lastStepMsg(inputdata);
				MsgOutput.inputMsg(inputdata);
				input = in.nextLine();
				if(input.equals("menu")){
					return "MENU";
				}else if(InputCheck.isIntSplit(2, input, "-")){
					String[] vals = input.split("-");
					Integer[] intvals = {Integer.parseInt(vals[0])-1, Integer.parseInt(vals[1])-1};
					if(NextStepCheck.isNextAvailable(boarddata, inputdata.isNextBlack(), intvals[0], intvals[1]) == 0){
						System.out.println("そこには置けません。再度入力してください。");
						continue;
					}
					return input;
				}else{
					System.out.println("正しい形式で入力してください。");
					continue;
				}
			}else{
				MsgOutput.passMsg(inputdata);
				input = in.nextLine();
				if(input.equals("menu")){
					return "MENU";
				}else{
					return "PASS";
				}
			}
		}
	}

	//入力座標をinputdataに入れる
	public static InputData writeInputData(BoardData boarddata, InputData inputdata, String input){
		String[] vals = input.split("-");
		Integer[] intvals = {Integer.parseInt(vals[0])-1, Integer.parseInt(vals[1])-1};
		inputdata.addPlayerInput(intvals);
		return inputdata;
	}

	//メニューの選択肢入力
	public static String menuInput(InputData inputdata){
		MsgOutput.menuMsg(inputdata);
		String select = in.nextLine();
		if(InputCheck.isInt(select)){
			int key = Integer.parseInt(select);
			if(MenuData.isKeyExist(key)){
				menuselect = MenuData.getMenu(key);
			}else{
				System.out.println("正しい選択肢を入力してください。");
				menuInput(inputdata);
			}
		}else{
			System.out.println("数字で入力してください。");
			menuInput(inputdata);
		}
		return menuselect;
	}
}