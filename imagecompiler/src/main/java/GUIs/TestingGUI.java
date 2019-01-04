package GUIs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import java.io.File;

import Controller.*;

public class TestingGUI extends Application implements EventHandler<ActionEvent> {

    Stage primaryStage;

    ControllerInterface controller;

    BorderPane layout;
    HBox buttons;

    Button run;
    Button pause;
    Button step;
    Button reset;
    Button setImage;

    FileChooser fileChooser;

    TextField red;
    TextField green;
    TextField blue;
    Button runColor;

    Label PCPosition;

    File sourceImageFile;
    Image sourceImage;
    ImageView sourceImageView;
    ImageView workingImageView;

    public static void main(String[] args) {

        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        controller = new Controller();

        layout = new BorderPane();
        buttons = new HBox();

        layout.setTop(buttons);

        Scene scene = new Scene(layout, 900, 600);

        primaryStage.setTitle("Apollo testing GUI");

        run = new Button("Run");
        run.setOnAction(this);
        buttons.getChildren().add(run);
        pause = new Button("Pause");
        pause.setOnAction(this);
        buttons.getChildren().add(pause);
        step = new Button("Step");
        step.setOnAction(this);
        buttons.getChildren().add(step);
        reset = new Button("Reset");
        reset.setOnAction(this);
        buttons.getChildren().add(reset);
        setImage = new Button("Set Image");
        setImage.setOnAction(this);
        buttons.getChildren().add(setImage);

        red = new TextField();
        buttons.getChildren().add(red);
        green = new TextField();
        buttons.getChildren().add(green);
        blue = new TextField();
        buttons.getChildren().add(blue);

        runColor = new Button("Run Color");
        runColor.setOnAction(this);
        buttons.getChildren().add(runColor);

        PCPosition = new Label(controller.getPC().x + ", " + controller.getPC().y);
        buttons.getChildren().add(PCPosition);

        sourceImageView = new ImageView();

        workingImageView = new ImageView();
        workingImageView.setImage(controller.getWorkingImage());
        layout.setCenter(workingImageView);

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Source Image");

        primaryStage.setOnCloseRequest(event -> {
            controller.terminate();
            primaryStage.close();
        });

        primaryStage.setScene(scene);

        primaryStage.show();

        //After closing the main window

    }

    public void handle(ActionEvent event) {

        if (event.getSource() == setImage) {

            sourceImageFile = fileChooser.showOpenDialog(primaryStage);

            sourceImage = loadImage(sourceImageFile);

            sourceImageView = new ImageView();
            sourceImageView.setImage(sourceImage);

            controller.setImage(sourceImage);

            update();

        } else if (event.getSource() == step) {

            controller.step();

            update();

        } else if (event.getSource() == reset) {

            controller.reset();

            update();

        } else if (event.getSource() == runColor) {

            int rValue = Integer.parseInt(red.getText());
            int gValue = Integer.parseInt(green.getText());
            int bValue = Integer.parseInt(blue.getText());

            rValue %= 256;
            gValue %= 256;
            bValue %= 256;

            Color c = Color.rgb(rValue, gValue, bValue);
            controller.runColor(c);

            update();

        } else if (event.getSource() == run) {

            controller.run();

        } else if (event.getSource() == pause) {

            controller.stop();

        }

    }

    public void update() {

        buttons.getChildren().remove(PCPosition);
        PCPosition = new Label(controller.getPC().x + ", " + controller.getPC().y);
        buttons.getChildren().add(PCPosition);
        layout.setTop(buttons);

        workingImageView.setImage(controller.getWorkingImage());
        layout.setCenter(workingImageView);

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
}
