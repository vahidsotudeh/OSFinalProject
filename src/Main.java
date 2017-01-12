import java.util.Timer;

public class Main {
    //TODO: Just run operations from here
    public static void main(String[] args) {
        Timer timer = new Timer();
        Bakery bakery=new Bakery();
        timer.schedule(bakery,0,10000);


    }
}
