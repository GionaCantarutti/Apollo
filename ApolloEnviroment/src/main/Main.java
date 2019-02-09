package main;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application{

    String PROGRAM_NAME = "Image complier";

    Stage primaryStage;

    File imageFile;
    Image source;
    Image workspace;

    StackPane layout;

    public Button chooseImage;
    public Button startButton;
    public Button pauseButton;
    public Button stopButton;

    ButtonHandler handler;

    ImageView sourceView;
    ImageView workspaceView;


    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        primaryStage.setTitle(PROGRAM_NAME);

        handler = new ButtonHandler(this);

        setupButtons();

    }

    /** Sets up the buttons
     *  modifies the button variables of this class
     */

    private void setupButtons() {

        chooseImage = new Button("Choose image");
        startButton = new Button("Start");
        pauseButton = new Button("Pause");
        stopButton = new Button("Stop");

        chooseImage.setOnAction(handler);
        startButton.setOnAction(handler);
        pauseButton.setOnAction(handler);
        stopButton.setOnAction(handler);

    }

    /**
     *  Called by the handler when an image is choosen
     */

    public void imageLoaded(File imageFile) {

        source = loadImage(imageFile);
        workspace = loadImage(imageFile);

    }

    /**
     * Loads image found in the given
     * @param imageFile is the file containing the image
     * @return image. If loading fails returns null  (should make it throw exception?)
     */

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

}
