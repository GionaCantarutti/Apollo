package GUIs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//ToDo with scene builder
public class DebuggingGUI extends Application{

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/DbuggingView.fxml"));
            Parent root = fxmlLoader.load();
            primaryStage.setTitle("Debugging environment for Apollo");
            primaryStage.setScene(new Scene(root, 1800, 800));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
