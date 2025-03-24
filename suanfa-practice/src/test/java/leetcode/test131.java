package leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 30241
 * @version 1.0
 * @description: 测试leetcode_131
 * @date 2025/2/26 下午7:09
 */
public class test131 {

    private final leetcode_131 leetcode131=new leetcode_131();
    private final leetcode_332 leetcode332=new leetcode_332();

    @Test
    public void test() {

        //boolean a = leetcode131.isRightString("abcdba");
        //System.out.println(a);

        List<List<String>> list2 = leetcode131.partition("aab");
        for (List<String> list : list2) {
            for (String s : list) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }

    @Test
    public void test332() {
        List<List<String>> tickets = new ArrayList<>();
        //tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "KUL"));
        tickets.add(Arrays.asList("JFK", "NRT"));
        tickets.add(Arrays.asList("NRT", "JFK"));
        List<String> itinerary = leetcode332.findItinerary(tickets);
        for (String s : itinerary) {
            System.out.println(s);
        }
    }
}
