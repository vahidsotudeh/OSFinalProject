import java.util.Comparator;

/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class BarbariCustomerComprator implements Comparator<Customer> {
    //for customizing priorities of barbari queue
    @Override
    public int compare(Customer o1, Customer o2) {
        if(o1.breadsNumber == 1){
            return 1;
        }
        if(o1.gender == Gender.FEMALE && o2.gender == Gender.MALE){
            return 1;
        }
        if(o1.gender == Gender.MALE){
            return -1;
        }
        return 0;
    }
}
