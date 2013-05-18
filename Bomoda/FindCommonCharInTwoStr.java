/* copied */
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Set;  
  
public class FindCommonCharInTwoStr {  
  
    public static void main(String[] args) {  
        intersection(args[0],args[1]);  
    }  
  
    public static void intersection(String a,String b)  
    {  
        HashMap<Character, Integer> mapA = new HashMap<Character, Integer>();  
        HashMap<Character, Integer> mapB = new HashMap<Character, Integer>();  
        Set<Character> keys = null;  
        Iterator iterator = null;  
        boolean flag = false;  
        for(int i=0,j=0; i<a.length()||j<b.length(); i++,j++)  
        {  
            if(i<a.length())  
                mapA.put(new Character(a.charAt(i)), new Integer(i));  
            if(j<b.length())  
                mapB.put(new Character(b.charAt(j)), new Integer(j));  
        }  
        keys = mapB.keySet();  
        iterator = keys.iterator();  
        while(iterator.hasNext())  
        {  
            Character common = (Character)iterator.next();  
            if(mapA.get(common)!=null)  
            {  
                flag = true;  
                System.out.println("\""+common.charValue()+"\" is a common character");  
            }  
        }  
        if(flag!=true)  
            System.out.println("There is no common characters between these strings");  
    }  
}  
