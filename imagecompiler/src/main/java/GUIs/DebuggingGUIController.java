package GUIs;

import ADTs.Adress;
import Controller.Controller;
import Observer.*;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DebuggingGUIController implements Initializable, Observer {

    Controller controller;

    //region Observer variables
    public boolean withImage;
    public boolean withStandardOutput;
    public boolean withPointer;
    public boolean withPointers;
    public boolean withFlags;
    public boolean withPC;
    public boolean withError;
    public boolean withOffset;
    public boolean withStatus;
    //endregion

    //region Controller variables

    private String standardOutput;
    private boolean runningStatus;
    private Image workingImage;
    private Adress pointer;
    private Adress[] pointers;
    private Boolean[] flags;
    private Adress PC;
    private boolean ERROR;
    private int xOffset;
    private int yOffset;

    //endregion

    //region Observer

    public void setObserverOptions(DebuggingGUIController.ObserverSetup s) {
        this.withError = s.withError;
        this.withFlags = s.withFlags;
        this.withImage = s.withImage;
        this.withOffset = s.withOffset;
        this.withPC = s.withPC;
        this.withPointer = s.withPointer;
        this.withPointers = s.withPointers;
        this.withStandardOutput = s.withStandardOutput;
        this.withStatus = s.withStatus;
    }

    public static class ObserverSetup {

        DebuggingGUIController g;

        public boolean withImage;
        public boolean withStandardOutput;
        public boolean withPointer;
        public boolean withPointers;
        public boolean withFlags;
        public boolean withPC;
        public boolean withError;
        public boolean withOffset;
        public boolean withStatus;

        ObserverSetup(DebuggingGUIController gui) {
            g = gui;
            withImage = false;
            withStandardOutput = false;
            withPointer = false;
            withPointers = false;
            withFlags = false;
            withPC = false;
            withError = false;
            withOffset = false;
            withStatus = false;
        }

        void set() {
            g.setObserverOptions(this);
        }

        DebuggingGUIController.ObserverSetup withImage() {
            withImage = true;
            return this;
        }
        DebuggingGUIController.ObserverSetup withStandardOutput() {
            withStandardOutput = true;
            return this;
        }
        DebuggingGUIController.ObserverSetup withPointer() {
            withPointer = true;
            return this;
        }
        DebuggingGUIController.ObserverSetup withPointers() {
            withPointers = true;
            return this;
        }
        DebuggingGUIController.ObserverSetup withFlags() {
            withFlags = true;
            return this;
        }
        DebuggingGUIController.ObserverSetup withPC() {
            withPC = true;
            return this;
        }
        DebuggingGUIController.ObserverSetup withError() {
            withError = true;
            return this;
        }
        DebuggingGUIController.ObserverSetup withOffset() {
            withOffset = true;
            return this;
        }
        DebuggingGUIController.ObserverSetup withStatus() {
            withStatus = true;
            return this;
        }

    }

    public void updateImage(Image workingImage) {
        this.workingImage = workingImage;
    }

    public void updateStandardOutput(String standardOutput) {
        this.standardOutput = standardOutput;
    }

    public void updatePointer(Adress pointer) {
        this.pointer = pointer;
    }

    public void updatePointers(Adress[] pointers) {
        this.pointers = pointers;
    }

    public void updateFlags(Boolean[] flags) {
        this.flags = flags;
    }

    public void updatePC(Adress PC) {
        this.PC = PC;
    }

    public void updateError(boolean ERROR) {
        this.ERROR = ERROR;
    }

    public void updateOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void updateStatus(boolean runningStatus) {
        this.runningStatus = runningStatus;
    }

    @Override
    public boolean isWithImage() {
        return withImage;
    }

    @Override
    public boolean isWithPC() {
        return withPC;
    }

    @Override
    public boolean isWithPointer() {
        return withPointer;
    }

    @Override
    public boolean isWithPointers() {
        return withPointers;
    }

    @Override
    public boolean isWithFlags() {
        return withFlags;
    }

    @Override
    public boolean isWithOffset() {
        return withOffset;
    }

    @Override
    public boolean isWithError() {
        return withError;
    }

    @Override
    public boolean isWithStatus() {
        return withStatus;
    }

    @Override
    public boolean isWithStandardOutput() {
        return withStandardOutput;
    }

    //endregion

    ImageView MainImage;

    public void initialize(URL location, ResourceBundle resources) {

        controller = new Controller();



        //region setting up and adding Observer
        new DebuggingGUIController.ObserverSetup(this)
                .withPointers()
                .withPointer()
                .withStandardOutput()
                .withError()
                .withFlags()
                .withImage()
                .withOffset()
                .withPC()
                .withStatus()
                .set();

        controller.addObserver(this);
        //endregion


    }

    //region Callable functions

    void StepVM() {

        controller.step();

        MainImage.setImage(controller.getWorkingImage());

    }

    //endregion

}
