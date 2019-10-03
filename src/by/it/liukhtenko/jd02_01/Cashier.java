package by.it.liukhtenko.jd02_01;

public class Cashier implements Runnable {
    private String name;

    public Cashier(int number) {
        name = ":=) Cashier " + number;
    }

    @Override
    public void run() {
        System.out.println(this + " ОТКРЫЛ КАССУ");
        while (Dispatcher.marketIsOpened()) {
            Buyer buyer = QueueBuyers.extract();
            if (buyer != null) {
                System.out.println(this + " начал обслуживать " + buyer);
                int timeout = Util.random(2000, 5000);
                Util.sleep(timeout);
                System.out.println(this + " закончил обслуживать " + buyer);
                synchronized (buyer) {
                    buyer.notify();
                }
            }
            else {
                Util.sleep(1);
            }
         }
        System.out.println(this+" ЗАКРЫЛ КАССУ");
    }

    @Override
    public String toString() {
        return name;
    }
}
