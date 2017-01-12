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
        Customer customer = new Customer();
        System.out.println(customer.customerComeString());
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

        Customer customer = null;
        switch (breadType){
            case BARBARI:{
                if(!barbariProducer.getQueue().isEmpty()) {
                    customer = barbariProducer.getQueue().peek();
                }
            }
                break;
            case LAVASH:{
                if(!lavashProducer.getQueue().isEmpty()) {
                    customer = lavashProducer.getQueue().peek();
                }
            }
                break;
            case SANGAK:{
                if(!sangakProducer.getQueue().isEmpty()) {
                    customer = sangakProducer.getQueue().peek();
                }
            }
                break;
            default:
                customer = null;
        }
        return customer;
    }

    public static Customer whoIsNext2(BreadType breadType){

        Customer customer = null;
        switch (breadType){
            case BARBARI:{
                if(!barbariProducer.getQueue2().isEmpty()) {
                    customer = barbariProducer.roundRobbinAlgorithm();
                }
            }
            break;
            case LAVASH:{
                if(!lavashProducer.getQueue().isEmpty()) {
                    customer = lavashProducer.getQueue().peek();
                }
            }
            break;
            case SANGAK:{
                if(!sangakProducer.getQueue().isEmpty()) {
                    customer = sangakProducer.getQueue().peek();
                }
            }
            break;
            default:
                customer = null;
        }
        return customer;
    }
}
