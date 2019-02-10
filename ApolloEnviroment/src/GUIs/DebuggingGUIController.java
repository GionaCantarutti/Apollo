package GUIs;

import ADTs.Adress;
import Controller.Controller;
import Observer.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
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

    //region Virtual Machine variables

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

    //region View variables

    @FXML
    ImageView workingView;

    @FXML
    ImageView sourceView;

    @FXML
    GridPane pointersGrid, flagsGrid;

    @FXML
    Label GUIMessage;

    @FXML
    TextField redField, greenField, blueField;

    //endregion

    //region Controller variables

    @FXML
    AnchorPane dummy;

    FileChooser fileChooser;

    File sourceFile;

    Image sourceImage;

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
        controller.updateObservers();
        //endregion


    }

    //region Callable functions

    @FXML
    void ChooseFile() {

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Source Image");

        sourceFile = fileChooser.showOpenDialog(dummy.getScene().getWindow());

        sourceImage = loadImage(sourceFile);

        controller.setImage(sourceImage);

        sourceView.setImage(sourceImage);
        workingView.setImage(sourceImage);

        updateView();

    }

    @FXML
    void StepVM() {

        controller.step();

        workingView.setImage(controller.getWorkingImage());

        updateView();

    }

    @FXML
    void RunColor() {

        String redS = redField.getText();
        String greenS = greenField.getText();
        String blueS = blueField.getText();

        int red, green, blue;

        try {
            red = Integer.parseInt(redS);
            green = Integer.parseInt(greenS);
            blue = Integer.parseInt(blueS);

            if (red > 255 || green > 255 || blue > 255 || red < 0 || green < 0 || blue < 0) {
                GUIMessage.setText("Values must be within the range 0-255");
            } else {
                controller.runColor(Color.rgb(red, green, blue));
            }

        } catch (Exception e) {
            GUIMessage.setText("Values must be integers!");
        }

    }

    //endregion

    public Image loadImage(File imageFile) {

        try
        {

            Image image = new Image(imageFile.toURI().toString());

            return image;

        } catch ( Exception e ) {

            System.out.println("An error occurred while loading image - " + e.getMessage());

            return null;

        }
    }

    void updateView() {

        updatePointersGrid();
        updateFlagsGrid();
        updateWorkingImage();

    }

    void updateWorkingImage() {

        workingView.setImage(workingImage);

    }

    void updatePointersGrid() {

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                Adress data = pointers[(i * 16) + j];

                Label dataLabel = new Label(data.toString());

                pointersGrid.add(dataLabel, i, j);
            }
        }

    }

    void updateFlagsGrid() {

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                boolean data = flags[(i * 16) + j];

                String dataString;

                if (data) {
                    dataString = "TRUE";
                } else {
                    dataString = "FALSE";
                }

                Label dataLabel = new Label(dataString);

                flagsGrid.add(dataLabel, i, j);
            }
        }

    }

}
