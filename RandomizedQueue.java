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

        // choosing position with non-empty containing
        while (s[pos % s.length] != null)
            pos = (pos + 1) % s.length;

        s[pos] = item;
        ++size;
    }

    // remove and return a random item
    public Item dequeue() {
        // resizing too large array
        if (size > 0 && size == s.length / 4) resize(s.length / 2);

        // detecting non-empty position
        int pos = StdRandom.uniform(s.length);

        // choosing position with non-empty containing
        while (s[pos % s.length] == null)
            pos = (pos + 1) % s.length;

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
            StdRandom.shuffle(s, 0, capacity);
        }

        for (int i = 0; i < s.length; ++i) {
            copy[i] = s[i];
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
        RandomizedQueue<Integer> sample = new RandomizedQueue<>();
        for (int i = 0; i < 100000; ++i)
            sample.enqueue(i);

        for (int i = 0; i < 20; ++i)
            System.out.println(sample.sample());

        System.out.println();
        System.out.println();

//        for (Integer s1 : sample)
//            System.out.println(s1);

        System.out.println();
        System.out.println();


//        for (Integer s2 : sample)
//            System.out.println(s2);

    }
}
