import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Q5507_ModifyString {
    public String modifyString(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        if (s.length() == 1) {
            if (s.charAt(0) == '?') {
                return "a";
            } else {
                return s;
            }
        }

        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < s.length(); i++) {
            if (sb.charAt(i) == '?'){
                if(i == 0){
                    sb.replace(0,1,randomChar(' ',sb.charAt(1)));
                }else if (i == s.length() - 1){
                    sb.replace(i, i+1,randomChar(
                           s.charAt(i-1),' '
                    ));
                }else {
                    sb.replace(i,i+1,randomChar(sb.charAt(i-1),sb.charAt(i+1)));
                }
            }
        }
        return String.valueOf(sb);
    }

    private String randomChar(char left, char right) {
        char[] c = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'
                , 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char result;
        do {
            int x = new Random().nextInt(25);
            result = c[x];
        } while (result == left || result == right);

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        Q5507_ModifyString obj = new Q5507_ModifyString();
        System.out.println(obj.modifyString("???"));
    }
}
