package com.example.towerdefense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String name;

    @FXML
    private Label nameLabel;
    @FXML
    private Label difficultyLabel;
    @FXML
    private TextField nameField;
    @FXML
    private ToggleButton easyToggle;
    @FXML
    private ToggleButton mediumToggle;
    @FXML
    private ToggleButton hardToggle;

    @FXML
    public void switchToConfigScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("configurationscreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void submit(ActionEvent event) throws IOException {
        try {
            name = nameField.getText();
            if (name.trim().isEmpty()
                    && !(easyToggle.isSelected()
                    || mediumToggle.isSelected() || hardToggle.isSelected())) {
                nameLabel.setText("Name field can't be empty or whitespace-only.");
                difficultyLabel.setText("Please Select Difficulty and Click Begin.");
            } else if (!name.trim().isEmpty()
                    && !(easyToggle.isSelected()
                    || mediumToggle.isSelected() || hardToggle.isSelected())) {
                nameLabel.setText("Enter Name: ✓");
                difficultyLabel.setText("Please Select Difficulty and Click Begin.");
            } else if (name.trim().isEmpty()
                    && (easyToggle.isSelected()
                    || mediumToggle.isSelected() || hardToggle.isSelected())) {
                nameLabel.setText("Name field can't be empty or whitespace-only.");
                difficultyLabel.setText("Select Difficulty: ✓");
            } else {
                if (easyToggle.isSelected()) {
                    switchToEasy(event);
                } else if (mediumToggle.isSelected()) {
                    switchToMedium(event);
                } else {
                    switchToHard(event);
                }
            }
        } catch (NullPointerException e) {
            nameLabel.setText("Name Field cannot be null. Please try again.");
        }
    }

    public void switchToEasy(ActionEvent event) throws IOException {
        EasyController easyController = new EasyController();
        easyController.switchToEasy(event, name);
    }

    public void switchToMedium(ActionEvent event) throws IOException {
        MediumController mediumController = new MediumController();
        mediumController.switchToMedium(event, name);
    }

    public void switchToHard(ActionEvent event) throws IOException {
        HardController hardController = new HardController();
        hardController.switchToHard(event, name);
    }

    public String getName() {
        return name;
    }
}