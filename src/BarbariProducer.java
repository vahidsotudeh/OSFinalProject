import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class BarbariProducer extends Thread {
    //TODO:Barbari bakery
    Queue<Customer> queue = new PriorityQueue<>(10, new BarbariCustomerComprator());
}
