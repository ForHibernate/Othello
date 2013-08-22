package li.check;

public class InputCheck {
	//入力は集合choicesの範囲内にあるかどうかをチェック
/*	public static boolean selectCheck(String select, List<String> choices) throws CalcException{
		if (choices.contains(select)){
			return true;
		} else {
			return false;
		}
	}
*/
	//String inputは一つのint型数字であるかどうかをチェック
	public static boolean isInt(String input) {
		try{
			Integer.parseInt(input);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	public static boolean isIntSplit(int length, String input, String splitstr){
		String[] vals = input.split(splitstr);
		if (vals.length == length){
			for(int i = 0; i < vals.length; i++){
				if(!isInt(vals[i])){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}
}