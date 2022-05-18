/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis.Interface;

import fr.insa.alla.infom2.ProjetTreillis.*;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
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

    Group graph = new Group();
    Rectangle2D tailleEcran = Screen.getPrimary().getVisualBounds();

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
        // Rectangle graph = new Rectangle();

        //double graphW = tailleEcran.getWidth() - 40;
        //double graphH = tailleEcran.getHeight() - 40;

        /*graph.setX(0);
        graph.setY(0);
        graph.setFill(Color.TRANSPARENT);
        graph.setStroke(Color.BLACK);*/
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

        /*groupGraph.setMinSize(graphW, graphH);

        graph.setWidth(groupGraph.getMinWidth());
        graph.setHeight(groupGraph.getMinHeight());
        groupGraph.getChildren().add(graph);
        System.out.println(graph.getWidth());*/
        Pane rootGraph = new Pane();
        menuVBox.setPadding(new Insets(0, 0, 0, 0));
        menuVBox.setMinWidth(tailleEcran.getWidth());
        rootGraph.getChildren().addAll(graph, menuVBox);
        //rootGraph.setTopAnchor(menuVBox, 0.0);

        //rootGraph.setAlignment(graph, Pos.BOTTOM_LEFT);
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

        primaryStage.setMaximized(true);
        primaryStage.show();
        //primaryStage.setWidth(tailleEcran.getWidth());
        //primaryStage.setHeight(tailleEcran.getHeight());
        Point2D[] pts = dessinerGraphe();
        Point2D zero = pts[0];
        Point2D topRightPt = pts[1];

        EventHandler testMEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Point2D pt = calcCoord(zero, topRightPt, e.getSceneX(), e.getSceneY());
                //System.out.println("(" + zero.getX() + " ; " + zero.getY() + ")");
                //System.out.println("(" + topRightPt.getX() + " ; " + topRightPt.getY() + ")");
                System.out.println("(" + pt.getX() + " ; " + pt.getY() + ")");

            }
        };

        mainScene.addEventFilter(MouseEvent.MOUSE_CLICKED, testMEvent);

    }

    public Point2D[] dessinerGraphe() {
        double h = tailleEcran.getHeight();
        double w = tailleEcran.getWidth();
        ArrayList<Text> graduations = new ArrayList<>();
        ArrayList<Line> hLines = new ArrayList<>();
        ArrayList<Line> vLines = new ArrayList<>();

        for (int i = 1; i <= 17; i++) {
            if (i % 2 == 0) {
                hLines.add(new Line());
                hLines.get(i / 2 - 1).setStartX(50);
                hLines.get(i / 2 - 1).setStartY(h / 10 * i / 2 + 50);
                hLines.get(i / 2 - 1).setEndX(w - 50);
                hLines.get(i / 2 - 1).setEndY(h / 10 * i / 2 + 50);

                hLines.get(i / 2 - 1).setStrokeWidth(2);
                hLines.get(i / 2 - 1).setStroke(Color.GREY);
                hLines.get(i / 2 - 1).setOpacity(0.4);

                Text t = new Text(25, h / 10 * (i / 2) + 54, Integer.toString((8 - i / 2) * 10));
                t.setFill(Color.GREY);
                t.setOpacity(0.8);
                graduations.add(t);

            }

            vLines.add(new Line());
            vLines.get(i - 1).setStartX(h / 10 * i + 30);
            vLines.get(i - 1).setStartY(60);
            vLines.get(i - 1).setEndX(h / 10 * i + 30);
            vLines.get(i - 1).setEndY(h - 40);

            vLines.get(i - 1).setStrokeWidth(2);
            vLines.get(i - 1).setStroke(Color.GREY);
            vLines.get(i - 1).setOpacity(0.4);

            Text t = new Text(h / 10 * i + 30, h - 20, Integer.toString((i - 1) * 10));
            double tw = t.prefWidth(-1);
            t.setX(t.getX() - tw / 2);
            t.setFill(Color.GREY);
            t.setOpacity(0.8);
            graduations.add(t);

        }
        graph.getChildren().addAll(hLines);
        graph.getChildren().addAll(vLines);
        graph.getChildren().addAll(graduations);
        Bounds zeroBounds = Shape.intersect(hLines.get(hLines.size() - 1), vLines.get(0)).getBoundsInParent();
        Bounds topRightBounds = Shape.intersect(hLines.get(0), vLines.get(vLines.size() - 1)).getBoundsInParent();
        Point2D zero = new Point2D(zeroBounds.getCenterX(), zeroBounds.getCenterY());
        Point2D topRightPt = new Point2D(topRightBounds.getCenterX(), topRightBounds.getCenterY());
        Point2D[] pts = {zero, topRightPt};

        return pts;
    }

    public static Point2D calcCoord(Point2D zero, Point2D trp, double x, double y) {
        Point2D pt;
        double xf, yf;
        x -= zero.getX();
        xf = ((x) / (trp.getX() - zero.getX())) * 160;
        yf = (y / (trp.getY() - zero.getY() / 70)) - zero.getY();
        pt = new Point2D(xf, yf);
        return pt;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
