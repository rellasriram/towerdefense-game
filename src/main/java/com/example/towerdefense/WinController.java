package com.example.towerdefense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class WinController {

    @FXML
    private Button newGame;

    @FXML
    private Button exit;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;


    public void switchToWin(ActionEvent event, String statTime, String statMoney, String statHealth) throws IOException {
        root = FXMLLoader.load(getClass().getResource("winScreen.fxml"));

        label1 = (Label) root.lookup("#label1");
        label1.setText(statTime);

        label2 = (Label) root.lookup("#label2");
        label2.setText(statMoney);

        label3 = (Label) root.lookup("#label3");
        label3.setText(statHealth);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void newGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent event) throws IOException {
        System.exit(0);
    }
}
