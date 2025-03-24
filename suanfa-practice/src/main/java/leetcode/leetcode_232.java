package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class leetcode_232 {
    class MyQueue{
        //用Deque来模拟栈，用2个栈来实现队列
        Deque<Integer> stack1;//当入栈
        Deque<Integer> stack2;//当出栈
        int size;

        public MyQueue() {
            this.stack1=new ArrayDeque<>();
            this.stack2=new ArrayDeque<>();
            this.size=0;
        }

        public void push(int x) {
            this.stack1.push(x);
            this.size++;
        }

        public int pop() {
            for(int i=this.stack1.size()-1;i>=0;i--){
                this.stack2.push(this.stack1.poll());
            }
            this.size--;
            int result= this.stack2.poll();
            for(int i=this.stack2.size()-1;i>=0;i--){
                this.stack1.push(this.stack2.poll());
            }
            return result;
        }

        public int peek() {
            for(int i=this.stack1.size()-1;i>=0;i--){
                this.stack2.push(this.stack1.poll());
            }
            int result = this.stack2.peek();
            for(int i=this.stack2.size()-1;i>=0;i--){
                this.stack1.push(this.stack2.poll());
            }
            return result;
        }

        public boolean empty() {
            return (this.size==0);
        }
    }
}
