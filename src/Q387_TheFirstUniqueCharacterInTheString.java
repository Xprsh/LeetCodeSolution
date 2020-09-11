import java.util.HashMap;
import java.util.Map;

public class Q387_TheFirstUniqueCharacterInTheString {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }

        if (s.length() == 1) {
            return 0;
        }

        Map<Character, Boolean> isSingle = new HashMap<>();

        for (Character ch : s.toCharArray()) {
            if (isSingle.containsKey(ch)) {
                isSingle.put(ch, false);
            } else {
                isSingle.put(ch, true);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (isSingle.get(s.charAt(i))) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(
                new Q387_TheFirstUniqueCharacterInTheString()
                        .firstUniqChar("aabbccc")
                );
    }
}
