package medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyTestClass {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        Iterator<Integer> it = new MyIterator(list.iterator());

        while (it.hasNext()) {
            System.out.println("---> " + it.next());
        }
    }
}

class MyIterator implements Iterator<Integer> {

    private final Iterator<Integer> itr;
    Deque<Integer> stack;

    MyIterator(Iterator<Integer> itr) {
        this. itr = itr;
        stack = new LinkedList<>();
    }

    @Override
    public boolean hasNext() {
        while (itr.hasNext()) {
            int nValue = itr.next();
            if(nValue % 2 == 0) {
                stack.push(nValue);
                break;
            }
        }

        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        while (itr.hasNext()) {
            int nValue = itr.next();
            if(nValue % 2 == 0) {
                return nValue;
            }
        }
        return null;
    }
}