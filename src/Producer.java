import java.util.*;

/**
 * Created by hamidrezasahraei on 12/1/17 AD.
 */
public class Producer extends Thread {
    Timer timer;
    String breadName;
    BreadType breadType;
    static Queue<Customer> queue = new ArrayDeque<Customer>(10);

    public Queue<Customer> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }

    public void addCustomer(Customer customer){
        if(queue.isEmpty()){
            produceBread();
        }
        queue.add(customer);
    }

    public void removeCustomer(){
        Customer customer = queue.poll();
        customer.turnTime = new Date();
        if(queue.isEmpty()) {
            timer.cancel();
            timer.purge();
        }
    }

    public void produceBread(){
        // And From your main() method or any other method
        timer = new Timer();
        timer.schedule(new BreadProducer(), 0, 30000);
    }

    class BreadProducer extends TimerTask {

        public void run() {
            System.out.println(breadName + " Produced.");
            Customer customer = Bakery.whoIsNext(breadType);
            customer.breadsNumber--;
            if(customer.breadsNumber == 0){
                removeCustomer();
            }
        }
    }

}
