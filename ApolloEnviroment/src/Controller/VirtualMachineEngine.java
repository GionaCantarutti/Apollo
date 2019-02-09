package Controller;

import Virtual_Machine.VMInterface;

public class VirtualMachineEngine implements VirtualMachineThread, Runnable{

    private final int DEFAULT_SLEEP_TIME = 10;

    boolean running;

    int sleepTime;

    boolean toTerminate;

    VMInterface virtualMachine;

    Controller controller;

    public VirtualMachineEngine(Controller controller) {

        running = false;
        sleepTime = DEFAULT_SLEEP_TIME;
        toTerminate = false;
        this.virtualMachine = controller.virtualMachine;
        this.controller = controller;

    }

    public void start() {

        System.out.println("Started execution of engine");

        running = true;

    }

    public void pause() {

        System.out.println("Paused execution of engine");

        running = false;

    }

    public void setSleepTime(int time) {

        sleepTime = time;

    }

    public void terminate() {

        System.out.println("Termination of thread initialized");

        toTerminate = true;

    }

    public void run() {

        System.out.println("run method started");

        try {

            while (true) {

                //For whatever reason removing this print breaks everything
                System.out.print("");

                if (toTerminate) {
                    System.out.println("Thread terminated");
                    break;
                }

                if (running) {

                    System.out.println("stepped, sleeping for: " + sleepTime);

                    try {
                        virtualMachine.step();
                        controller.updateObservers();
                    } catch (Exception e) {

                        System.out.println("Error occurred while stepping the machine: " + e.getMessage());

                    }

                    Thread.sleep(sleepTime);

                }

            }

        } catch (Exception e) {

            System.out.println("Error occurred while executing the thread: " + e.getMessage());

        }

    }

}
