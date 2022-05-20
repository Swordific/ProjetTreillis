/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis.Interface;

import fr.insa.alla.infom2.ProjetTreillis.*;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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

    private Group graph = new Group();
    private Group graphGrille = new Group();
    private Group graphObjets = new Group();
    private Rectangle2D tailleEcran = Screen.getPrimary().getVisualBounds();
    private Treillis treillis = new Treillis();

    Point2D zero, topRightPt;

    Image appuiSimple = new Image(getClass().getResourceAsStream("/appui_simple.png"), 24, 24, true, true);
    Image appuiDouble = new Image(getClass().getResourceAsStream("/appui_double.png"), 24, 24, true, true);
    Image noeudSimple = new Image(getClass().getResourceAsStream("/noeud_simple.png"), 24, 24, true, true);

    ArrayList<ImageView> appuiSimpleImages = new ArrayList<>();
    ArrayList<ImageView> appuiDoubleImages = new ArrayList<>();
    ArrayList<ImageView> noeudSimpleImages = new ArrayList<>();
    ArrayList<Line> barresList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Treillis");
        primaryStage.setResizable(false);
        Scene mainScene;
        graph.getChildren().addAll(graphGrille, graphObjets);

        //Images
        Image appicon = new Image(getClass().getResourceAsStream("/appicon.png"));
        Image shrab = new Image(getClass().getResourceAsStream("/companylogo.png"));
        ImageView shrabView = new ImageView(shrab);
        shrabView.setOpacity(0.35);

        //Numeroteurs
        Numeroteur numAppuiSimple = new Numeroteur();
        Numeroteur numAppuiDouble = new Numeroteur();
        Numeroteur numNoeudSimple = new Numeroteur();

        //Boutons/Menus/Dialogs
        Button importTreillisBouton = new Button("Importer un treillis existant");
        Button creerTreillisBouton = new Button("Créer un nouveau treillis");

        MenuItem importTreillisMenu = new MenuItem("Importer un treillis existant");
        MenuItem exportTreillisMenu = new MenuItem("Exporter le treillis actif");
        MenuItem creerTreillisMenu = new MenuItem("Créer un nouveau treillis");
        MenuItem creerNoeudMenu = new MenuItem("Créer un noeud");
        MenuItem supprNoeudMenu = new MenuItem("Supprimer un noeud");
        MenuItem creerBarreMenu = new MenuItem("Créer une barre");
        MenuItem supprBarreMenu = new MenuItem("Supprimer une barre");

        Menu menuFichier = new Menu("Fichier");
        Menu menuEditer = new Menu("Editer");

        ToggleGroup boutonsAjout = new ToggleGroup();
        ToggleButton btnNoeudSimple = new ToggleButton("", new ImageView(noeudSimple));
        btnNoeudSimple.setUserData("noeudSimple");
        ToggleButton btnAppuiSimple = new ToggleButton("", new ImageView(appuiSimple));
        btnAppuiSimple.setUserData("appuiSimple");
        ToggleButton btnAppuiDouble = new ToggleButton("", new ImageView(appuiDouble));
        btnAppuiDouble.setUserData("appuiDouble");
        boutonsAjout.getToggles().addAll(btnNoeudSimple, btnAppuiSimple, btnAppuiDouble);

        MenuBar menuBar = new MenuBar();
        ToolBar toolBar = new ToolBar();
        HBox barHBox = new HBox(menuBar, toolBar);

        menuBar.setPrefHeight(36);
        menuBar.setPadding(new Insets(8, 0, 8, 0));
        toolBar.setPrefHeight(36);
        barHBox.setMinWidth(tailleEcran.getWidth());
        barHBox.setHgrow(toolBar, Priority.ALWAYS);

        importTreillisBouton.setEffect(new DropShadow());
        creerTreillisBouton.setEffect(new DropShadow());
        menuFichier.getItems().addAll(creerTreillisMenu, importTreillisMenu, exportTreillisMenu);
        menuEditer.getItems().addAll(creerNoeudMenu, supprNoeudMenu, creerBarreMenu, supprBarreMenu);
        menuBar.getMenus().addAll(menuFichier, menuEditer);
        toolBar.getItems().addAll(btnNoeudSimple, btnAppuiSimple, btnAppuiDouble);

        ArrayList<String> typesNoeuds = new ArrayList<>();
        typesNoeuds.add("Noeud Simple");
        typesNoeuds.add("Noeud Appui Simple");
        typesNoeuds.add("Noeud Appui Double");
        ChoiceDialog<String> choixNoeudDialog = new ChoiceDialog<>(typesNoeuds.get(0), typesNoeuds);
        Dialog<double[]> creationNoeudDialog = new Dialog<>();

        //Menu de départ/déclaration écran principal
        primaryStage.getIcons().add(appicon);
        HBox hboxWelcome = new HBox(50);
        hboxWelcome.getChildren().addAll(creerTreillisBouton, importTreillisBouton);
        hboxWelcome.setAlignment(Pos.CENTER);

        StackPane rootWelcome = new StackPane();
        rootWelcome.getChildren().addAll(shrabView, hboxWelcome);

        mainScene = new Scene(rootWelcome);
        primaryStage.setScene(mainScene);

        Pane rootPane = new Pane();
        rootPane.getChildren().addAll(barHBox, graph);

        Point2D[] pts = dessinerGraphe();
        Point2D zero = pts[0];
        Point2D topRightPt = pts[1];

        //Ecran principal
        primaryStage.setMaximized(true);
        primaryStage.show();
        Noeud n1 = new NoeudSimple(0, 0);
        Noeud n2 = new NoeudSimple(10, 10);
        treillis.getNoeuds().add(n1);
        treillis.getNoeuds().add(n2);
        Barre bb = new Barre(n1, n2);
        treillis.getBarres().add(bb);
        dessinerContenu(treillis);

        //Events et set boutons
        creerTreillisBouton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.getScene().setRoot(rootPane);
            }
        });

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

        EventHandler ajouterAppuiSimple = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Point2D pt = calcCoord(e.getSceneX(), e.getSceneY());
                NoeudAppuiSimple n = new NoeudAppuiSimple(pt.getX(), pt.getY());;
                n.setId(numAppuiSimple.add(n));
                treillis.ajouteNoeud(n);
                System.out.println(treillis.getNoeuds());

                dessinerContenu(treillis);
            }
        };
        EventHandler ajouterAppuiDouble = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Point2D pt = calcCoord(e.getSceneX(), e.getSceneY());
                NoeudAppuiDouble n = new NoeudAppuiDouble(pt.getX(), pt.getY());;
                n.setId(numAppuiDouble.add(n));
                treillis.ajouteNoeud(n);
                System.out.println(treillis.getNoeuds());

                dessinerContenu(treillis);
            }
        };
        EventHandler ajouterNoeudSimple = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Point2D pt = calcCoord(e.getSceneX(), e.getSceneY());
                NoeudSimple n = new NoeudSimple(pt.getX(), pt.getY());
                n.setId(numNoeudSimple.getOrAdd(n));
                treillis.ajouteNoeud(n);
                System.out.println(treillis.getNoeuds());

                dessinerContenu(treillis);
            }
        };

        creationNoeudDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        choixNoeudDialog.setTitle("Choix de noeud");
        choixNoeudDialog.setHeaderText(null);
        choixNoeudDialog.setContentText("Choisissez le type voulu :");
        choixNoeudDialog.setGraphic(null);

        creationNoeudDialog.setTitle("Création de noeud");
        GridPane creationNoeudGrid = new GridPane();
        creationNoeudGrid.setHgap(10);
        creationNoeudGrid.setVgap(10);
        creationNoeudGrid.setPadding(new Insets(10, 10, 10, 10));
        TextField noeudPxField = new TextField();
        creationNoeudGrid.add(noeudPxField, 1, 0);
        creationNoeudGrid.add(new Label("Entrez l'abscisse du noeud"), 0, 0);
        TextField noeudPyField = new TextField();
        creationNoeudGrid.add(noeudPyField, 1, 1);
        creationNoeudGrid.add(new Label("Entrez l'ordonnée du noeud"), 0, 1);
        TextField noeudFxField = new TextField();
        creationNoeudGrid.add(noeudFxField, 1, 2);
        creationNoeudGrid.add(new Label("Entrez la force horizontale"), 0, 2);
        TextField noeudFyField = new TextField();
        creationNoeudGrid.add(noeudFyField, 1, 3);
        creationNoeudGrid.add(new Label("Entrez la force verticale"), 0, 3);

        creationNoeudDialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                double[] out = {Double.parseDouble(noeudPxField.getText()), Double.parseDouble(noeudPyField.getText()), Double.parseDouble(noeudFxField.getText()), Double.parseDouble(noeudFyField.getText())};
                return out;
            }
            return null;
        });

        creationNoeudDialog.getDialogPane().setContent(creationNoeudGrid);

        EventHandler creerNoeudEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                Optional<String> typeNoeud = choixNoeudDialog.showAndWait();
                Optional<double[]> noeudInfo = creationNoeudDialog.showAndWait();
                typeNoeud.ifPresent(type -> {
                    noeudInfo.ifPresent(data -> {
                        double px = data[0];
                        double py = data[1];
                        Vecteur2D f = new Vecteur2D(data[2], data[3]);
                        Noeud n;
                        switch (type) {
                            case "Noeud Simple":
                                n = new NoeudSimple(px, py, f);
                                n.setId(numNoeudSimple.getOrAdd(n));
                                treillis.ajouteNoeud(n);
                                break;
                            case "Noeud Appui Simple":
                                n = new NoeudAppuiSimple(px, py, f);
                                n.setId(numAppuiSimple.getOrAdd(n));
                                treillis.ajouteNoeud(n);
                                break;
                            case "Noeud Appui Double":
                                n = new NoeudAppuiDouble(px, py, f);
                                n.setId(numAppuiDouble.getOrAdd(n));
                                treillis.ajouteNoeud(n);
                                break;
                        }
                        System.out.println(treillis.getNoeuds());

                        dessinerContenu(treillis);
                    });
                });
            }
        };

        creerNoeudMenu.setOnAction(creerNoeudEvent);

        boutonsAjout.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null) {
                    graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterNoeudSimple);
                    graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiSimple);
                    graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiDouble);
                } else {
                    switch ((String) boutonsAjout.getSelectedToggle().getUserData()) {
                        case "noeudSimple":
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiDouble);
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiSimple);
                            graphGrille.addEventFilter(MouseEvent.MOUSE_CLICKED, ajouterNoeudSimple);
                            break;
                        case "appuiSimple":
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiDouble);
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterNoeudSimple);
                            graphGrille.addEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiSimple);
                            break;
                        case "appuiDouble":
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterNoeudSimple);
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiSimple);
                            graphGrille.addEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiDouble);
                            break;
                    }
                }
            }
        });
    }

    public Point2D[] dessinerGraphe() {
        double h = tailleEcran.getHeight();
        double w = tailleEcran.getWidth();
        ArrayList<Text> graduations = new ArrayList<>();
        ArrayList<Line> hLines = new ArrayList<>();
        ArrayList<Line> vLines = new ArrayList<>();
        Rectangle rect = new Rectangle();
        rect.setFill(Color.TRANSPARENT);

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
        graphGrille.getChildren().addAll(hLines);
        graphGrille.getChildren().addAll(vLines);
        graphGrille.getChildren().addAll(graduations);
        Bounds zeroBounds = Shape.intersect(hLines.get(hLines.size() - 1), vLines.get(0)).getBoundsInParent();
        Bounds topRightBounds = Shape.intersect(hLines.get(0), vLines.get(vLines.size() - 1)).getBoundsInParent();
        zero = new Point2D(zeroBounds.getCenterX(), zeroBounds.getCenterY());
        topRightPt = new Point2D(topRightBounds.getCenterX(), topRightBounds.getCenterY());
        Point2D[] pts = {zero, topRightPt};
        rect.setX(hLines.get(0).getStartX());
        rect.setY(vLines.get(0).getStartY());
        rect.setWidth(hLines.get(0).getEndX() - hLines.get(0).getStartX());
        rect.setHeight(vLines.get(0).getEndY() - vLines.get(0).getStartY());
        graphGrille.getChildren().add(rect);

        return pts;
    }

    public void dessinerContenu(Treillis t) {
        graphObjets.getChildren().removeAll();
        for (Noeud n : t.getNoeuds()) {
            dessinerNoeud(n);
        }
        for (Barre b : t.getBarres()) {
            dessinerBarre(b);
        }
    }

    public void dessinerNoeud(Noeud n) {
        int type = n.nbrInconnues();
        Point2D xy = calcCoordR(n.getPx(), n.getPy());
        switch (type) {
            case 0:
                ImageView noeudSimpleView = new ImageView(noeudSimple);
                noeudSimpleView.setFitWidth(16);
                noeudSimpleView.setFitHeight(16);
                noeudSimpleView.setX(xy.getX() - 8);
                noeudSimpleView.setY(xy.getY() - 8);
                noeudSimpleImages.add(noeudSimpleView);
                graphObjets.getChildren().removeAll(noeudSimpleImages);
                graphObjets.getChildren().addAll(noeudSimpleImages);
                break;

            case 1:
                ImageView appuiSimpleView = new ImageView(appuiSimple);
                appuiSimpleView.setFitWidth(32);
                appuiSimpleView.setFitHeight(32);
                appuiSimpleView.setX(xy.getX() - 16);
                appuiSimpleView.setY(xy.getY() - 5);
                appuiSimpleImages.add(appuiSimpleView);
                graphObjets.getChildren().removeAll(appuiSimpleImages);
                graphObjets.getChildren().addAll(appuiSimpleImages);
                break;

            case 2:
                ImageView appuiDoubleView = new ImageView(appuiDouble);
                appuiDoubleView.setFitWidth(32);
                appuiDoubleView.setFitHeight(32);
                appuiDoubleView.setX(xy.getX() - 16);
                appuiDoubleView.setY(xy.getY() - 4);
                appuiDoubleImages.add(appuiDoubleView);
                graphObjets.getChildren().removeAll(appuiDoubleImages);
                graphObjets.getChildren().addAll(appuiDoubleImages);
                break;
        }
    }

    public void dessinerBarre(Barre b) {
        Noeud nd = b.getNoeudDepart();
        Noeud na = b.getNoeudArrivee();
        Point2D pd = calcCoordR(nd.getPx(), nd.getPy());
        Point2D pa = calcCoordR(na.getPx(), na.getPy());
        Line l = new Line(pd.getX(), pd.getY(), pa.getX(), pa.getY());
        l.setStrokeWidth(5);
        barresList.add(l);
        graphObjets.getChildren().removeAll(barresList);
        graphObjets.getChildren().addAll(barresList);
    }

    public Point2D calcCoord(double x, double y) {
        Point2D pt;
        Point2D trp = topRightPt;
        double xf, yf;
        x -= zero.getX();
        y -= zero.getY();
        xf = (x / (trp.getX() - zero.getX())) * 160;
        yf = (y / (trp.getY() - zero.getY())) * 70;
        pt = new Point2D(xf, yf);
        return pt;
    }

    public Point2D calcCoordR(double x, double y) {
        Point2D pt;
        Point2D trp = topRightPt;
        double xf, yf;
        xf = ((x / 160) * (trp.getX() - zero.getX())) + zero.getX();
        yf = ((y / 70) * (trp.getY() - zero.getY())) + zero.getY();
        pt = new Point2D(xf, yf);
        return pt;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
