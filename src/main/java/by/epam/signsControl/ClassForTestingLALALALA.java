package by.epam.signsControl;

import by.epam.signsControl.config.DBConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.Query;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Test {

    static final Logger rootLogger = LogManager.getRootLogger();
    static final Logger classLogger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {

        System.out.println(DBConfiguration.getInstance().toString());

        Queue<Integer> squ = new PriorityQueue<>(3);
        BlockingQueue<Integer> qu = new ArrayBlockingQueue<>(3);

        System.out.println(qu.size());
        System.out.println(qu.peek());
        System.out.println(qu.poll());
        System.out.println(qu.offer(2));
        System.out.println(qu.offer(5));
        System.out.println(qu.offer(10));
        System.out.println(qu.offer(11));
        System.out.println(qu.remainingCapacity());
        System.out.println("size: " + qu.size());
        System.out.println(qu.peek());
        System.out.println(qu.poll());
        System.out.println(qu.poll());


    }


}
