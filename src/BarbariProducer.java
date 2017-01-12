import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class BarbariProducer extends Thread {
    Customer currentCustomer;
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

    Semaphore semaphore = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);


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
        Baker_In_Back();
    }

    public void setQueue(Queue<Customer> queue) {
        this.queue = queue;
    }

    public Customer Customer_In_Front(){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isRoundRobbin){
            currentCustomer = Bakery.whoIsNext2(BreadType.BARBARI);
        }else {
            currentCustomer = Bakery.whoIsNext(BreadType.BARBARI);
        }
        mutex.release();
        semaphore.release();

        return currentCustomer;
    }

    public void Baker_In_Back(){
        timer = new Timer();
        try {
            semaphore.acquire();
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.schedule(new BreadProducer(),5000,10000);
        mutex.release();
    }

    public void addCustomer(Customer customer){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isRoundRobbin){
            queue2.add(customer);
        }else {
            queue.add(customer);
        }
        mutex.release();
        semaphore.release();
    }

    public void removeCustomer(Customer customerRemove){
        Customer customer;
        try {
            semaphore.acquire();
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(isRoundRobbin){
            customer = queue2.remove(queue2.indexOf(customerRemove));
        }else {
            customer = queue.poll();
        }
        mutex.release();
        System.out.println(customer.customerFinishString());
        customer.turnTime = new Date();
        Bakery.Compute_TurnAround_Time(customer);
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
            if(!isRoundRobbin) {
                if (!queue.isEmpty()) {
                    System.out.println(breadName + " Produced At " + new Date());

                    Customer customer = Customer_In_Front();
                    customer.breadsNumber--;
                    if (customer.breadsNumber == 0) {
                        removeCustomer(customer);
                    }
                }
            }else {
                if (!queue2.isEmpty()) {
                    System.out.println(breadName + " Produced At " + new Date());
                    Customer customer = Customer_In_Front();
                    customer.breadsNumber--;
                    if (customer.breadsNumber == 0) {
                        removeCustomer(customer);
                    }
                }
            }
        }
    }




}
