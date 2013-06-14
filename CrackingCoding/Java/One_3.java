/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 */
public class One_3 {
	
	/*
	 * Solution #1: Sort the strings.
	 */
	public String sort(String s) {
		char[] content= s.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}
	
	public boolean permutation(String s, String t) {
		if(s.length() != t.length()) {
			return false;
		}
		return sort(s).equals(sort(t));
	}
	
	/*
	 * Solutions #2: check if the two strings have identical character counts.
	 */
	public boolean permutation2(String s, String t) {
		if(s.length() != t.length()) { 
			return false;
		}
		
		/*
		 *  s.length == t.length 
		 *  ensures that there is no necessary to exam any letters[c] would > 0,
		 *  because letters[c] < 0 has been examed, and if any letters[c] < 0 would has a letters[c] > 0 accordingly for same length.
		 */
		
		int[] letters = new int[256]; // assumption for ASCII
		
		char[] s_array = s.toCharArray();
		for(char c: s_array) { // count number of each char in s.
			letters[c]++;
		}
		
		for(int i = 0; i < t.length(); i++) {
			int c = (int) t.charAt(i);
			if(--letters[c] < 0) {
				return false;
			}
		}
		
		return true;
		
	}


	public static void main(String[] args) {
		One_3 test = new One_3();
		System.out.println(" --- Solution #1: Sort the strings. ---");
		System.out.println("test.permutation(halo, aloh) : " + test.permutation("halo", "aloh"));
		System.out.println("test.permutation(halo, Aloh) : " + test.permutation("halo", "Aloh"));
		System.out.println("test.permutation(halo, aloha) : " + test.permutation("halo", "aloha"));
		System.out.println(" --- Solution #2: Check character counts. ---");
		System.out.println("test.permutation2(halo, aloh) : " + test.permutation2("halo", "aloh"));
		System.out.println("test.permutation2(halo, Aloh) : " + test.permutation2("halo", "Aloh"));
		System.out.println("test.permutation2(halo, aloha) : " + test.permutation2("halo", "aloha"));
	}

}
