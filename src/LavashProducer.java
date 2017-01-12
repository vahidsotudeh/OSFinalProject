import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Microsoft on 25/12/2016.
 */
public class LavashProducer extends Thread {

    Timer timer;
    String breadName;
    BreadType breadType;
    static Queue<Customer> queue = new ArrayDeque<Customer>(10);
    private Customer currentCustomer;

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

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

    public Customer Customer_In_Front(){
        lock();
        currentCustomer = Bakery.whoIsNext(BreadType.LAVASH);
        unlock();
        return currentCustomer;
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }

    public void addCustomer(Customer customer) throws InterruptedException {
        lock();
        queue.add(customer);
        unlock();
    }

    public void removeCustomer() throws InterruptedException {
        lock();
        Customer customer = queue.poll();
        System.out.println(customer.customerFinishString());
        customer.turnTime = new Date();
        Bakery.Compute_TurnAround_Time(customer);
        unlock();
    }

    public void Baker_In_Back(){
        timer = new Timer();
        lock();
        timer.schedule(new BreadProducer(), 5000, 10000);
        unlock();
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

    public void lock() {

        Thread thread = Thread.currentThread();
        while (!owner.compareAndSet(null, thread)) {
        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        owner.compareAndSet(thread, null);
    }



}
