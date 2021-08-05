import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


public class Main {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT+1);
        CountDownLatch cd = new CountDownLatch(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) ( Math.random() * 10 ), cb, cd);
        }

        for (Car car : cars) {
            new Thread(car).start();
        }
        try {
            cb.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            cb.await();
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

