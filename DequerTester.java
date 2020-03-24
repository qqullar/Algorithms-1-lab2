// deque unit testing
public class DequerTester {
    public static void test() {
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

    }
}
