import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class BarbariProducer extends Thread {
    Timer timer;
    String breadName;
    BreadType breadType;
    //for first timing algorithm
    static Queue<Customer> queue = new PriorityQueue<Customer>(10, new BarbariCustomerComprator());

    //for second timing algorithm
    static ArrayList<Customer> queue2 = new ArrayList<Customer>(10);
    int yekiNum = 0;
    int femaleNum = 0;
    int maleNum = 0;

    boolean isRoundRobbin = true;


    BarbariProducer(){
        breadName="Barbari";
        breadType=BreadType.BARBARI;
    }

    public Queue<Customer> getQueue() {
        return queue;
    }

    public ArrayList<Customer> getQueue2() {
        return queue2;
    }

    public void run(){
        produceBread();
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }

    public void addCustomer(Customer customer){
        if(isRoundRobbin){
            queue2.add(customer);
        }else {
            queue.add(customer);
        }
    }

    public void removeCustomer(Customer customerRemove){
        Customer customer;
        if(isRoundRobbin){
            customer = queue2.remove(queue2.indexOf(customerRemove));
        }else {
            customer = queue.poll();
        }
        System.out.println(customer+"Finished!");
        customer.turnTime = new Date();
        Bakery.Compute_TurnAround_Time(customer);
    }

    public void produceBread(){
        // And From your main() method or any other method
        timer = new Timer();
        timer.schedule(new BreadProducer(),0,10000);
    }

    public Customer roundRobbinAlgorithm(){
        for(Customer customer : queue2){
            if(customer.breadsNumber == 1 && yekiNum < 2){
                yekiNum++;
                return customer;
            }
        }
        for(Customer customer : queue2){
            if(customer.gender == Gender.FEMALE && femaleNum<2){
                femaleNum++;
                return customer;
            }
        }
        for(Customer customer : queue2){
            if(customer.gender == Gender.MALE && maleNum<2){
                maleNum++;
                return customer;
            }
        }
        yekiNum = 0;
        femaleNum = 0;
        maleNum = 0;
        return queue2.get(0);
    }

    class BreadProducer extends TimerTask {

        public void run() {
//            if(!queue.isEmpty()) {
//                System.out.println(breadName + " Produced At "+new Date());
//                Customer customer = Bakery.whoIsNext(breadType);
//                customer.breadsNumber--;
//                if (customer.breadsNumber == 0) {
//                    removeCustomer();
//                }
//            }
            if(!queue2.isEmpty()) {
                System.out.println(breadName + " Produced At "+new Date());
                Customer customer = Bakery.whoIsNext2(breadType);
                customer.breadsNumber--;
                if (customer.breadsNumber == 0) {
                    removeCustomer(customer);
                }
            }
        }
    }




}
