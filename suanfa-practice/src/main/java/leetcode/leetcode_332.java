package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 30241
 * @version 1.0
 * @description: TODO
 * @date 2025/2/27 上午8:53
 */
public class leetcode_332 {

    private List<String> result;
    private String cur;
    private boolean[] used;
    private int usedNum;
    public List<String> findItinerary(List<List<String>> tickets) {
        //回溯法，暴力搜索，
        //按照当前节点，深度优先搜索 所有可能，且优先搜索 字典序靠前 的节点
        result=new ArrayList<>();
        //先排序，方便后续处理字符序的比较
        tickets.sort((a,b)->{
            if(a.get(0).equals(b.get(0))){
                return a.get(1).compareToIgnoreCase(b.get(1));
            }else{
                return a.get(0).compareToIgnoreCase(b.get(0));
            }
        });
        cur="JFK";//从JFK开始
        result.add(cur);
        used=new boolean[tickets.size()];
        Arrays.fill(used,false);
        usedNum=0;
        backTrack(tickets);
        return result;
    }
    private void backTrack(List<List<String>> tickets){
        //终止
        if(usedNum==tickets.size()){
            return;
        }
        //递归
        for(int i=0;i<tickets.size();i++){
            if(used[i]){
                continue;
            }
            List<String> ticket = tickets.get(i);
            if(!ticket.get(0).equals(cur)){
                continue;
            }
            //找到了可用的节点
            //System.out.println(ticket.get(0)+"->"+ticket.get(1));
            result.add(ticket.get(1));
            cur=ticket.get(1);
            used[i]=true;
            usedNum++;

            backTrack(tickets);
            //终止
            if(usedNum==tickets.size()){
                return;
            }
            usedNum--;
            used[i]=false;
            result.remove(result.size()-1);
            cur=result.get(result.size()-1);
        }
    }

}
