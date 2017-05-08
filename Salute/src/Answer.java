
public class Answer {
	private static int buildAnswer(String input) {
        char c;
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == '>') {
            	for (int j = i; j < input.length(); j++) {
            		c = input.charAt(j);
            		if (c == '<') {
            			count += 2;
            		}
            	}
            }
        }

        return count;
    }

	public static int answer(String plaintext) {
        return buildAnswer(plaintext);
    }

	public static void main(String args[]) {
		System.out.println(answer("<<>><"));
	}
}
