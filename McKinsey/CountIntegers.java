import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CountIntegers {

	Map<Integer, Integer> numbers = new HashMap<Integer,Integer>();

	public void frequencyCount(int numArray[]){

		List<Map.Entry<Integer,Integer>> numberslist = new ArrayList<Map.Entry<Integer,Integer>>(numbers.entrySet());

		Collections.sort(numberslist, new Comparator<Map.Entry<Integer,Integer>>() {

			public int compare(Map.Entry<Integer,Integer> map1, Map.Entry<Integer,Integer> map2) {
				Integer i1 = (Integer) map1.getValue();
				Integer i2 = (Integer) map2.getValue();
				return i2.compareTo(i1);
			}

		});

		System.out.println("Printing array element and count");
		for(Map.Entry<Integer,Integer> map : numberslist) {
			System.out.println("  "+map.getKey()+" = "+map.getValue());
		}
	}

	public static void main (String[] args){

		try {

			CountIntegers countintegers = new CountIntegers();

			int size = 0;
			System.out.println("Enter the length of the array: ");
			Scanner input = new Scanner(System.in);
			size = input.nextInt();

			int[] array = new int[size];

			System.out.println("Enter the array: ");

			// Reading user input and adding determining its frequency simultaneously

			for(int i =0;i<size;i++){

				array[i]=input.nextInt();

				if(countintegers.numbers.containsKey(array[i])){
					countintegers.numbers.put(array[i], (countintegers.numbers.get(array[i])+1));
				}
				else{
					countintegers.numbers.put(array[i], 1);
				}

			}
			input.close();

			// Sorting it and displaying the entered numbers in descending order
			countintegers.frequencyCount(array);
		}
		catch(NoSuchElementException e){
			System.out.println("Error entering input: Enter Integers only.");
		}
		catch(Exception e){
			System.out.println("Error: Run the Program again"+e.getMessage());

		}	
	}
}
