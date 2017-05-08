import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answer {

	private static int value;
	
	private static int getNextValue(String n, int b) {
		int x, y;
		String strX;
		char[] tmp;

		tmp = n.toCharArray();

		Arrays.sort(tmp);

		strX = new StringBuilder(new String(tmp)).reverse().toString();
		y = Integer.parseInt(new String(tmp), b);
		x = Integer.parseInt(strX, b);

		return x - y;
	}
	
	private static List<Integer> getNextCycle(String n, int b) {
		List<Integer> cycle = new ArrayList<Integer>();

		value = getNextValue(n, b);

		while (!cycle.contains(value)) {
			cycle.add(value);
			value = getNextValue(Integer.toString(value, b), b);
		}

		return cycle;
	}
	
	public static int answer(String n, int b) { 
		int cycleLength = 1;
		List<List<Integer>> cycles = new ArrayList<List<Integer>>();
		List<Integer> cycle;

		cycle = getNextCycle(n, b);

		while (!cycles.contains((cycle))) {
			cycles.add(cycle);
			cycle = getNextCycle(Integer.toString(value, b), b);
		}

		cycles.add(cycle);

		for (int i = cycles.indexOf(cycle) + 1; i < cycles.size(); i++) {
			if (cycles.get(i).equals(cycle)) {
				cycleLength = i - cycles.indexOf(cycle);
			}
		}

		return cycleLength;
    }

	public static void main(String[] args) {
		System.out.println("Answer: " + answer("210022", 3));
	}

}
