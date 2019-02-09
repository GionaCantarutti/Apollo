package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

import java.io.File;


public class ButtonHandler implements EventHandler<ActionEvent> {

    Main main;
    FileChooser fileChooser;

    File imageFile;

    public ButtonHandler(Main main) {

        this.main = main;

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open source image");

    }

    public void handle(ActionEvent event) {

        Object source = event.getSource();

        if (source == main.chooseImage) {

            imageFile = fileChooser.showOpenDialog(main.primaryStage);

            main.imageLoaded(imageFile);

        } else if (source == main.startButton) {



        } else if (source == main.pauseButton) {



        } else if (source == main.stopButton) {



        }

    }

}
