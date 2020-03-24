import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

// implementation uses ResizingArray
// TODO: tell the link to book, which implementation has been adopted
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        // using resizing array semantics
        if (size == s.length) resize(2 * s.length);
        s[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size <= 0)
                  throw new java.util.NoSuchElementException();

        // resizing too large array
        if (size == s.length / 4) resize(s.length / 2);

        // detecting non-empty position
        int randPos = StdRandom.uniform(size);
        Item item = s[randPos];
        s[randPos] = s[--size];
        s[size] = null; // loitering

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        int randPos = StdRandom.uniform(size);
        return s[randPos];
    }

    // TODO: add semantics when we must make the array smaller
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < size; ++i)
            copy[i] = s[i];

        s = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        // "randomized" cuz each time we start iteration
        // from randomly chosen pos and the iterates
        // to the right
        private int sz = size;
        private int[] positions = new int[size];
        private int i = 0;

        public RandomizedQueueIterator() {
            for (int j = 0; j < size; j++) {
                positions[j] = j;
            }
            StdRandom.shuffle(positions);
        }

        public boolean hasNext() {
            return sz > 0;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("no more items to return");

            int randPos = positions[i++];
            Item item = s[randPos];
            sz--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueueTester.testEnqueueFunc();
        RandomizedQueueTester.testDequeueFunc();
        RandomizedQueueTester.testEnqueueFunc();
        RandomizedQueueTester.testIsEmptyFunc();
        RandomizedQueueTester.testSampleFunc();
        RandomizedQueueTester.tesIteratorRemoveFunc();
        RandomizedQueueTester.testIteratorNextFunc();

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 100; i++) {
            rq.enqueue(i);
        }

        for(int t : rq) {
            StdOut.print(t);
            System.out.println();
        }


        System.out.println("All tests were passsed");
    }
}

class RandomizedQueueTester {
    public static void testEnqueueFunc() {
        testEnqueueFunc_cornerCase();
    }

    private static void testEnqueueFunc_cornerCase() {
        RandomizedQueue<Integer> sample = new RandomizedQueue<>();
        // test for null
        Integer t = null;
        boolean cond = false;

        try {
            sample.enqueue(t);
        } catch (IllegalArgumentException e) {
            cond = true;
        }

        assert cond : "method should throw exception for null arg";
    }

    public static void testDequeueFunc() {
        testDequeueFunc_cornerCase();

    }

    private static void testDequeueFunc_cornerCase() {
        RandomizedQueue<Integer> sample = new RandomizedQueue<>();

        // Throw a java.util.NoSuchElementException
        // if the client calls either sample() or dequeue()
        // when the randomized queue is empty.
        boolean cond = false;
        try {
            sample.dequeue();
        } catch (java.util.NoSuchElementException e) {
            cond = true;
        }

        assert cond : "method should throw " +
                "java.util.NoSuchElementException" +
                "for empty collection";
    }

    public static void testSampleFunc() {
        testSampleFunc_cornerCase();
    }

    private static void testSampleFunc_cornerCase() {
        RandomizedQueue<Integer> sample = new RandomizedQueue<>();

        // Throw a java.util.NoSuchElementException
        // if the client calls either sample() or dequeue()
        // when the randomized queue is empty.
        boolean cond = false;
        try {
            sample.sample();
        } catch (java.util.NoSuchElementException e) {
            cond = true;
        }

        assert cond : "method should throw " +
                "java.util.NoSuchElementException" +
                "for empty collection";
    }


    public static void testSizeFunc() {
        RandomizedQueue<Integer> sample = new RandomizedQueue<>();
        assert sample.size() == 0 : "method tells wrong for empty collection";

        // add 100 elements
        for (int i = 0; i < 100; ++i)
            sample.enqueue(i);
        assert sample.size() == 100 : "method tells wrong for 100 elements";

        // remove 50 elements then size = 50
        for (int i = 0; i < 50; ++i)
            sample.dequeue();
        assert sample.size() == 50 : "method tells wrong for 50 elements";

        // add 100 elements
        for (int i = 0; i < 100; ++i)
            sample.enqueue(i);
        assert sample.size() == 150 : "method tells wrong for 150 elements";

        // remove all elements
        for (int i = 0; i < 50; ++i)
            sample.dequeue();
        assert sample.size() == 0 : "method tells wrong for 0 elements";
    }

    public static void testIsEmptyFunc() {
        RandomizedQueue<Integer> sample = new RandomizedQueue<>();
        assert sample.isEmpty() : "method tells wrong for empty" +
                "collection";

        // add 100 elements
        for (int i = 0; i < 100; ++i)
            sample.enqueue(i);
        assert !sample.isEmpty() : "method tells wrong" +
                "for non-empty collection";

        // remove 100 elements
        for (int i = 0; i < 100; ++i)
            sample.dequeue();
        assert sample.isEmpty() : "collection should be empty";
    }

    public static void testIteratorNextFunc() {
        testIteratorNextFunc_cornerCase();
    }

    private static void testIteratorNextFunc_cornerCase() {
        RandomizedQueue<Integer> sample = new RandomizedQueue<>();

        // Throw a java.util.NoSuchElementException
        // if the client calls the next() method i
        // n the iterator when there are no more items to return.
        var it = sample.iterator();
        boolean cond = false;
        try {
            it.next();
        } catch (java.util.NoSuchElementException e) {
            cond = true;
        }

        assert cond : "Throw a java.util.NoSuchElementException if the client " +
                "calls the next() method in the iterator " +
                "when there are no more items to return.";

    }

    public static void tesIteratorRemoveFunc() {
        RandomizedQueue<Integer> sample = new RandomizedQueue<>();

        // Throw an UnsupportedOperationException
        // if the client calls the remove()
        // method in the iterator.
        var it = sample.iterator();
        boolean cond = false;
        try {
            it.remove();
        } catch (UnsupportedOperationException e) {
            cond = true;
        }

        assert cond : "Throw an UnsupportedOperationException " +
                "if the client calls the " +
                "remove() method in the iterator.";

    }


}
