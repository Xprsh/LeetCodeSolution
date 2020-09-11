import java.util.Stack;

/**
 * Q232. 用栈实现队列
 */
public class Q232_MyQueue {
    /** Initialize your data structure here. */
    public Q232_MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    private Stack<Integer> s1;
    private Stack<Integer> s2;


    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!s2.isEmpty()){
           return s2.pop();
        }else if (!s1.isEmpty()){
            int size = s1.size();
            for (int i = 0; i < size; i++) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!s2.isEmpty()){
            s2.peek();
        }else if (!s1.isEmpty()){
            int size = s1.size();
            for (int i = 0; i < size; i++) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {

        return s2.isEmpty() && s1.isEmpty();
    }

    public static void main(String[] args) {
        Q232_MyQueue obj = new Q232_MyQueue();
        obj.push(1);
        obj.push(2);
        System.out.println(obj.peek());
        System.out.println(obj.pop());
        System.out.println(obj.empty());
    }
}
