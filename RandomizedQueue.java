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

        System.out.println("All tests were passsed");
    }
}

