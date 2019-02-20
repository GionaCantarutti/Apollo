package GUIs;

import ADTs.Adress.Adress;
import Controller.Controller;
import Observer.Observer;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    Stage primaryStage;

    @FXML
    ImageView displayImageView;

    @FXML
    GridPane pointersGrid, flagsGrid;

    @FXML
    GridPane virtualMachineProprieties;

    @FXML
    Label GUIMessage, VMVersionLabel;

    @FXML
    TextField redField, greenField, blueField;

    @FXML
    Button ShowSourceButton;

    @FXML
    HBox inspectorValues1, inspectorValues2, inspectorValues3, inspectorValues4;

    @FXML
    Label PCcolorLabel, PointerColorLabel, MainFlagValueLabel, inspectorLabel1, inspectorLabel2, inspectorLabel3, inspectorLabel4;

    Label Standard_Output;
    Label Program_Counter;
    Label Main_Pointer;
    Label X_Offset;
    Label Y_Offset;
    Label Error_Bit;
    Label Status;

    Label[][] pointersLabels, flagsLabels;

    HBox[] boxes;
    Label[] labels;

    Image toDisplayImage;

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

        toDisplayImage = workingImage;

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

        //region initializing virtual machine proprieties

        VMVersionLabel.setText("Virtual Machine version " + controller.getVMVersion());

        Standard_Output = new Label(standardOutput);
        Program_Counter = new Label(PC.toString());
        Main_Pointer = new Label(pointer.toString());
        X_Offset = new Label(xOffset + "");
        Y_Offset = new Label(yOffset + "");
        if (ERROR) {
            Error_Bit = new Label("TRUE");
        } else {
            Error_Bit = new Label("FALSE");
        }
        if (runningStatus) {
            Status = new Label("STOPPED");
        } else {
            Status = new Label("RUNNING");
        }

        virtualMachineProprieties.add(Standard_Output, 1, 0);
        virtualMachineProprieties.add(Program_Counter, 1, 1);
        virtualMachineProprieties.add(Main_Pointer, 1, 2);
        virtualMachineProprieties.add(X_Offset, 1, 3);
        virtualMachineProprieties.add(Y_Offset, 1, 4);
        virtualMachineProprieties.add(Error_Bit, 1, 5);
        virtualMachineProprieties.add(Status, 1, 6);

        flagsLabels = new Label[16][16];
        pointersLabels = new Label[16][16];
        for(int i = 0; i < 16; i++) {
            for(int j = 0; j < 16; j++) {
                flagsLabels[i][j] = new Label("FLASE");
                pointersLabels[i][j] = new Label("(0,0)");
                flagsGrid.add(flagsLabels[i][j], i, j);
                pointersGrid.add(pointersLabels[i][j], i, j);
            }
        }
        //endregion

        //region Initialize inspector variables

        boxes = new HBox[4];
        boxes[0] = inspectorValues1;
        boxes[1] = inspectorValues2;
        boxes[2] = inspectorValues3;
        boxes[3] = inspectorValues4;
        labels = new Label[4];
        labels[0] = inspectorLabel1;
        labels[1] = inspectorLabel2;
        labels[2] = inspectorLabel3;
        labels[3] = inspectorLabel4;

        //endregion

    }

    //region Callable functions

    @FXML
    void updateLabels() {

        PixelReader reader = workingImage.getPixelReader();

        for (int j = 0; j < boxes.length; j++) {

            HBox b = boxes[j];
            Label l = labels[j];

            int coords[] = new int[2];

            for (int i = 0; i < 2; i++) {
                TextField t = (TextField)b.getChildren().get(i);
                String text = t.getText();
                try {
                    coords[i] = Integer.parseInt(text);
                } catch (Exception e) {
                    //nothin'
                }
            }

            l.setText(colorToString(reader.getColor(coords[0], coords[1])));

        }

    }

    @FXML
    void CloseWindow() {
        primaryStage.close();
    }

    @FXML
    void LoadDummy() {
        int SIZE = 512;
        WritableImage writableImage = new WritableImage(512, 512);
        PixelWriter writer = writableImage.getPixelWriter();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                writer.setColor(i, j, Color.rgb(0, 0, 0));
            }
        }
        sourceImage = writableImage;
        workingImage = writableImage;
        controller.setImage(writableImage);
        displayImageView.setImage(writableImage);
        updateView();
        SourceSwitch();
        SourceSwitch();
    }

    //ToDo
    @FXML
    void ExportWorkingImage() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG File", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            saveImageToFile(workingImage, file);
        }

    }

    //ToDo
    @FXML
    void DisplayInformation() {}

    //ToDo
    @FXML
    void OpenDocumentation() {}

    @FXML
    void ResetVM() {
        controller.reset();
        workingImage = sourceImage;
        updateView();
    }

    @FXML
    void ResetToSource() {
        controller.reset();
        controller.setImage(sourceImage);
        updateView();
        SourceSwitch();
        SourceSwitch();
    }

    @FXML
    void ChooseFile() {

        ResetVM();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Source Image");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.gif", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);

        sourceFile = fileChooser.showOpenDialog(primaryStage);

        sourceImage = loadImage(sourceFile);

        controller.setImage(sourceImage);

        displayImageView.setImage(sourceImage);

        updateView();
        SourceSwitch();
        SourceSwitch();

    }

    @FXML
    void ChooseFileAndStep() {

        ResetVM();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Source Image");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.gif", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);

        sourceFile = fileChooser.showOpenDialog(primaryStage);

        sourceImage = loadImage(sourceFile);

        controller.setImage(sourceImage);

        displayImageView.setImage(sourceImage);

        updateView();
        StepVM();
        SourceSwitch();
        SourceSwitch();

    }

    @FXML
    void StepVM() {

        controller.step();
        updateView();

    }

    @FXML
    void SourceSwitch() {
        if (toDisplayImage == sourceImage) {
            toDisplayImage = workingImage;
            ShowSourceButton.setText("Show Source Image");
        } else {
            toDisplayImage = sourceImage;
            ShowSourceButton.setText("Show Working Image");
        }
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
                GUIMessage.setText("");
                updateView();
            }

        } catch (Exception e) {
            GUIMessage.setText("Values must be integers!");
        }

    }

    //endregion

    public void saveImageToFile(Image image, File file) {

        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

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

    public String colorToString(Color c) {

        return "(" + (int)(c.getRed()*255) + ", " + (int)(c.getGreen()*255) + ", " + (int)(c.getBlue()*255) + ")";

    }

    public void setStage(Stage s) {
        primaryStage = s;
    }

    void updateView() {

        updatePointersGrid();
        updateFlagsGrid();
        updateWorkingImage();
        updateValues();
        updateInspectors();
        updateLabels();

    }

    void updateInspectors() {

        PixelReader reader = workingImage.getPixelReader();

        PCcolorLabel.setText(colorToString(reader.getColor(PC.x + xOffset, PC.y + yOffset)));
        PointerColorLabel.setText(colorToString(reader.getColor(pointer.x + xOffset, pointer.y + yOffset)));
        if (flags[0]) {
            MainFlagValueLabel.setText("TRUE");
        } else {
            MainFlagValueLabel.setText("FALSE");
        }

    }

    void updateValues() {

        Standard_Output.setText(standardOutput);
        Program_Counter.setText(PC.toString());
        Main_Pointer.setText(pointer.toString());
        X_Offset.setText(xOffset + "");
        Y_Offset.setText(yOffset + "");
        if (ERROR) {
            Error_Bit.setText("TRUE");
        } else {
            Error_Bit.setText("FALSE");
        }
        if (runningStatus) {
            Status.setText("RUNNING");
        } else {
            Status.setText("STOPPED");
        }

    }

    void updateWorkingImage() {

        displayImageView.setImage(toDisplayImage);

    }

    void updatePointersGrid() {

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                Adress data = pointers[(j * 16) + i];

                pointersLabels[i][j].setText(data.toString());
            }
        }

    }

    void updateFlagsGrid() {

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {

                boolean data = flags[(j * 16) + i];

                String dataString;

                if (data) {
                    dataString = "TRUE";
                } else {
                    dataString = "FALSE";
                }

                flagsLabels[i][j].setText(dataString);
            }
        }

    }

}
