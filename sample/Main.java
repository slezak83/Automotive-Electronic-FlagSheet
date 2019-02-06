package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*@author: Nicholas Slezak
* Created on: 1/26/2019
*/

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Technicians Electronic Flag Sheet");
        primaryStage.setScene(new Scene(root, 700, 625));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("sample\\Images\\2017Toyota-Supra.jpg"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
