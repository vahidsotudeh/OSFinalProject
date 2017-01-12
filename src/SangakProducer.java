import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * Created by Microsoft on 25/12/2016.
 */
public class SangakProducer extends Thread {
    Timer timer;
    String breadName;
    BreadType breadType;
    static Queue<Customer> queue = new ArrayDeque<Customer>(10);
    private Customer currentCustomer;

    SangakProducer(){
        breadName="Sangak";
        breadType=BreadType.SANGAK;
    }

    public Queue<Customer> getQueue() {
        return queue;
    }

    public void run(){
        try {
            Baker_In_Back();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Customer Customer_In_Front() throws InterruptedException {
        currentCustomer = Bakery.whoIsNext(BreadType.SANGAK);
        return currentCustomer;
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }

    public synchronized void addCustomer(Customer customer) throws InterruptedException {
        queue.add(customer);
    }

    public void removeCustomer() throws InterruptedException {
        synchronized (queue) {
            Customer customer = queue.poll();
            System.out.println(customer.customerFinishString());
            customer.turnTime = new Date();
            Bakery.Compute_TurnAround_Time(customer);
        }
    }

    public void Baker_In_Back() throws InterruptedException {
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
