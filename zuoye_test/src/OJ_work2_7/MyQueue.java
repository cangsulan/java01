package OJ_work2_7;

import java.util.ArrayList;

public class MyQueue {
    ArrayList<String> list;
    public MyQueue() {
        list = new ArrayList<>();
    }
    public void push(String x) {
        list.add(x);
    }
    public String pop() {
        if(list.isEmpty()){
            return null;
        }else {
            return list.remove(0);
        }
    }
}
