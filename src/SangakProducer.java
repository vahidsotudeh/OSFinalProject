import java.util.*;

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
        Baker_In_Back();
    }

    public Customer Customer_In_Front(){
        currentCustomer = Bakery.whoIsNext(BreadType.SANGAK);
        return currentCustomer;
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }

    public void addCustomer(Customer customer){
        queue.add(customer);
    }

    public void removeCustomer(){
        Customer customer = queue.poll();
        System.out.println(customer.customerFinishString());
        customer.turnTime = new Date();
    }

    public void Baker_In_Back(){
        timer = new Timer();
        timer.schedule(new BreadProducer(),5000,10000);
    }

    class BreadProducer extends TimerTask {

        public void run() {
            if(!queue.isEmpty()) {
                System.out.println(breadName + " Produced At " + new Date());
                Customer customer = Bakery.whoIsNext(breadType);
                customer.breadsNumber--;
                if (customer.breadsNumber == 0) {
                    removeCustomer();
                }
            }
        }
    }


}
