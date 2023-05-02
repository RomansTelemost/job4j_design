package ru.job4j.gc.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeakDemo {

    public static void main(String[] args) throws InterruptedException {
        //example1();
        example2();
//    example3();
    }

    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    private static void example2() throws InterruptedException {
//        List<ReferenceQueue<Object>> queue = new ArrayList<>(); //new ReferenceQueue<>();
        ReferenceQueue<Object> queue = new ReferenceQueue<>();

        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 1_000; i++) {
            WeakReference<Object> weakReference = new WeakReference<Object>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    //System.out.println("Removed!");
                }
            }, queue);
            objects.add(weakReference);
            //System.out.println(weakReference);
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);

        int liveObjectCount = 0;
        Object qq = queue.poll();
        System.out.println(qq);
//        for (WeakReference w : objects) {
//            if (w.get() != null) {
//                liveObjectCount++;
//            }
//        }
        System.out.println(liveObjectCount);
    }

    private static void example3() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link " + weak.get());
        System.out.println("from queue " + queue.poll());
    }
}
