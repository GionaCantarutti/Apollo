package GUIs;

import ADTs.Adress;
import Observer.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import java.io.File;

import Controller.*;
import sun.security.ssl.Debug;

//ToDo with scene builder
public class DebuggingGUI extends Application{

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("TestingView.fxml"));
        primaryStage.setTitle("Debugging environment for Apollo");
        primaryStage.setScene(new Scene(root, 1800, 800));
        primaryStage.show();

    }

}
