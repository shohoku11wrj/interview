
public class One_8 {
	
	public static boolean isRotation(String s1, String s2) {
		int len = s1.length();
		
		/* check that s1 and s2 are equal length and not empty */
		if(len == s2.length() && len > 0) {
			/* concatenate s1 and s1 within new buffer */
			String s1s1 = s1 + s1;
			return s1s1.contains(s2); 
			//TODO Here is my weakpoint -> just return the result of contains() is enough
			// no necessary to if(contains()) {return true;} else {return false;}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isRotation("waterbottle", "erbottlewat"));
		System.out.println(isRotation("waterbottlee", "erbottlewat"));
		System.out.println(isRotation("waterbtotle", "erbottlewat"));
		System.out.println(isRotation("waterbottel", "erbottlewat"));
		System.out.println(isRotation("waterbottle", "terbottlewa"));
	}

}
