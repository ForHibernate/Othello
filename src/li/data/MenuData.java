package li.data;

import java.util.HashMap;

public class MenuData {
	private static HashMap<Integer, String> menudata = new HashMap<Integer, String>();

	public static String getMenu(int i){
		return menudata.get(i);
	}

	public static void setMenu(InputData inputdata){
		int num = 0;
		menudata = new HashMap<Integer, String>();
		menudata.put(num++, "EXIT");
		if(inputdata.getInputSize() < 60){
			menudata.put(num++, "RETURN");
			menudata.put(num++, "HINTSWITCH");
			menudata.put(num++, "AI-SWITCH-BLACK");
			menudata.put(num++, "AI-SWITCH-WHITE");
		}
		menudata.put(num++, "RESET");
		if(inputdata.getInputSize() != 0){
			menudata.put(num++, "UNDO");
		}
	}

	public static void setEndMenu(){
		int num = 0;
		menudata = new HashMap<Integer, String>();
		menudata.put(num++, "EXIT");
		menudata.put(num++, "RESET");
		menudata.put(num++, "UNDO");
	}

	public static int getMenuSize(){
		return menudata.size();
	}

	public static boolean isKeyExist(int key){
		return menudata.containsKey(key);
	}

}
