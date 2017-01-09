import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class BarbariProducer extends Thread {
    //TODO:Barbari bakery
    static Queue<Customer> queue = new PriorityQueue<Customer>(10, new BarbariCustomerComprator());

    public static Queue<Customer> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }
}
