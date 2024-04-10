package project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VB {
	public static List<Byte> vbEncodeNumber(int n) {
	    List<Byte> bytes = new ArrayList<>();
	    int quotient = n / 128;
	    int remainder = n % 128;
	    while (quotient > 0) {
	        bytes.add(0, (byte)(remainder | 0x80));
	        remainder = quotient % 128;
	        quotient /= 128;
	    }
	    bytes.add(0, (byte)remainder);
	    return bytes;
	}

	public static List<String> vbEncode(List<Integer> gapList) {
	    List<String> bitStream = new ArrayList<>();
	    for (int n : gapList) {
	        List<Byte> bytes = vbEncodeNumber(n);
	        for (byte b : bytes) {
	            String bits = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
	            bitStream.add(bits);
	        }
	    }
	    return bitStream;
	}

	public static List<Integer> gapencode(List<Integer> postingsList) {
	    List<Integer> gapList = new ArrayList<>();
	    int prev = 0;
	    for (int i = 0; i < postingsList.size(); i++) {
	        int curr = postingsList.get(i);
	        int gap = curr - prev;
	        prev = curr;
	        gapList.add(gap);
	    }
	    return gapList;
	}

	public static void main(String[] args) {
	    List<Integer> number = Arrays.asList(283047, 283154, 283159, 283202);
	    List<String> encoded = vbEncode(gapencode(number));
	    encoded.forEach(bit -> System.out.print(bit + " "));
	}
}
