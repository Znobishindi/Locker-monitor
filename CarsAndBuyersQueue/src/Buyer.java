import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buyer extends Thread {

    private final MachineManufacturer machineManufacturer;
    private final Integer TIME_UNTIL_THE_NEXT_PURCHASE = 1000;
    protected Queue<String> buyerQueue = new PriorityQueue<>();
    private final ReentrantLock lock;
    private Condition condition;

    public Buyer(ThreadGroup group, String name, MachineManufacturer machineManufacturer, ReentrantLock lock, Condition condition) {
        super(group, name);
        this.machineManufacturer = machineManufacturer;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        while (!isInterrupted()) {

            try {
                buyerQueue.offer(getName());
                Thread.sleep(TIME_UNTIL_THE_NEXT_PURCHASE);
                lock.lock();

                if (machineManufacturer.getCars().isEmpty()) {
                    System.out.println(getName() + " пришел в автосалон");
                    System.out.println("Машин нет");
                }
                while (machineManufacturer.getCars().isEmpty()) {
                    condition.await();
                }
                if (Objects.equals(getName(), buyerQueue.poll())) {
                    System.out.println(getName() + " уехал домой на новенькой " + machineManufacturer.getCars().remove(0));
                    condition.signalAll();
                }

            } catch (InterruptedException e) {
                break;
            } finally {
                lock.unlock();
            }
        }
    }
}





