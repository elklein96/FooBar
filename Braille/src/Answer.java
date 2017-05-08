import java.util.HashMap;

public class Answer {
	private static HashMap<Character, String> brailleLookup; 
    private static final String CAPITALIZATION_MARK = "000001";

    private static void loadBraille() {
        brailleLookup = new HashMap<Character,String>();

        brailleLookup.put(' ', "000000");
        brailleLookup.put('a', "100000");
        brailleLookup.put('b', "110000");
        brailleLookup.put('c', "100100");
        brailleLookup.put('d', "100110");
        brailleLookup.put('e', "100010");
        brailleLookup.put('f', "110100");
        brailleLookup.put('g', "110110");
        brailleLookup.put('h', "110010");
        brailleLookup.put('i', "010100");
        brailleLookup.put('j', "010110");
        brailleLookup.put('k', "101000");
        brailleLookup.put('l', "111000");
        brailleLookup.put('m', "101100");
        brailleLookup.put('n', "101110");
        brailleLookup.put('o', "101010");
        brailleLookup.put('p', "111100");
        brailleLookup.put('q', "111110");
        brailleLookup.put('r', "111010");
        brailleLookup.put('s', "011100");
        brailleLookup.put('t', "011110");
        brailleLookup.put('u', "101001");
        brailleLookup.put('v', "111001");
        brailleLookup.put('w', "010111");
        brailleLookup.put('x', "101101");
        brailleLookup.put('y', "101111");
        brailleLookup.put('z', "101011");
    }

    private static String buildAnswer(String input) {
        Character c;
        String brailleChar;
        StringBuilder result = new StringBuilder(50);

        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            brailleChar = brailleLookup.get(c);
            if (brailleChar == null && Character.isUpperCase(c)) {
                result.append(CAPITALIZATION_MARK);
                brailleChar = brailleLookup.get(Character.toLowerCase(c));
            }
            result.append(brailleChar);
        }

        return result.toString();
    }

    public static String answer(String plaintext) { 
        loadBraille();
        return buildAnswer(plaintext);
    }
}
