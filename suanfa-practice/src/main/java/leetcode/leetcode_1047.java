package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class leetcode_1047 {
    public String removeDuplicates(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        int length=s.length();
        if(length<2){
            return s;
        }
        deque.push(s.charAt(0));
        for(int i=1;i<length;i++){
            Character ch=s.charAt(i);
            if(ch.equals(deque.peek())){
                deque.poll();
            }else{
                deque.push(ch);
            }
        }
        StringBuilder sb=new StringBuilder();
        if(deque.isEmpty()){
            return "";
        }
        for(int i=0;i<deque.size();i++){
            sb.append(deque.pollLast());
        }
        return sb.toString();
    }
}
