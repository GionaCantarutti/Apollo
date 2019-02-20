package zGarbage.prove.JavaFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class EventHandlerFX implements EventHandler<ActionEvent> {

    Button button;
    FileChooser fileChooser;
    Stage primaryStage;

    JavaFXprova caller;

    File image;

    public EventHandlerFX(Button button, Stage primaryStage, FileChooser fileChooser, JavaFXprova caller) {

        this.button = button;

        this.image = null;

        this.primaryStage = primaryStage;
        this.fileChooser = fileChooser;

        this.caller = caller;

    }

    public void handle(ActionEvent event) {

        if (event.getSource() == button) {

            image = fileChooser.showOpenDialog(primaryStage);

            caller.displayImage();

        }

    }

    public File getImageFile() {

        return image;

    }

}
