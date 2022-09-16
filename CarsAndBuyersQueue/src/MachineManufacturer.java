import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MachineManufacturer extends Thread {

    protected final int PRODUCTION_PROCESS_TIME = 2000;
    protected final List<String> cars;
    protected final int maxCount = 10;
    private final ReentrantLock lock;
    private final Condition condition;


    public List<String> getCars() {
        return cars;
    }

    public MachineManufacturer(ReentrantLock lock,Condition condition) {
        this.cars = new LinkedList<>();
        this.lock = lock;
        this.condition = condition;
    }


    @Override
    public synchronized void start() {
        for (int i = 0; i < maxCount; i++) {
            try {
                Thread.sleep(PRODUCTION_PROCESS_TIME);
                lock.lock();
                cars.add("Лада гранта");
                System.out.println("Автомобиль выпущен");
               condition.signal();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
              lock.unlock();
            }

        }
    }
}

