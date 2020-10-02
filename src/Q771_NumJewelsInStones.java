import java.util.ArrayList;
import java.util.List;

/**
 * Q771. 宝石与石头
 *
 * @author Xprsh
 * @Description 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表
 * 了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。J 中的字母不重复，J 和 S中的所有
 * 字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * @createTime 2020/10/02/ 10:18:33
 */
public class Q771_NumJewelsInStones {
    public int numJewelsInStones(String J, String S) {
        if(J == null || S == null){
            return 0;
        }

        int len1 = J.length();
        int len2 = S.length();

        List<Character> list = new ArrayList<>();
        for(int i = 0; i < len1; i++){
            if(!list.contains(J.charAt(i))){
                list.add(J.charAt(i));
            }
        }

        int ans = 0;
        for(int i = 0; i < len2; i++){
            if(list.contains(S.charAt(i))){
                ans++;
            }
        }
        return ans;
    }
}
