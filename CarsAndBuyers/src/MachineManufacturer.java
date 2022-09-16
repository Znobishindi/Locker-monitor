import java.util.LinkedList;
import java.util.List;

public class MachineManufacturer extends Thread {

    protected final int PRODUCTION_PROCESS_TIME = 1000;
    protected final List<String> cars;
    protected int count = 0;
    protected final int maxCount = 10;

    public int getMaxCount() {
        return maxCount;
    }

    public List<String> getCars() {
        return cars;
    }

    public MachineManufacturer() {
        this.cars = new LinkedList<>();
    }


    public int getCount() {
        return count;
    }

    @Override
    public synchronized void start() {
        for (int i = 0; i < maxCount; i++) {
            try {
                Thread.sleep(PRODUCTION_PROCESS_TIME);
                synchronized (cars) {
                    cars.add("Лада гранта");
                    System.out.println("Автомобиль выпущен");
                    cars.notify();
                    count++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
