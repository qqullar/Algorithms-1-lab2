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