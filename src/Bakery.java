/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class Bakery {
    //TODO: All bakery operation should be here
    //Three bread producer each one has its own queue
    BarbariProducer barbariProducer = new BarbariProducer();
    LavashProducer lavashProducer = new LavashProducer();
    SangakProducer sangakProducer = new SangakProducer();

    public void customerComesIn(){
        //TODO:Implement
        Customer customer = new Customer();
        switch (customer.breadType){
            case BARBARI:{
                barbariProducer.getQueue().add((customer));
            }
            break;
            case LAVASH:{
                lavashProducer.getQueue().add(customer);
            }
            break;
            case SANGAK:{
                sangakProducer.getQueue().add(customer);
            }
            break;
        }
    }

    public Customer whoIsNext(BreadType breadType){
        //TODO:Implement
        Customer customer;
        switch (breadType){
            case BARBARI:{
                customer = barbariProducer.getQueue().poll();
            }
                break;
            case LAVASH:{
                customer = lavashProducer.getQueue().poll();
            }
                break;
            case SANGAK:{
                customer = sangakProducer.getQueue().poll();
            }
                break;
            default:
                customer = null;
        }
        return customer;
    }
}
