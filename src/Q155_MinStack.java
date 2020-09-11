import java.util.Stack;

/**
 * Q155. 最小栈
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 */
public class Q155_MinStack {
    /** initialize your data structure here. */
    public Q155_MinStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }

    Stack<Integer> stack;
    Stack<Integer> min;

    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || min.peek() >= x){
            min.push(x);
        }
    }

    public void pop() {
        if (stack.pop().equals(min.peek())){
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }

    public static void main(String[] args) {
        Q155_MinStack obj = new Q155_MinStack();
        obj.push(0);
        obj.push(1);
        obj.push(0);

        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.getMin());
    }
}
