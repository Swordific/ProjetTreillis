/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis.Interface;

import fr.insa.alla.infom2.ProjetTreillis.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 *
 * @author Asus
 */
public class MainFx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Treillis");
        //primaryStage.setWidth(1280);
        //primaryStage.setHeight(720);
        //primaryStage.setResizable(true);

        //FileInputStream inputstream = new FileInputStream(MainFx.class.getClassLoader().getResourceAsStream("src/main/java/fr/insa/alla/infom2/ProjetTreillis/Interface/appicon.png"));
        //Image appicon = new Image(getClass().getResourceAsStream("/bruh/appicon.png"));
        //primaryStage.getIcons().add(appicon);
        Scene sceneMain;

        Button importTreillisBouton = new Button("Importer un treillis existant");
        Button creerTreillisBouton = new Button("Créer un nouveau treillis");
        MenuItem importTreillisMenu = new MenuItem("Importer un treillis existant");
        MenuItem exportTreillisMenu = new MenuItem("Exporter le treillis actif");
        MenuItem creerTreillisMenu = new MenuItem("Créer un nouveau treillis");
        MenuItem creerNoeud = new MenuItem("Créer un noeud");
        MenuItem supprNoeud = new MenuItem("Supprimer un noeud");
        MenuItem creerBarre = new MenuItem("Créer une barre");
        MenuItem supprBarre = new MenuItem("Supprimer une barre");
        FileChooser fileChooser = new FileChooser();
        EventHandler importTreillisEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String filePath = fileChooser.showOpenDialog(primaryStage).getPath();
                //Fichier.importTreillis();
            }
        };

        importTreillisMenu.setOnAction(importTreillisEvent);
        importTreillisBouton.setOnAction(importTreillisEvent);

        DirectoryChooser directoryChooser = new DirectoryChooser();

        EventHandler exportTreillisEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String filePath = fileChooser.showOpenDialog(primaryStage).getPath();
            }
        };
        exportTreillisMenu.setOnAction(exportTreillisEvent);

        HBox hboxWelcome = new HBox(50);
        hboxWelcome.getChildren().addAll(creerTreillisBouton, importTreillisBouton);
        hboxWelcome.setAlignment(Pos.CENTER);

        Menu menuFichier = new Menu("Fichier");
        Menu menuEditer = new Menu("Editer");
        MenuBar menuBar = new MenuBar();
        menuFichier.getItems().addAll(creerTreillisMenu, importTreillisMenu, exportTreillisMenu);
        menuEditer.getItems().addAll(creerNoeud, supprNoeud, creerBarre, supprBarre);
        menuBar.getMenus().addAll(menuFichier, menuEditer);
        VBox menuVBox = new VBox(menuBar);
        menuVBox.setMaxSize(3000, 50);
        sceneMain = new Scene(hboxWelcome);
        primaryStage.setScene(sceneMain);

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        LineChart xy = new LineChart(x, y);
        StackPane rootGraph = new StackPane();
        rootGraph.getChildren().addAll(xy, menuVBox);
        rootGraph.setAlignment(menuVBox, Pos.TOP_LEFT);
        rootGraph.setAlignment(xy, Pos.BOTTOM_LEFT);
        rootGraph.setMargin(xy, new Insets(20, 5, 5, 5));

        creerTreillisBouton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.getScene().setRoot(rootGraph);
                Treillis treillis = new Treillis();
                Noeud dep = new NoeudSimple(1, 1);
                Noeud arr = new NoeudSimple(2, 2);
                Barre b = new Barre(dep, arr);
                Segment.tracerSegment(b);
            }
        });
        primaryStage.setMaximized(true);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
