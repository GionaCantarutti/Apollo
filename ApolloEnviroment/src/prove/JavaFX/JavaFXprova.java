package prove.JavaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class JavaFXprova extends Application {

    Button button;

    ImageView iv1;

    StackPane layout;

    File imageFile;

    EventHandlerFX handler;

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("giusto una prova");

        button = new Button();
        button.setText("Choose a pictcure");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        handler = new EventHandlerFX(button, primaryStage, fileChooser, this);

        button.setOnAction(handler);

        layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 500, 500);

        iv1 = new ImageView();

        if (imageFile != null) {

            System.out.println("image selected");

            iv1.setImage(loadImage(imageFile));
            layout.getChildren().add(iv1);

        }

        primaryStage.setScene(scene);

        primaryStage.show();

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

    public void displayImage() {

        imageFile = handler.getImageFile();

        System.out.println("image selected");

        iv1.setImage(loadImage(imageFile));
        layout.getChildren().add(iv1);

    }
}
