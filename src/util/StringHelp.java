package util;

public class StringHelp {
	public static boolean isNotEmpty(String s){
		if(s == null){
			return false;
		}
		if(s.equals("")){
			return false;
		}
		return true;
	}
}
