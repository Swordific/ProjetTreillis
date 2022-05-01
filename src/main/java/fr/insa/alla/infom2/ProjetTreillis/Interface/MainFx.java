/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis.Interface;


import java.io.FileInputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class MainFx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Treillis");
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setResizable(true);

        FileInputStream inputstream = new FileInputStream("src/main/java/fr/insa/alla/infom2/ProjetTreillis/Interface/appicon.png");
        Image appicon = new Image(inputstream);

        //primaryStage.getIcons().add(appicon);
        Button importTreillis = new Button("Importer un treillis existant");
        Button creerTreillis = new Button("Créer un nouveau treillis");
        //creerTreillis.setOnAction(primaryStage.setScene(sceneAction));

        HBox boutonsMenu = new HBox(50);
        boutonsMenu.getChildren().addAll(importTreillis, creerTreillis);
        boutonsMenu.setAlignment(Pos.CENTER);
        Scene sceneMenu = new Scene(boutonsMenu, 1280, 720);
        primaryStage.setScene(sceneMenu);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
