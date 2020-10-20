import java.util.Stack;

/**
 * Q844. 比较含退格的字符串
 *
 * @author Xprsh
 * @Description TODO
 * @createTime 2020/10/20/ 08:31:21
 */
public class Q844_BackSpaceCompare {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s = new Stack<>();
        Stack<Character> t = new Stack<>();

        for(int i = 0;i<S.length();i++){
            char ch = S.charAt(i);
            if(ch == '#'){
                if(!s.isEmpty()){
                    s.pop();
                }
            }else{
                s.push(ch);
            }
        }

        for(int i = 0;i<T.length();i++){
            char ch = T.charAt(i);
            if(ch == '#'){
                if(!t.isEmpty()){
                    t.pop();
                }
            }else{
                t.push(ch);
            }
        }

        if(s.size() != t.size()){
            return false;
        }

        int size = s.size();
        for(int i = 0;i < size;i++){
            if(s.pop() != t.pop()){
                return false;
            }
        }
        return true;
    }
}
