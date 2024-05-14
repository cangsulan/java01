package OJ_work2_6;

import java.util.ArrayList;

public class MyStack {
    ArrayList<String> list;
    public MyStack() {
        list = new ArrayList<>();
    }
    public void push(String x) {
        list.add(x);
    }
    public String pop() {
        if(list.isEmpty()){
            return null;
        }else {
            return list.remove(list.size()-1);
        }
    }
}
