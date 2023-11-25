package study.generic.low;

import java.util.ArrayList;
import java.util.List;

class GenericStack<T> {
    private List<T> stack;

    public GenericStack() {
        this.stack = new ArrayList<>();
    }

    public void push(T item){
        stack.add(item);
    }

    public T pop(){
        if(stack.isEmpty()){
            System.out.println("Stack is empty");
            return null;
        }
        return stack.remove(stack.size()-1);
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

}


public class GenericStackExample {
    public static void main(String[] args) {
        GenericStack<Integer> stack = new GenericStack<>();

        stack.push(10);
        stack.push(100);
        stack.push(1000);

        System.out.println("Popped Item : " + stack.pop());

        GenericStack<String> Stringstack = new GenericStack<>();

        Stringstack.push("HI");
        Stringstack.push("HELLO");
        System.out.println("Popped Item : " + Stringstack.pop());

        System.out.println("Stack is empty : "+stack.isEmpty());
        System.out.println("StringStack is empty : "+Stringstack.isEmpty());

        GenericStack<Boolean> bStack = new GenericStack<>();
        System.out.println("Exception ERROR : "+bStack.pop());
    }

}