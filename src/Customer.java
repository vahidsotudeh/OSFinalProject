import java.util.Date;
import java.util.Random;

/**
 * Created by hamidrezasahraei on 24/12/16 AD.
 */
public class Customer extends Thread {

    int breadsNumber;
    Gender gender;
    BreadType breadType;
    Date comesInTime;
    Date turnTime;

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
        comesInTime = new Date();
    }

    //constructor for make a customer with customization
    public Customer(int breadsNumber, Gender gender, BreadType breadType){
        this.breadsNumber = breadsNumber;
        this.gender = gender;
        this.breadType = breadType;
        comesInTime = new Date();
    }

    @Override
    public String toString() {
        String bread;
        if(breadType==BreadType.LAVASH){
            bread = "Lavash";
        }else if (breadType==BreadType.SANGAK){
            bread="Sangak";
        }else if (breadType==BreadType.BARBARI){
            bread="Barbari";
        }else {
            bread="Invalid";
        }
        return "Customer of "+bread+"Arrive at "+comesInTime+"\n";

    }
}
