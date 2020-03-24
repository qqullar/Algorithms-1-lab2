import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front +++
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("adding null");

        Node newFirst = new Node();

        newFirst.item = item;
        newFirst.prev = null;

        newFirst.next = first;

        // making first
        first = newFirst;
        if (isEmpty()) last = first;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("adding null");

        // makin' last prev
        Node oldLast = last;
        last = new Node();

        last.item = item;
        last.next = null;
        last.prev = oldLast;

        if (isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("remove from empty deque");

        Item item = first.item;

        // here if sz == 1 then here
        // all pointers makes null
        first = first.next;
        size--;

        if (isEmpty()) last = first;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("remove from empty deque");

        Item item = last.item;
        size--;
        if (isEmpty()) {
            last = null;
            first = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("no more items to return");

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> sample = new Deque<>();


        // First pack ----------
        System.out.println("First pack of tests");
        // ***
        // first first
        sample.addFirst("helloworld");
        String tmp = sample.removeFirst();
        System.out.println(tmp);
        // last first
        sample.addLast("helloworld");
        tmp = sample.removeFirst();
        System.out.println(tmp);
        // first last
        sample.addFirst("helloworld");
        tmp = sample.removeLast();
        System.out.println(tmp);
        // last last
        sample.addLast("helloworld");
        tmp = sample.removeLast();
        System.out.println(tmp);


        // Second pack ----------
        System.out.println();
        System.out.println("Second pack of tests");
        // ***
        // 1 3 2
        sample.addLast("1");
        sample.addLast("2");
        sample.addLast("3");
        tmp = sample.removeFirst();
        System.out.println(tmp);
        tmp = sample.removeLast();
        System.out.println(tmp);
        tmp = sample.removeLast();
        System.out.println(tmp);

        System.out.println();
        // 1 2 3
        sample.addLast("1");
        sample.addLast("2");
        sample.addLast("3");
        tmp = sample.removeFirst();
        System.out.println(tmp);
        tmp = sample.removeFirst();
        System.out.println(tmp);
        tmp = sample.removeFirst();
        System.out.println(tmp);

        System.out.println();
        // 3 2 1
        sample.addLast("1");
        sample.addLast("2");
        sample.addLast("3");
        tmp = sample.removeLast();
        System.out.println(tmp);
        tmp = sample.removeLast();
        System.out.println(tmp);
        tmp = sample.removeLast();
        System.out.println(tmp);


        // Third pack ----------
        System.out.println();

        System.out.println("Size:" + sample.size());

        // -------
        for (int i = 0; i < 10; ++i) {
            sample.addFirst(String.valueOf(i));
        }

        System.out.println("Size:" + sample.size());

        System.out.println(sample.size());
        for (String s : sample) {
            System.out.println(s);
        }
        System.out.println();
        for (int i = 0; i < 10; ++i) {
            System.out.println(sample.removeFirst());
        }

        System.out.println("Size:" + sample.size());

        // everything was deleted

        // -------
        for (int i = 0; i < 10; ++i) {
            sample.addLast(String.valueOf(i));
        }

        System.out.println("Size:" + sample.size());

        System.out.println();
        for (String s : sample) {
            System.out.println(s);
        }

        System.out.println("Size:" + sample.size());

        for (int i = 0; i < 10; ++i) {
            System.out.println(sample.removeFirst());
        }

        System.out.println("Size:" + sample.size());


        // Optional tests
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.removeLast()  ;
        deque.addFirst(3);
        deque.addFirst(4);
        deque.removeLast();
    }
}
