package fitz.games.utils;

public class Utils {
	
	public final static String SUCCESS = "SUCCESS";
	
	public static boolean isValidPosNumber(String number) {
	    if (number == null) {
	        return false;
	    } else {
	    	for (char c : number.toCharArray()) {
		        if (c < '0' || c > '9') {
		            return false;
		        }
	    	}
	    }
	    return true;
	}
}
