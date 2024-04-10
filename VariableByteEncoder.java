package project2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
import java.util.stream.IntStream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
import java.util.stream.IntStream;

public class VariableByteEncoder {
	
	public static ArrayList<Byte> variablebytenumber(int number) {
		
        ArrayList<Byte> list = new ArrayList<Byte>();
        
        while (true) {
        	
            list.add(0, (byte) (number % 128));
            if (number < 128) {
                break;
            }
            number /= 128;
        }
        
        list.set(list.size() - 1, (byte) (list.get(list.size() - 1) | 128));
        return list;
    }
    
    public static ArrayList<String> variablebyteEncode(ArrayList<Integer> gaps) {
    	
        ArrayList<String> bitslist = new ArrayList<String>();
        
        for (int number : gaps) {
        	
            ArrayList<Byte> variablebytes = variablebytenumber(number);
            
            for (byte b : variablebytes) {
            	
                String bits = Integer.toBinaryString(b & 0xFF);
                bits = "00000000".substring(bits.length()) + bits;
                bitslist.add(bits);
                
            }
        }
        return bitslist;
    }
    public static ArrayList<Integer> gapencode(List<Integer> postings) {
    	
        ArrayList<Integer> gaps = new ArrayList<Integer>();
        
        int previous = 0;
    
        for (int i = 0; i < postings.size(); i++){
        	
         int current = postings.get(i);
         int gap = current - previous;
            previous = current;
            gaps.add(gap);
            
    }   
    return gaps;
}
    
    public static void main(String[] args) {
    	
    ArrayList<Integer> number = new ArrayList<Integer>();
    
    number.add(5);
    number.add(9);
    number.add(13);
    number.add(21);
    
    ArrayList<String> encoding = variablebyteEncode(gapencode(number));
    
    for (String bit : encoding) {
    	
        System.out.print(bit + "\n");
        
    }
}

}