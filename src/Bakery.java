/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class Bakery {
    //TODO: All bakery operation should be here
    //Three bread producer each one has its own queue
    static BarbariProducer barbariProducer;
    static LavashProducer lavashProducer;
    static SangakProducer sangakProducer;
    Bakery(){
        barbariProducer = new BarbariProducer();
        lavashProducer = new LavashProducer();
        sangakProducer = new SangakProducer();
    }

    public void startSimulation(){
        barbariProducer.start();
        lavashProducer.start();
        sangakProducer.start();
    }

    public void customerComesIn(){
        //TODO:Implement
        Customer customer = new Customer();
        System.out.println(customer);
        switch (customer.breadType){
            case BARBARI:{
                barbariProducer.addCustomer(customer);
            }
            break;
            case LAVASH:{
                lavashProducer.addCustomer(customer);
            }
            break;
            case SANGAK:{
                sangakProducer.addCustomer(customer);
            }
            break;
        }
    }

    public static Customer whoIsNext(BreadType breadType){
        //TODO:Implement
        Customer customer;
        switch (breadType){
            case BARBARI:{
                customer = barbariProducer.getQueue().peek();
            }
                break;
            case LAVASH:{
                customer = lavashProducer.getQueue().peek();
            }
                break;
            case SANGAK:{
                customer = sangakProducer.getQueue().peek();
            }
                break;
            default:
                customer = null;
        }
        return customer;
    }
}
