import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Microsoft on 25/12/2016.
 */
public class SangakProducer extends Thread {
    //TODO:Sangak bakery
    static Queue<Customer> queue = new PriorityQueue<Customer>(10);

    public Queue<Customer> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }

}
