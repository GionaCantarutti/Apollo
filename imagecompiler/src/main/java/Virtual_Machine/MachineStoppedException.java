package Virtual_Machine;

import javafx.scene.image.Image;

public class MachineStoppedException extends Exception {

    int runningStatus;

    MachineStoppedException(int runningStatus) {
        this.runningStatus = runningStatus;
    }

    @Override
    public String getMessage() {
        return ("runningStatus is " + runningStatus + ": the code terminated the execution of the machine.\n" +
                "If forcing further execution is intended, call the resetRunningStatus() method and then step again");
    }
}
