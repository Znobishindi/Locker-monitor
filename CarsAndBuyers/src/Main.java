public class Main {
    public static void main(String[] args) throws InterruptedException {
        final MachineManufacturer machineManufacturer = new MachineManufacturer();
        ThreadGroup threadGroup = new ThreadGroup("Группа покупателей");
        final Buyer buyer1 = new Buyer(threadGroup, "Покупатель 1", machineManufacturer);
        final Buyer buyer2 = new Buyer(threadGroup, "Покупатель 2", machineManufacturer);
        final Buyer buyer3 = new Buyer(threadGroup, "Покупатель 3", machineManufacturer);
        final Buyer buyer4 = new Buyer(threadGroup, "Покупатель 4", machineManufacturer);


        buyer1.start();
        buyer2.start();
        buyer3.start();
        buyer4.start();
        machineManufacturer.start();

        if (machineManufacturer.getCount() == machineManufacturer.getMaxCount()) {
            Thread.sleep(500);
            threadGroup.interrupt();


        }
    }

    }



