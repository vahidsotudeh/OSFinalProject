import java.util.*;

/**
 * Created by Microsoft on 25/12/2016.
 */
public class SangakProducer extends Thread {
    Timer timer;
    String breadName;
    BreadType breadType;
    static Queue<Customer> queue = new ArrayDeque<Customer>(10);

    SangakProducer(){
        breadName="Sangak";
        breadType=BreadType.SANGAK;
    }

    public Queue<Customer> getQueue() {
        return queue;
    }

    public void run(){
        produceBread();
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }

    public void addCustomer(Customer customer){
        queue.add(customer);
    }

    public void removeCustomer(){
        Customer customer = queue.poll();
        System.out.println(customer+" finished.");
        customer.turnTime = new Date();
        Bakery.Compute_TurnAround_Time(customer);
    }

    public void produceBread(){
        // And From your main() method or any other method
        timer = new Timer();
        timer.schedule(new BreadProducer(),0,10000);
    }

    class BreadProducer extends TimerTask {

        public void run() {
            if(!queue.isEmpty()) {
                System.out.println(breadName + " Produced.");
                Customer customer = Bakery.whoIsNext(breadType);
                customer.breadsNumber--;
                if (customer.breadsNumber == 0) {
                    removeCustomer();
                }
            }
        }
    }


}
