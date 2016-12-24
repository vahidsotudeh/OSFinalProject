import java.util.Random;

/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class Customer extends Thread {

    int breadsNumber;
    Gender gender;
    BreadType breadType;

    //default constructor
    public Customer(){
        Random random = new Random();
        breadsNumber = random.nextInt(10)+1;    //between 1-10 for number of breads that customer want
        if(random.nextBoolean()){
            gender = Gender.MALE;
        }else {
            gender = Gender.FEMALE;
        }
        int chance = random.nextInt(3);
        if(chance == 0){
            breadType = BreadType.BARBARI;
        }else if(chance == 1){
            breadType = BreadType.LAVASH;
        }else if(chance == 2){
            breadType = BreadType.SANGAK;
        }
    }

    //constructor for make a customer with customization
    public Customer(int breadsNumber, Gender gender, BreadType breadType){
        this.breadsNumber = breadsNumber;
        this.gender = gender;
        this.breadType = breadType;
    }

}
