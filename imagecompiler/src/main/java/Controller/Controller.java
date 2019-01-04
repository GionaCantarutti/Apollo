package Controller;

import GUIs.TestingGUI;
import Virtual_Machine.*;

import ADTs.Adress;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Controller implements ControllerInterface{

    VMInterface virtualMachine;
    Image sourceImage;
    VirtualMachineThread VMT;

    public Controller() {
        sourceImage = new WritableImage(256, 256);
        virtualMachine = new VirtualMachine(sourceImage);
        VMT = new VirtualMachineEngine(virtualMachine);

        Thread VMthread = new Thread((Runnable)VMT);

        VMthread.start();
    }

    public void step() {
        try {
            virtualMachine.step();
        } catch (InvalidImageException invalidImage) {
            virtualMachine.setImage(sourceImage);
            step();
        } catch (MachineStoppedException machineStopped) {
            System.out.println("machine is stopped");
        }
    }

    //ToDo
    public void run() {

        //System.out.println("called controller run");

        VMT.start();

    }

    //ToDo
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
}
