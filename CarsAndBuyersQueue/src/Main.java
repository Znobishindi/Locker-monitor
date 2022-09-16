import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        final MachineManufacturer machineManufacturer = new MachineManufacturer(lock, condition);
        ThreadGroup threadGroup = new ThreadGroup("Группа покупателей");
        final Buyer buyer1 = new Buyer(threadGroup, "Покупатель 1", machineManufacturer, lock, condition);
        final Buyer buyer2 = new Buyer(threadGroup, "Покупатель 2", machineManufacturer, lock, condition);
        final Buyer buyer3 = new Buyer(threadGroup, "Покупатель 3", machineManufacturer, lock, condition);
        final Buyer buyer4 = new Buyer(threadGroup, "Покупатель 4", machineManufacturer, lock, condition);

        buyer1.start();
        buyer2.start();
        buyer3.start();
        buyer4.start();
        machineManufacturer.start();

        if (!machineManufacturer.isAlive()) {
            threadGroup.interrupt();
            System.out.println();


        }
    }

}



