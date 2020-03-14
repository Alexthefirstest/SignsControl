package by.epam.signsControl;

import by.epam.signsControl.connectionPool.IConnectionPool;
import by.epam.signsControl.connectionPool.impl.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ClassForTestingLALALALA {

    static final Logger rootLogger = LogManager.getRootLogger();
    static final Logger classLogger = LogManager.getLogger(ClassForTestingLALALALA.class);

    public static void main(String[] args) throws SQLException {

//        System.out.println(DBConfiguration.getInstance().toString());
//
////        Queue<Integer> squ = new PriorityQueue<>(3);
//        BlockingQueue<Integer> qu = new ArrayBlockingQueue<>(3);
////
//        System.out.println(qu.size());
//        System.out.println(qu.peek());
//        System.out.println(qu.poll());
//        System.out.println(qu.size());
//        System.out.println(qu.offer(2));
//        System.out.println(qu.size());
//        qu.offer(null);
//        System.out.println(qu.size());
////        System.out.println(qu.offer(5));
//        System.out.println(qu.offer(10));
//        System.out.println(qu.offer(11));
//        System.out.println(qu.remainingCapacity());
//        System.out.println("size: " + qu.size());
//        System.out.println(qu.peek());
//        System.out.println(qu.poll());
//        System.out.println(qu.poll());

     IConnectionPool cp=ConnectionPool.getConnectionPoolInstance();
     Connection connection = cp.retrieveConnection();
        System.out.println(cp);
        cp.destroyConnectionPool();
        System.out.println(cp.retrieveConnection());
        // cp.retrieveConnection();
//        System.out.println(cp);
//        Connection connection = cp.retrieveConnection();
//        System.out.println(connection);
//        System.out.println(cp);
//        System.out.println(cp.releaseConnection(connection));
//        System.out.println(cp);

//        for(int i=0; i<40; i++) {
//            System.out.println(cp.retrieveConnection());
//            System.out.println(cp);
//            System.out.println("_________________"+i);
//        }


    }


}
