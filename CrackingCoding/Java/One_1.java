/*
 * Chapter 1 | Arrays and Strings
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structure?
 */

public class One_1 {
	/*
	 * Time O(n), Space O(1) , O(1) = O(256) as they are both constant in calculus
	 */
	public static boolean isUniqueChars2(String str) {
		if(str.length() > 256)
			return false;
		
		boolean[] char_set = new boolean[256];
		for(int i=0; i < str.length(); i++) {
			int val = str.charAt(i);
			if(char_set[val]) { // Already found this char in string
				return false;
			}
			char_set[val] = true;
		}
		return false;
	}

// see: http://stackoverflow.com/questions/15909949/bitshift-need-help-to-understand-the-code
	// assume only 'a'..'z' in characters, so 26 < 32(<-- sizeof int)
	public static boolean isUniqueChars(String str) {
		if(str.length() > 256)
			return false;

		int checker = 0; // 000000000000000000000000000000000
		for(int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if((checker & (1 << val)) > 0) { // exam whether there is already an '1' at position val in checker
				return false;
			}
			checker |= (1 << val); // put 1 in checker at the position of val
		}
		return true;
	}

	public static void main(String[] args) {
		String testStr = "halo";
		if(isUniqueChars(testStr)){
			System.out.println("All unique characters in " + testStr + ".");
		}
		else {
			System.out.println("Not all unique characters in " + testStr + "." );
		}
	}
}
