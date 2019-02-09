package Controller;

public interface VirtualMachineThread {

    void start();

    void pause();

    void setSleepTime(int time);

    void terminate();

}
