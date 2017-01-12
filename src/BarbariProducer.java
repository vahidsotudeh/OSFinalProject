import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class BarbariProducer extends Producer {

    //TODO:Barbari bakery
    //static Queue<Customer> queue = new PriorityQueue<Customer>(10, new BarbariCustomerComprator());

    BarbariProducer(){
        breadName = "Barbari";
        breadType = BreadType.BARBARI;
    }

}
