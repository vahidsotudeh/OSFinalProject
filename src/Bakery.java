/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class Bakery {
    //TODO: All bakery operation should be here
    //Three bread producer each one has its own queue
    BarbariProducer barbariProducer = new BarbariProducer();

    public void customerComesIn(){
        //TODO:Implement
        Customer customer = new Customer();
    }

    public Customer whoIsNext(BreadType breadType){
        //TODO:Implement
        Customer customer;
        switch (breadType){
            case BARBARI:{
                customer = BarbariProducer.getQueue().poll();
            }
                break;
            case LAVASH:{
                customer = LavashProducer.getQueue().poll();
            }
                break;
            case SANGAK:{
                customer = SangakProducer.getQueue().poll();
            }
                break;
            default:
                customer = null;
        }
        return customer;
    }
}
