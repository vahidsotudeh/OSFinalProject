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
    String genderString;

    //default constructor
    public Customer(){
        Random random = new Random();
        breadsNumber = random.nextInt(10)+1;    //between 1-10 for number of breads that customer want
        if(random.nextBoolean()){
            gender = Gender.MALE;
            genderString = "Male";
        }else {
            gender = Gender.FEMALE;
            genderString = "Female";
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

    public String customerComeString(){
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

        return "\nCustomer Comes:\n" +
                "Gender: "+ genderString +"\n" +
                "Bread: "+bread+"\n" +
                "Bread Number Want: "+breadsNumber+"\n" +
                "Arrival Time: "+new Date()+"\n";
    }

    public String customerFinishString(){
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
        return "\nCustomer of "+bread+" finish his/her work!\n";
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
        return "\nCustomer of "+bread+" Arrive at "+comesInTime+", Want "+breadsNumber+"\n";

    }
}
