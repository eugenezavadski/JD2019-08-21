package by.it.zavadski.jd02_06;

public class Client {
    public static void main(String[] args) {
        Logger logger=Logger.getLogger();
        logger.log("Main Tread");
        for (int i = 0; i <= 5; i++) {
            new ClientThread(i).start();
            logger.log("LogRecord"+i);
        }
}}