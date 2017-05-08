import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Answer {

	private static int high = 0;
	private static int largestElementSize = 0;

	private static String getStringVal(List<Integer> list) {
		String buff = "";
		int sumOfDigits = 0;

		for (int i = 0; i < list.size(); i++) {
			buff += list.get(i);
			sumOfDigits += list.get(i);
		}
		return (sumOfDigits % 3 == 0) ? buff : null;
	}

	private static Set<String> getPossibleValues(int[] list) {
		int n = list.length;
		int highElementSize = 0;
		Set<String> output = new HashSet<String>();
		List<Integer> element;
		String strVal;

		for (long i = 0; i < (1 << n); i++) {
			element = new ArrayList<Integer>();
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					element.add(list[j]);
				}
			}
			if (element.size() > 0) {
				strVal = getStringVal(element);
				if (strVal != null && element.size() >= highElementSize) {
					highElementSize = element.size();
					output.add(strVal);
				}
			}
		}
		largestElementSize = highElementSize;
		return output;
	}
	
	private static void getHighestValOfPermutations(String str, int l, int r) {
	    if (l == r) {
	    	int p = Integer.parseInt(str);
	    	if (p > high) {
	    		high = p;
	    	}
        }
	    else {
	        for (int i = l; i <= r; i++) {
	            str = swap(str, l, i);
	            getHighestValOfPermutations(str, l + 1, r);
	            str = swap(str, l, i);
	        }
	    }
	}

	private static String swap(String a, int i, int j) {
	    char temp;
	    char[] charArray = a.toCharArray();
	    temp = charArray[i] ;
	    charArray[i] = charArray[j];
	    charArray[j] = temp;
	    return String.valueOf(charArray);
	}

	public static int answer(int[] l) {
		Set<String> setOfValues = getPossibleValues(l);

		for (String s : setOfValues) {
			if (s.length() == largestElementSize) {
				getHighestValOfPermutations(s, 0, s.length() - 1);
			}
		}
		return high;
	}

	public static void main(String[] args) {
		int[] input = { 9, 6, 1, 7, 4, 8, 9, 4, 1, 0 };
		System.out.println("Answer: " + answer(input));
	}
}