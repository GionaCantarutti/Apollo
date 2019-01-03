package Controller;

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

    public Controller() {
        sourceImage = new WritableImage(256, 256);
        virtualMachine = new VirtualMachine(sourceImage);
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

    }

    //ToDo
    public void stop() {

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
}
