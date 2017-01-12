import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    static Timer timer = new Timer();
    static Bakery bakery = new Bakery();

    public static void main(String[] args) {
        new Task().run();
        bakery.startSimulation();
    }

    static class Task extends TimerTask {
        public void run() {
            int delay = (1 + new Random().nextInt(60)) * 1000;
            timer.schedule(new Task(), delay);
            bakery.customerComesIn();
        }
    }

}
