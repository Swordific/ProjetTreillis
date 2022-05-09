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
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
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
        primaryStage.setResizable(false);

        Image appicon = new Image(getClass().getResourceAsStream("/appicon.png"));
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(appicon);
        Scene mainScene;
        Rectangle2D tailleEcran = Screen.getPrimary().getVisualBounds();
        Rectangle graph = new Rectangle();

        double graphW = tailleEcran.getWidth() - 40;
        double graphH = tailleEcran.getHeight() - 40;

        graph.setX(0);
        graph.setY(0);
        graph.setFill(Color.TRANSPARENT);
        graph.setStroke(Color.BLACK);

        Button importTreillisBouton = new Button("Importer un treillis existant");
        Button creerTreillisBouton = new Button("Créer un nouveau treillis");
        importTreillisBouton.setEffect(new DropShadow());
        creerTreillisBouton.setEffect(new DropShadow());
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
                String filePathImport = fileChooser.showOpenDialog(primaryStage).getPath();
                //Fichier.importTreillis();
            }
        };

        importTreillisMenu.setOnAction(importTreillisEvent);
        importTreillisBouton.setOnAction(importTreillisEvent);

        DirectoryChooser directoryChooser = new DirectoryChooser();

        EventHandler exportTreillisEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String filePathExport = fileChooser.showOpenDialog(primaryStage).getPath();
            }
        };
        exportTreillisMenu.setOnAction(exportTreillisEvent);

        HBox hboxWelcome = new HBox(50);
        hboxWelcome.getChildren().addAll(creerTreillisBouton, importTreillisBouton);
        hboxWelcome.setAlignment(Pos.CENTER);
        Image shrab = new Image(getClass().getResourceAsStream("/companylogo.png"));
        ImageView imageView = new ImageView(shrab);
        imageView.setOpacity(0.35);
        StackPane rootWelcome = new StackPane();
        rootWelcome.getChildren().addAll(imageView, hboxWelcome);

        Menu menuFichier = new Menu("Fichier");
        Menu menuEditer = new Menu("Editer");
        MenuBar menuBar = new MenuBar();
        menuFichier.getItems().addAll(creerTreillisMenu, importTreillisMenu, exportTreillisMenu);
        menuEditer.getItems().addAll(creerNoeud, supprNoeud, creerBarre, supprBarre);
        menuBar.getMenus().addAll(menuFichier, menuEditer);
        VBox menuVBox = new VBox(menuBar);
        menuVBox.setMaxSize(3000, 50);
        mainScene = new Scene(rootWelcome);
        primaryStage.setScene(mainScene);

        Pane graphPane = new Pane();
        graphPane.setMinSize(graphW, graphH);

        graph.setWidth(graphPane.getMinWidth());
        graph.setHeight(graphPane.getMinHeight());
        graphPane.getChildren().add(graph);
        System.out.println(graph.getWidth());
        StackPane rootGraph = new StackPane();
        rootGraph.getChildren().addAll(graph, menuVBox);
        rootGraph.setAlignment(menuVBox, Pos.TOP_LEFT);
        //rootGraph.setAlignment(graphPane, Pos.BOTTOM_LEFT);
        rootGraph.setMargin(graphPane, new Insets(20, 20, 20, 20));

        creerTreillisBouton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.getScene().setRoot(rootGraph);

                Treillis treillis = new Treillis();
                Noeud dep = new NoeudSimple(1, 1);
                Noeud arr = new NoeudSimple(2, 2);
                Barre b = new Barre(dep, arr);
                //Segment.tracerSegment(b);
            }
        });
        //TODO 
        Text t;
        Line l;
        for (int i = 0; i <= 10; i++) {
            l = new Line( graph.getX()+(graphPane.getWidth()/10) * i, 0, graph.getX()+(graphPane.getWidth()/10) * i, graph.getY() + graphPane.getHeight());
            l.setStrokeWidth(10);
            l.setStroke(Color.BLACK);
            rootGraph.getChildren().add(l);
            System.out.println(l.getStartY() + ", " + l.getEndY());

        }

        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
