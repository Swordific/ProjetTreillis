/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.alla.infom2.ProjetTreillis.Interface;

import fr.insa.alla.infom2.ProjetTreillis.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javafx.application.Application;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
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
    Group graphGrille = new Group();
    Group graphObjets = new Group();
    Rectangle2D tailleEcran = Screen.getPrimary().getVisualBounds();
    Treillis treillis = new Treillis();
    Boolean isSupprOn = false;
    EventHandler supprimerObjet;

    Point2D zero, topRightPt;

    Image appuiSimpleImg = new Image(getClass().getResourceAsStream("/appui_simple.png"), 24, 24, true, true);
    Image appuiDoubleImg = new Image(getClass().getResourceAsStream("/appui_double.png"), 24, 24, true, true);
    Image noeudSimpleImg = new Image(getClass().getResourceAsStream("/noeud_simple.png"), 24, 24, true, true);
    Image barreImg = new Image(getClass().getResourceAsStream("/barre.png"), 24, 24, true, true);
    Image supprImg = new Image(getClass().getResourceAsStream("/suppr.png"), 24, 24, true, true);

    HashMap<ImageView, Noeud> appuiSimpleImages = new HashMap<>();
    HashMap<ImageView, Noeud> appuiDoubleImages = new HashMap<>();
    HashMap<ImageView, Noeud> noeudSimpleImages = new HashMap<>();
    HashMap<Line, Barre> linesMap = new HashMap<>();
    HashMap<Barre, Line> barresMap = new HashMap<>();
    HashMap<String, Noeud> stringNoeudMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Treillis");
        primaryStage.setResizable(false);
        Scene mainScene;
        graph.getChildren().addAll(graphGrille, graphObjets);

        ArrayList<String> choixNoeudArray = new ArrayList<>();

        //Images
        Image appicon = new Image(getClass().getResourceAsStream("/appicon.png"));
        Image shrab = new Image(getClass().getResourceAsStream("/pont.png"));
        ImageView shrabView = new ImageView(shrab);
        shrabView.setOpacity(0.80);

        //Numeroteurs
        Numeroteur numAppuiSimple = new Numeroteur();
        Numeroteur numAppuiDouble = new Numeroteur();
        Numeroteur numNoeudSimple = new Numeroteur();
        Numeroteur numBarre = new Numeroteur();

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
        ToggleButton btnNoeudSimple = new ToggleButton("", new ImageView(noeudSimpleImg));
        btnNoeudSimple.setUserData("noeudSimple");
        ToggleButton btnAppuiSimple = new ToggleButton("", new ImageView(appuiSimpleImg));
        btnAppuiSimple.setUserData("appuiSimple");
        ToggleButton btnAppuiDouble = new ToggleButton("", new ImageView(appuiDoubleImg));
        btnAppuiDouble.setUserData("appuiDouble");
        ToggleButton btnBarre = new ToggleButton("", new ImageView(barreImg));
        btnBarre.setUserData("barre");
        ToggleButton btnSuppr = new ToggleButton("", new ImageView(supprImg));
        btnSuppr.setUserData("suppr");
        boutonsAjout.getToggles().addAll(btnNoeudSimple, btnAppuiSimple, btnAppuiDouble, btnBarre, btnSuppr);

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
        toolBar.getItems().addAll(btnNoeudSimple, btnAppuiSimple, btnAppuiDouble, btnBarre, btnSuppr);

        Dialog<double[]> creationNoeudDialog = new Dialog<>();
        Dialog<Object[]> creationBarreDialog = new Dialog<>();

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
        Vecteur2D f = new Vecteur2D(0, 20000);
        Vecteur2D f0 = new Vecteur2D(0, 0);
        Noeud n1 = new NoeudAppuiSimple(1, 0, 0, f0);
        Noeud n2 = new NoeudAppuiSimple(2, 20, 0, f0);
        Noeud n3 = new NoeudSimple(3, 10, 10, f);

        Barre b1 = new Barre(1, n1, n2, "acier");
        Barre b2 = new Barre(2, n2, n3, "acier");
        Barre b3 = new Barre(3, n3, n1, "acier");
        //b1.setTrac(-11100);

        treillis.ajouteNoeuds(n1, n2, n3);
        treillis.ajouteBarres(b1, b2, b3);
        treillis.calculeTraction();
        //System.out.println(b1.getTrac());

        dessinerContenu(treillis);
        System.out.println(b1.getTrac());
        System.out.println(b2.getTrac());
        System.out.println(b3.getTrac());
        //System.out.println(barresMap.get(b3).getFill());

        //System.out.println(Fichier.exportTreillis(treillis, "")[0]);
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
                stringNoeudMap.put(n.toString(), n);
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
                stringNoeudMap.put(n.toString(), n);
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
                stringNoeudMap.put(n.toString(), n);
                System.out.println(treillis.getNoeuds());

                dessinerContenu(treillis);
            }
        };

        EventHandler ajouterBarre = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //Barre b = new Barre();
            }
        };

        supprimerObjet = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Line objL = null;
                ImageView objIm = null;
                Barre b = null;
                Noeud n = null;
                System.out.println(e.getSource());
                if (e.getSource() instanceof ImageView) {
                    objIm = (ImageView) e.getSource();
                    if (appuiSimpleImages.containsKey(objIm)) {
                        n = appuiSimpleImages.get(objIm);
                        appuiSimpleImages.remove(objIm);
                    } else if (appuiDoubleImages.containsKey(objIm)) {
                        n = appuiDoubleImages.get(objIm);
                        appuiDoubleImages.remove(objIm);
                    } else if (noeudSimpleImages.containsKey(objIm)) {
                        n = noeudSimpleImages.get(objIm);
                        noeudSimpleImages.remove(objIm);
                    }
                    treillis.getNoeuds().remove(n);
                    choixNoeudArray.remove(n.toString());
                } else {
                    objL = (Line) e.getSource();
                    if (linesMap.containsKey(objL)) {
                        b = linesMap.get(objL);
                        linesMap.remove(objL);
                        treillis.getBarres().remove(b);
                    }
                }
                dessinerContenu(treillis);
            }
        };

        creationNoeudDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        creationNoeudDialog.setWidth(500);

        creationNoeudDialog.setTitle("Création de noeud");
        GridPane creationNoeudGrid = new GridPane();
        creationNoeudGrid.setHgap(10);
        creationNoeudGrid.setVgap(10);
        creationNoeudGrid.setPadding(new Insets(10, 10, 10, 10));

        String[] typesNoeuds = {"Noeud Simple", "Noeud Appui Simple", "Noeud Appui Double"};

        ChoiceBox<String> choixNoeudField = new ChoiceBox<String>();
        choixNoeudField.getItems().addAll(typesNoeuds);
        creationNoeudGrid.add(choixNoeudField, 1, 0);
        creationNoeudGrid.add(new Label("Choisissez le type de noeud"), 0, 0);
        TextField noeudPxField = new TextField();
        creationNoeudGrid.add(noeudPxField, 1, 1);
        creationNoeudGrid.add(new Label("Entrez l'abscisse du noeud"), 0, 1);
        TextField noeudPyField = new TextField();
        creationNoeudGrid.add(noeudPyField, 1, 2);
        creationNoeudGrid.add(new Label("Entrez l'ordonnée du noeud"), 0, 2);
        TextField noeudFxField = new TextField();
        creationNoeudGrid.add(noeudFxField, 1, 3);
        creationNoeudGrid.add(new Label("Entrez la force horizontale"), 0, 3);
        TextField noeudFyField = new TextField();
        creationNoeudGrid.add(noeudFyField, 1, 4);
        creationNoeudGrid.add(new Label("Entrez la force verticale"), 0, 4);

        creationNoeudDialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK && !noeudPxField.getText().equals("") && !noeudPyField.getText().equals("")) {
                int type = -1;
                switch (choixNoeudField.getValue()) {
                    case "Noeud Simple":
                        type = 0;
                        break;
                    case "Noeud Appui Simple":
                        type = 1;
                        break;
                    case "Noeud Appui Double":
                        type = 2;
                        break;
                }
                try {
                    double[] out = {type, Double.parseDouble(noeudPxField.getText()), Double.parseDouble(noeudPyField.getText()), Double.parseDouble(noeudFxField.getText()), Double.parseDouble(noeudFyField.getText())};
                    return out;
                } catch (Exception e) {
                    double[] out = {type, Double.parseDouble(noeudPxField.getText()), Double.parseDouble(noeudPyField.getText())};
                    return out;
                }
            }
            return null;
        }
        );

        creationNoeudDialog.getDialogPane().setContent(creationNoeudGrid);
        //creationNoeudDialog.setWidth(1000d);

        creationBarreDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        creationBarreDialog.getDialogPane().setMinWidth(500);
        //creationBarreDialog.setHeight(500d);
        creationBarreDialog.setResizable(true);
        String[] typesBarres = {"Barre"};
        creationBarreDialog.setTitle("Création de barre");
        GridPane creationBarreGrid = new GridPane();
        creationBarreGrid.setHgap(10);
        creationBarreGrid.setVgap(10);
        creationBarreGrid.setPadding(new Insets(10, 10, 10, 10));
        ChoiceBox<String> choixBarreField = new ChoiceBox<String>();
        choixBarreField.getItems().addAll(typesBarres);
        creationBarreGrid.add(new Label("Choisir le type de barre"), 0, 0);
        creationBarreGrid.add(choixBarreField, 1, 0);
        ChoiceBox<String> choixNoeudDepartField = new ChoiceBox<String>();
        for (Noeud n : treillis.getNoeuds()) {
            choixNoeudArray.add(n.toString());
        }
        choixNoeudDepartField.getItems().addAll(choixNoeudArray);
        creationBarreGrid.add(new Label("Choisir le noeud de départ"), 0, 1);
        creationBarreGrid.add(choixNoeudDepartField, 1, 1);
        ChoiceBox<String> choixNoeudArriveeField = new ChoiceBox<String>();
        choixNoeudArriveeField.getItems().addAll(choixNoeudArray);

        creationBarreGrid.add(new Label("Choisir le noeud d'arrivée"), 0, 2);
        creationBarreGrid.add(choixNoeudArriveeField, 1, 2);

        creationBarreDialog.getDialogPane().setContent(creationBarreGrid);

        creationBarreDialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                //choixBarreField.getValue();
                Noeud nd = stringNoeudMap.get((String) choixNoeudDepartField.getValue());
                Noeud na = stringNoeudMap.get((String) choixNoeudArriveeField.getValue());
                Object[] out = {nd, na};
                return out;
            }
            return null;
        }
        );
        EventHandler creerNoeudEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Optional<double[]> noeudInfo = creationNoeudDialog.showAndWait();
                noeudInfo.ifPresent(data -> {
                    double px = data[1];
                    double py = data[2];
                    Vecteur2D f = new Vecteur2D(0, 0);
                    if (data.length > 3) {
                        f = new Vecteur2D(data[3], data[4]);
                    }
                    Noeud n;
                    switch ((int) data[0]) {
                        case 0:
                            n = new NoeudSimple(px, py, f);
                            n.setId(numNoeudSimple.getOrAdd(n));
                            treillis.ajouteNoeud(n);
                            break;
                        case 1:
                            n = new NoeudAppuiSimple(px, py, f);
                            n.setId(numAppuiSimple.getOrAdd(n));
                            treillis.ajouteNoeud(n);
                            break;
                        case 2:
                            n = new NoeudAppuiDouble(px, py, f);
                            n.setId(numAppuiDouble.getOrAdd(n));
                            treillis.ajouteNoeud(n);
                            break;
                    }
                    System.out.println(treillis.getNoeuds());

                    dessinerContenu(treillis);
                });

            }
        };

        EventHandler creerBarreEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                for (Noeud n : treillis.getNoeuds()) {
                    if (!choixNoeudArray.contains(n.toString())) {
                        choixNoeudArray.add(n.toString());
                    }
                }
                choixNoeudDepartField.getItems().removeAll(choixNoeudDepartField.getItems());
                choixNoeudArriveeField.getItems().removeAll(choixNoeudArriveeField.getItems());
                choixNoeudDepartField.getItems().addAll(choixNoeudArray);
                choixNoeudArriveeField.getItems().addAll(choixNoeudArray);
                Optional<Object[]> barreInfo = creationBarreDialog.showAndWait();
                barreInfo.ifPresent(data -> {
                    Barre b = new Barre((Noeud) data[0], (Noeud) data[1]);
                    b.setId(numBarre.getOrAdd(b));
                    treillis.ajouteBarre(b);

                    System.out.println(treillis.getBarres());

                    dessinerContenu(treillis);
                });

            }
        };

        creerNoeudMenu.setOnAction(creerNoeudEvent);
        creerBarreMenu.setOnAction(creerBarreEvent);

        boutonsAjout.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null) {
                    isSupprOn = false;
                    graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterNoeudSimple);
                    graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiSimple);
                    graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiDouble);
                    graphObjets.removeEventFilter(MouseEvent.MOUSE_CLICKED, supprimerObjet);
                } else {
                    switch ((String) boutonsAjout.getSelectedToggle().getUserData()) {
                        case "noeudSimple":
                            isSupprOn = false;
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiDouble);
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiSimple);
                            graphObjets.removeEventFilter(MouseEvent.MOUSE_CLICKED, supprimerObjet);
                            graphGrille.addEventFilter(MouseEvent.MOUSE_CLICKED, ajouterNoeudSimple);
                            break;
                        case "appuiSimple":
                            isSupprOn = false;
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiDouble);
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterNoeudSimple);
                            graphObjets.removeEventFilter(MouseEvent.MOUSE_CLICKED, supprimerObjet);
                            graphGrille.addEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiSimple);
                            break;
                        case "appuiDouble":
                            isSupprOn = false;
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterNoeudSimple);
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiSimple);
                            graphObjets.removeEventFilter(MouseEvent.MOUSE_CLICKED, supprimerObjet);
                            graphGrille.addEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiDouble);
                            break;
                        case "suppr":
                            isSupprOn = true;
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterNoeudSimple);
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiSimple);
                            graphGrille.removeEventFilter(MouseEvent.MOUSE_CLICKED, ajouterAppuiDouble);
                            for (Node obj : graphObjets.getChildren()) {
                                obj.addEventFilter(MouseEvent.MOUSE_CLICKED, supprimerObjet);
                            }
                            break;
                    }
                }
            }
        }
        );
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
                hLines.get(i / 2 - 1).setStroke(Color.BLACK);
                hLines.get(i / 2 - 1).setOpacity(0.4);

                Text t = new Text(25, h / 10 * (i / 2) + 54, Integer.toString((8 - i / 2) * 10));
                t.setFill(Color.BLACK);
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
        graphObjets.getChildren().removeAll(graphObjets.getChildren());
        for (Noeud n : t.getNoeuds()) {
            dessinerNoeud(n);
        }
        for (Barre b : t.getBarres()) {
            dessinerBarre(b);
        }
        if (isSupprOn) {
            for (Node obj : graphObjets.getChildren()) {
                obj.addEventFilter(MouseEvent.MOUSE_CLICKED, supprimerObjet);
            }
        }
    }

    public void dessinerNoeud(Noeud n) {
        int type = n.nbrInconnues();
        Point2D xy = calcCoordR(n.getPx(), n.getPy());
        switch (type) {
            case 0:
                ImageView noeudSimpleView = new ImageView(noeudSimpleImg);
                noeudSimpleView.setFitWidth(16);
                noeudSimpleView.setFitHeight(16);
                noeudSimpleView.setX(xy.getX() - 8);
                noeudSimpleView.setY(xy.getY() - 8);
                noeudSimpleImages.put(noeudSimpleView, n);
                graphObjets.getChildren().add(noeudSimpleView);
                break;

            case 1:
                ImageView appuiSimpleView = new ImageView(appuiSimpleImg);
                appuiSimpleView.setFitWidth(32);
                appuiSimpleView.setFitHeight(32);
                appuiSimpleView.setX(xy.getX() - 16);
                appuiSimpleView.setY(xy.getY() - 5);
                appuiSimpleImages.put(appuiSimpleView, n);
                graphObjets.getChildren().add(appuiSimpleView);
                break;

            case 2:
                ImageView appuiDoubleView = new ImageView(appuiDoubleImg);
                appuiDoubleView.setFitWidth(32);
                appuiDoubleView.setFitHeight(32);
                appuiDoubleView.setX(xy.getX() - 16);
                appuiDoubleView.setY(xy.getY() - 4);
                appuiDoubleImages.put(appuiDoubleView, n);
                graphObjets.getChildren().add(appuiDoubleView);
                break;
        }
        System.out.println(graphObjets.getChildren());
    }

    public void dessinerBarre(Barre b) {
        Noeud nd = b.getNoeudDepart();
        Noeud na = b.getNoeudArrivee();
        Point2D pd = calcCoordR(nd.getPx(), nd.getPy());
        Point2D pa = calcCoordR(na.getPx(), na.getPy());
        Line l = new Line(pd.getX(), pd.getY(), pa.getX(), pa.getY());
        l.setStrokeWidth(5);
        l.setStrokeLineCap(StrokeLineCap.ROUND);
        l.setFill(barreCouleur(b));

        linesMap.put(l, b);
        //barresMap.put(b, l);
        if (treillis.calculeTraction() != null) {
            l.setStroke(barreCouleur(b));
            if (l.getStroke() == Color.RED) {
                l.getStrokeDashArray().addAll(25d, 10d);
            } else if (l.getStroke() == Color.BLUE) {
                l.getStrokeDashArray().addAll(25d, 10d);
            }
        }
        else {
            l.setStroke(Color.BLACK);
        }

        graphObjets.getChildren().add(l);

    }

    public Paint barreCouleur(Barre b) {
        double colorComp;
        double colorTrac;
        Line l = barresMap.get(b);
        colorComp = (abs(b.getTrac()) / abs(b.getMaxComp())) * 120d + 120d;
        colorTrac = 120d - (b.getTrac() / b.getMaxTrac()) * 120d;
        //System.out.println(colorComp + " / " + colorTrac);

        if (b.getTrac() > b.getMaxTrac()) {
            //l.getStrokeDashArray().addAll(25d, 10d);
            return Color.RED;
        } else if (b.getTrac() < b.getMaxComp()) {
            //l.getStrokeDashArray().addAll(25d, 10d);
            return Color.BLUE;
        } else if (b.getTrac() >= 0) {
            //l.setFill(Color.hsb(colorTrac, 1.0, 1.0));
            return Color.hsb(colorTrac, 1.0, 0.75);
        } else if (b.getTrac() <= 0) {
            //l.setFill(Color.hsb(colorComp, 1.0, 1.0));
            return Color.hsb(colorComp, 1.0, 0.75);
        } else {
            //l.setFill(Color.BLACK);
            l.getStrokeDashArray().addAll(25d, 10d);
            return Color.BLACK;
        }
        //barresMap.replace(b, l);

    }

    public Point2D calcCoord(double x, double y) {
        Point2D pt;
        Point2D trp = topRightPt;
        double xf, yf;
        x -= zero.getX();

        xf = ((x) / (trp.getX() - zero.getX())) * 160;
        yf = (y / (trp.getY() - zero.getY() / 70)) - zero.getY();

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
