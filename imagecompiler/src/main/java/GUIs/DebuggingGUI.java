package GUIs;

import Observer.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

//ToDo with scene builder
public class DebuggingGUI extends Application {

    Observer VMObserver;
    Controller controller;

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        initializeObserver();
        controller = new Controller();

        ((Observable)controller).addObserver(VMObserver);

    }

    private void initializeObserver() {
        VMObserver = new VMObserver();
        VMObserver.withError();
        VMObserver.withFlags();
        VMObserver.withImage();
        VMObserver.withOffset();
        VMObserver.withPC();
        VMObserver.withPointer();
        VMObserver.withPointers();
        VMObserver.withStandardOutput();
        VMObserver.withStatus();
    }

}
