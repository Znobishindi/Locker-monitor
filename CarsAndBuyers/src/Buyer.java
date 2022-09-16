public class Buyer extends Thread {

    private final MachineManufacturer machineManufacturer;
    private final Integer TIME_UNTIL_THE_NEXT_PURCHASE = 500;

    public Buyer(ThreadGroup group, String name, MachineManufacturer machineManufacturer) {
        super(group, name);
        this.machineManufacturer = machineManufacturer;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {

            try {
                Thread.sleep(TIME_UNTIL_THE_NEXT_PURCHASE);
                synchronized (machineManufacturer.getCars()) {
                    System.out.println(getName() + " пришел в автосалон");
                    if (!machineManufacturer.getCars().isEmpty()) {
                        System.out.println(getName() + " уехал домой на новенькой " + machineManufacturer.getCars().remove(0));
                        machineManufacturer.getCars().notify();
                    } else {
                        System.out.println("Машин нет");
                    }
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}


