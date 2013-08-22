package li.output;

import li.data.BoardData;
import li.data.InputData;
import li.data.MenuData;

public class MsgOutput{
	public static void inputMsg(InputData inputdata){
		if(inputdata.isNextBlack()){
			System.out.println("\n次は黒の番です。");
		}else{
			System.out.println("\n次は白の番です。");
		}
		System.out.println("座標(x,y)をx-yの形で入力してください。メニューに入る場合はmenuを入力してください。");
	}

	public static void menuMsg(InputData inputdata){
		System.out.println("選びたい項目を数字で入力してください。");
		MenuData.setMenu(inputdata);
		for(int i = 0; i < MenuData.getMenuSize(); i++ ){
			System.out.println(i + ":" + MenuData.getMenu(i));
		}
	}

	public static void lastStepMsg(InputData inputdata){
		if(inputdata.getInputSize() != 0){
			System.out.println("前回相手は " + (inputdata.getLastInput()[0]+1) + "-" + (inputdata.getLastInput()[1]+1) + " に置きました。");
		}
	}

	public static void passMsg(InputData inputdata){
		if(inputdata.isNextBlack()){
			System.out.println("\n黒がパスとなります。");
		}else{
			System.out.println("\n白がパスとなります。");
		}
		System.out.println("メニューに入る場合はmenuを入力してください。それ以外の入力はパスします。");
	}

	public static void endMsg(BoardData boarddata){
		System.out.println("ゲーム終了です。");
		System.out.println("黒" + boarddata.getBlackSum() + " 対 白" + (-boarddata.getWhiteSum()));
		if(boarddata.getBlackSum() > -boarddata.getWhiteSum()){
			System.out.println("黒の勝ちです。");
		}else if(boarddata.getBlackSum() < -boarddata.getWhiteSum()){
			System.out.println("白の勝ちです。");
		}else{
			System.out.println("引き分けです。");
		}
	}

	public static void isHintOn(boolean ishinton){
		if(ishinton){
			System.out.println("HINT ON");
		}else{
			System.out.println("HINT OFF");
		}
	}

	public static void isBlackAI(boolean isblackAI){
		if(isblackAI){
			System.out.println("Now black is AI");
		}else{
			System.out.println("Now black is Player");
		}
	}

	public static void isWhiteAI(boolean iswhiteAI){
		if(iswhiteAI){
			System.out.println("Now white is AI");
		}else{
			System.out.println("Now white is Player");
		}
	}
}