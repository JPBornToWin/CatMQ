package catMQ;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> start()).start();
    }

    public static void start() {
        System.out.println("   ___     ___    _____  __  __   ___\n" +
                "  / __|   /   \\  |_   _||  \\/  | / _ \\\n" +
                " | (__    | - |    | |  | |\\/| || (_) |\n" +
                "  \\___|   |_|_|   _|_|_ |_|__|_| \\__\\_\\\n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
