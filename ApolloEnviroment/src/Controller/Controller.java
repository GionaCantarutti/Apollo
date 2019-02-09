package Controller;

import ADTs.Adress;
import Observer.Observable;
import Observer.Observer;
import Virtual_Machine.InvalidImageException;
import Virtual_Machine.MachineStoppedException;
import Virtual_Machine.VMInterface;
import Virtual_Machine.VirtualMachine;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controller implements ControllerInterface, Observable {

    VMInterface virtualMachine;
    Image sourceImage;
    VirtualMachineThread VMT;

    String standardOutput;

    ArrayList<Observer> observers;

    public Controller() {
        sourceImage = new WritableImage(256, 256);
        virtualMachine = new VirtualMachine(sourceImage);
        VMT = new VirtualMachineEngine(this);

        //Thread VMthread = new Thread((Runnable)VMT);

        //VMthread.start();

        observers = new ArrayList<Observer>();
    }

    public void step() {
        try {
            virtualMachine.step();
            updateObservers();
        } catch (InvalidImageException invalidImage) {
            virtualMachine.setImage(sourceImage);
            step();
        } catch (MachineStoppedException machineStopped) {
            standardOutput = "Machine is currently stopped, cannot step forward";
            System.out.println("machine is stopped");
        }
    }


    public void run() {

        //System.out.println("called controller run");

        VMT.start();

    }


    public void stop() {

        VMT.pause();

    }

    public void setImage(Image image) {
        sourceImage = image;
        virtualMachine.setImage(image);
    }

    public void reset() {
        virtualMachine.reset();
        virtualMachine.setImage(sourceImage);
    }

    public Image getWorkingImage() {
        return virtualMachine.getCurrentImage();
    }

    public Image getSourceImage() {
        return sourceImage;
    }

    public Adress getPC() {
        return virtualMachine.getPC();
    }

    public Adress getPointer() {
        return virtualMachine.getPointer();
    }

    public boolean getERROR() {
        return virtualMachine.getERROR();
    }

    public void runColor(Color c) {
        virtualMachine.runColor(c);
    }

    public void terminate() {

        VMT.terminate();

    }

    //region observable

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }



    private void updateObserver(Observer o) {

        if(o.isWithStandardOutput()) {
            o.updateStandardOutput(standardOutput);
        }


        if (o.isWithError()) {
            o.updateError(virtualMachine.getERROR());
        }
        if (o.isWithFlags()) {
            o.updateFlags(virtualMachine.getFlags());
        }
        if (o.isWithImage()) {
            o.updateImage(virtualMachine.getCurrentImage());
        }
        if (o.isWithOffset()) {
            o.updateOffset(virtualMachine.getXOffset(), virtualMachine.getYOffset());
        }
        if (o.isWithPC()) {
            o.updatePC(virtualMachine.getPC());
        }
        if (o.isWithPointer()) {
            o.updatePointer(virtualMachine.getPointer());
        }
        if (o.isWithPointers()) {
            o.updatePointers(virtualMachine.getBackgroundPointers());
        }
        if (o.isWithStatus()) {
            o.updateStatus(virtualMachine.getRunningStatus());
        }
    }

    public void updateObservers() {
        for (Observer o: observers) {
            updateObserver(o);
        }
    }

    //endregion
}
