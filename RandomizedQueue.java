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

    // TODO: transform next lines in one function
    // detecting non-empty position
//    int pos = StdRandom.uniform(s.length);
//
//    // choosing position with non-empty containing
//        while (s[pos % s.length] == null)
//    pos = (pos + 1) % s.length;

    // add the item
    public void enqueue(Item item) {
        // using resizing array semantics
        if (size == s.length) resize(2 * s.length);

        // detecting non-empty position
        int pos = StdRandom.uniform(s.length);
        while (s[pos] != null) {
            pos = StdRandom.uniform(s.length);
        }

        s[pos] = item;
        ++size;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size <= 0)
            throw new IllegalArgumentException();

        // resizing too large array
        if (size == s.length / 4) resize(s.length / 2);

        // detecting non-empty position
        int pos = StdRandom.uniform(s.length);
        while (s[pos] == null) {
            pos = StdRandom.uniform(s.length);
        }

        Item item = s[pos];
        s[pos] = null;
        --size;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        // detecting non-empty position
        int pos = StdRandom.uniform(s.length);

        // choosing position with non-empty containing
        while (s[pos % s.length] == null)
            pos = (pos + 1) % s.length;

        return s[pos];
    }


    // TODO: add semantics when we must make the array smaller
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        if (capacity < s.length) {
            for (int i = 0, j = 0; i < s.length && j < size; ++i) {
                if (s[i] != null) {
                    // if i = 1 & j = 0 we have
                    // copy[0] = s[1]
                    // then j+= 1
                    copy[j++] = s[i];
//                    StdRandom.shuffle(s, 0, capacity);
                }
            }
        } else {
            for (int i = 0; i < s.length; ++i) {
                copy[i] = s[i];
            }
        }

        s = copy;
    }

    private int max(int x, int y) {
        if (x > y) return x;
        else return y;
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

        // unique starting position for each iterator
        private int pos = StdRandom.uniform(s.length);

        public boolean hasNext() {
            return sz > 0;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("no more items to return");

            // choosing position with non-empty containing
            while (s[pos % s.length] == null)
                pos = (pos + 1) % s.length;

            Item item = s[pos++];
            --sz;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

//        long m = System.currentTimeMillis();
//        System.out.println();
//        RandomizedQueue<Integer> sample = new RandomizedQueue<>();
//        for (int i = 0; i < 10000000; ++i)
//            sample.enqueue(i);
//        System.out.println();
//        System.out.println((double) (System.currentTimeMillis() - m));
//
//        System.out.println();
//        System.out.println();
//        long m1 = System.currentTimeMillis();
//        for (int i = 0; i < 5000000; ++i)
//            System.out.println(sample.dequeue());
//        System.out.println();
//        System.out.println();
//        System.out.println((double) (System.currentTimeMillis() - m1));
//
//
//        System.out.println();
//        System.out.println();
//
////        for (Integer s1 : sample)
////            System.out.println(s1);
//
//        System.out.println();
//        System.out.println();
//
//
//        System.out.println();
//        System.out.println();
//        long m12 = System.currentTimeMillis();
//        for (Integer s2 : sample)
//            System.out.println(s2);
//        System.out.println((double) (System.currentTimeMillis() - m12));
//        System.out.println();
//        System.out.println();
//
//        System.out.println((double) (System.currentTimeMillis() - m));


        RandomizedQueue<Integer> sample = new RandomizedQueue<>();
        sample.enqueue(1);
        sample.enqueue(2);
        sample.enqueue(3);
        sample.enqueue(4);
        sample.enqueue(5);
        sample.enqueue(6);
        sample.enqueue(7);
        sample.enqueue(8);
        sample.enqueue(9);

        sample.dequeue();
        sample.dequeue();
        sample.dequeue();
        sample.dequeue();

        sample.dequeue();
        sample.dequeue();


    }
}
