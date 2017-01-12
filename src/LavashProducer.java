import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * Created by Microsoft on 25/12/2016.
 */
public class LavashProducer extends Thread {

    Timer timer;
    String breadName;
    BreadType breadType;
    static Queue<Customer> queue = new ArrayDeque<Customer>(10);
    private Customer currentCustomer;

    LavashProducer(){
        breadName="Lavash";
        breadType=BreadType.LAVASH;
    }

    public Queue<Customer> getQueue() {
        return queue;
    }

    public void run(){
        Baker_In_Back();
    }

    public synchronized Customer Customer_In_Front(){
        currentCustomer = Bakery.whoIsNext(BreadType.LAVASH);
        return currentCustomer;
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }

    public void addCustomer(Customer customer) throws InterruptedException {
        synchronized (queue) {
            queue.add(customer);
        }
    }

    public void removeCustomer() throws InterruptedException {
        synchronized (queue) {
            Customer customer = queue.poll();
            System.out.println(customer.customerFinishString());
            customer.turnTime = new Date();
            Bakery.Compute_TurnAround_Time(customer);
        }
    }

    public void Baker_In_Back(){
        timer = new Timer();
        synchronized (queue) {
            timer.schedule(new BreadProducer(), 5000, 10000);
        }
    }

    class BreadProducer extends TimerTask {

        public void run() {
            if(!queue.isEmpty()) {
                System.out.println(breadName + " Produced At " + new Date());
                Customer customer = Bakery.whoIsNext(breadType);
                customer.breadsNumber--;
                if (customer.breadsNumber == 0) {
                    try {
                        removeCustomer();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



}
