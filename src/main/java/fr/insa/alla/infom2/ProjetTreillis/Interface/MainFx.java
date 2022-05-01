/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis.Interface;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        //FileInputStream inputstream = new FileInputStream("src/main/java/fr/insa/alla/infom2/ProjetTreillis/Interface/appicon.png");
        //Image appicon = new Image(inputstream);

        //primaryStage.getIcons().add(appicon);
        Scene sceneMenu, sceneAction;

        Button importTreillis = new Button("Importer un treillis existant");
        Button creerTreillis = new Button("Créer un nouveau treillis");

        HBox boutonsMenu = new HBox(50);
        boutonsMenu.getChildren().addAll(importTreillis, creerTreillis);
        boutonsMenu.setAlignment(Pos.CENTER);
        sceneMenu = new Scene(boutonsMenu);
        primaryStage.setScene(sceneMenu);

        primaryStage.show();

        Button creerNoeud = new Button("Créer un noeud");
        Button supprNoeud = new Button("Supprimer un noeud");
        Button creerBarre = new Button("Créer une barre");
        Button supprBarre = new Button("Supprimer une barre");
        Button retourButton = new Button("Retour");
        retourButton.setOnAction(e -> primaryStage.setScene(sceneMenu));

        HBox hboxMenuTreillis = new HBox(50);
        hboxMenuTreillis.getChildren().addAll(creerNoeud, supprNoeud, creerBarre, supprBarre);
        hboxMenuTreillis.setAlignment(Pos.CENTER);

        sceneAction = new Scene(hboxMenuTreillis);
        creerTreillis.setOnAction(e -> primaryStage.setScene(sceneAction));

    }

    public static void main(String[] args) {
        launch(args);
    }

}
