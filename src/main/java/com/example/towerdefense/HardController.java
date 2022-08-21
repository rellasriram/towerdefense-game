package com.example.towerdefense;

import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class HardController implements DraggableTowers {

    @FXML
    private Text gameNameDisplay; // Player name
    @FXML
    private Label moneyLabel; // Money balance
    @FXML
    private Label healthLabel; // Health left
    @FXML
    private ImageView monument;

    //Initialize Tower Images after purchase
    private Image skyscraper = new Image(getClass().getResourceAsStream("skyscraper.png"));
    private Image castle = new Image(getClass().getResourceAsStream("castle.png"));
    private Image roman = new Image(getClass().getResourceAsStream("roman.png"));
    private Image upgradedSkyscraper = new Image(getClass().getResourceAsStream("upgrade2.png"));
    private Image upgradedCastle = new Image(getClass().getResourceAsStream("upgrade.png"));
    private Image upgradedRoman = new Image(getClass().getResourceAsStream("upgrade3.png"));

    //Initialize Enemies
    private Image enemy = new Image(getClass().getResourceAsStream("enemy.png"));
    private Image enemy2 = new Image(getClass().getResourceAsStream("enemy2.png"));
    private Image enemy3 = new Image(getClass().getResourceAsStream("enemy3.png"));
    private Image finalBoss = new Image(getClass().getResourceAsStream("finalboss.png"));

    //Tower Menu
    @FXML
    private ToggleButton option1;
    @FXML
    private ToggleButton option2;
    @FXML
    private ToggleButton option3;

    //Selected tower will be placed in its box
    @FXML
    private ImageView tower1Box;
    @FXML
    private ImageView tower2Box;
    @FXML
    private ImageView tower3Box;

    //Enemy will be visible once the player starts the game
    @FXML
    private ImageView enemyBox;
    @FXML
    private ImageView enemyBox2;
    @FXML
    private ImageView enemyBox3;
    @FXML
    private ImageView finalBox;

    // Tower price
    @FXML
    private Label price1;
    @FXML
    private Label price2;
    @FXML
    private Label price3;

    //Collision Path
    @FXML
    private ImageView path1;
    @FXML
    private ImageView path2;
    @FXML
    private ImageView path3;
    @FXML
    private ImageView path4;
    @FXML
    private ImageView path5;
    @FXML
    private ImageView path6;

    @FXML
    private Label errorMessage;
    @FXML
    private Label insufficient;
    @FXML
    private Button startGame;
    @FXML
    private Button quitButton;
    @FXML
    private Button gameOverTrigger;
    @FXML
    private Button winTrigger;

    //For testing purposes
    @FXML
    private ImageView dragLocation1;
    @FXML
    private ImageView dragLocation2;
    @FXML
    private ImageView dragLocation3;
    @FXML
    private ImageView dragLocation4;

    //Timer Label
    @FXML
    private Label countLabel;
    Timer timer = new Timer();
    int seconds = 0;

    private Path path01;
    private Path path02;
    private Path path03;
    private Path path04;

    private PathTransition transition;
    private PathTransition transition2;
    private PathTransition transition3;
    private PathTransition transition4;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private double red1 = 1;
    private double green1 = 1;
    private double blue1 = 1;

    private double red2 = 1;
    private double green2 = 1;
    private double blue2 = 1;

    private double red3 = 1;
    private double green3 = 1;
    private double blue3 = 1;

    private double red4 = 1;
    private double green4 = 1;
    private double blue4 = 1;

    public void switchToHard(ActionEvent event, String name) throws IOException {
        root = FXMLLoader.load(getClass().getResource("initialHardScreen.fxml"));
        gameNameDisplay = (Text) root.lookup("#gameNameDisplay");
        gameNameDisplay.setText(name);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public boolean checkBalance(Label price) {

        int balance = Integer.parseInt(moneyLabel.getText());
        int cost = Integer.parseInt(price.getText());

        if (balance < cost) {
            return false;
        }

        balance = balance - cost;
        moneyLabel.setText(Integer.toString(balance));
        return true;
    }

    public void displayTower()  {
        if (option1.isSelected() && checkBalance(price1)) {
            tower1Box.setImage(skyscraper);
            enableDrag(tower1Box);
            collisionTimer.start();
        } else if (option2.isSelected() && checkBalance(price2)) {
            tower2Box.setImage(castle);
            enableDrag(tower2Box);
            collisionTimer.start();
        } else if (option3.isSelected() && checkBalance(price3)) {
            tower3Box.setImage(roman);
            enableDrag(tower3Box);
            collisionTimer.start();
        } else {
            insufficient.setText("Insufficient!");
        }
    }

    private AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long time) {
            if (option1.isSelected()) {
                checkCollisionPath(tower1Box, path1, path2, path3, path4, path5, path6);
            } else if (option2.isSelected()) {
                checkCollisionPath(tower2Box, path1, path2, path3, path4, path5, path6);
            } else if (option3.isSelected()) {
                checkCollisionPath(tower3Box, path1, path2, path3, path4, path5, path6);
            }
        }
    };

    private AnimationTimer collisionTimer2 = new AnimationTimer() {
        @Override
        public void handle(long time) {
            if (tower1Box.getImage() == upgradedSkyscraper) {
                checkEnemy1InRange(0.009);
            } else if (tower2Box.getImage() == upgradedCastle) {
                checkEnemy1InRange(0.009);
            } else if (tower3Box.getImage() == upgradedRoman) {
                checkEnemy1InRange(0.009);
            } else {
                checkEnemy1InRange(0.0009);
            }
        }
    };

    private AnimationTimer collisionTimer3 = new AnimationTimer() {
        @Override
        public void handle(long time) {
            if (tower1Box.getImage() == upgradedSkyscraper) {
                checkEnemy2InRange(0.008);
            } else if (tower2Box.getImage() == upgradedCastle) {
                checkEnemy2InRange(0.008);
            } else if (tower3Box.getImage() == upgradedRoman) {
                checkEnemy2InRange(0.008);
            } else {
                checkEnemy2InRange(0.0008);
            }
        }
    };

    private AnimationTimer collisionTimer4 = new AnimationTimer() {
        @Override
        public void handle(long time) {
            if (tower1Box.getImage() == upgradedSkyscraper) {
                checkEnemy3InRange(0.007);
            } else if (tower2Box.getImage() == upgradedCastle) {
                checkEnemy3InRange(0.007);
            } else if (tower3Box.getImage() == upgradedRoman) {
                checkEnemy3InRange(0.007);
            } else {
                checkEnemy3InRange(0.0007);
            }
        }
    };

    private AnimationTimer collisionTimer5 = new AnimationTimer() {
        @Override
        public void handle(long time) {
            if (tower1Box.getImage() == upgradedSkyscraper) {
                checkBossInRange(0.007);
            } else if (tower2Box.getImage() == upgradedCastle) {
                checkBossInRange(0.007);
            } else if (tower3Box.getImage() == upgradedRoman) {
                checkBossInRange(0.007);
            } else {
                checkBossInRange(0.0007);
            }
        }
    };

    public void checkCollisionPath(ImageView tower, ImageView path1, ImageView path2,
                               ImageView path3, ImageView path4, ImageView path5, ImageView path6) {
        if (tower.getBoundsInParent().intersects(path1.getBoundsInParent())
                || tower.getBoundsInParent().intersects(path2.getBoundsInParent())
                || tower.getBoundsInParent().intersects(path3.getBoundsInParent())
                || tower.getBoundsInParent().intersects(path4.getBoundsInParent())
                || tower.getBoundsInParent().intersects(path5.getBoundsInParent())
                || tower.getBoundsInParent().intersects(path6.getBoundsInParent())) {
            startGame.isDisabled();
            errorMessage.setText("Tower can't be on the path!");
            startGame.setDisable(true);
        } else {
            errorMessage.setText("");
            startGame.setDisable(false);
        }
    }

    public void upgradeSkyscraper() {
        int limit = Integer.parseInt(countLabel.getText());

        if (limit < 5) {
            if (tower1Box.getOnMouseDragged() == null) {
                tower1Box.setImage(upgradedSkyscraper);
            }
        }
    }

    public void upgradeCastle() {
        int limit = Integer.parseInt(countLabel.getText());

        if (limit < 5) {
            if (tower2Box.getOnMouseDragged() == null) {
                tower2Box.setImage(upgradedCastle);
            }
        }
    }

    public void upgradeRoman() {
        int limit = Integer.parseInt(countLabel.getText());

        if (limit < 5) {
            if (tower3Box.getOnMouseDragged() == null) {
                tower3Box.setImage(upgradedRoman);
            }
        }
    }

    public void checkEnemy1InRange(double attack) {
        if (enemyBox.getBoundsInParent().intersects(
                tower1Box.getBoundsInParent().getMinX() - 35,
                tower1Box.getBoundsInParent().getMinY() - 35,
                tower1Box.getBoundsInParent().getWidth() + 80,
                tower1Box.getBoundsInParent().getHeight() + 80) || enemyBox.getBoundsInParent().intersects(
                tower2Box.getBoundsInParent().getMinX() - 35,
                tower2Box.getBoundsInParent().getMinY() - 35,
                tower2Box.getBoundsInParent().getWidth() + 50,
                tower2Box.getBoundsInParent().getHeight() + 50) || enemyBox.getBoundsInParent().intersects(
                tower3Box.getBoundsInParent().getMinX() - 35,
                tower3Box.getBoundsInParent().getMinY() - 35,
                tower3Box.getBoundsInParent().getWidth() + 60,
                tower3Box.getBoundsInParent().getHeight() + 60)) {
            decreaseEnemy1Health(attack);
        } else if (enemyBox.getBoundsInParent().intersects(monument.getBoundsInParent())) {
            transition.stop();
            decreaseHealth(5);
        }
    }

    public void checkEnemy2InRange(double attack) {
        if (enemyBox2.getBoundsInParent().intersects(
                tower1Box.getBoundsInParent().getMinX() - 35,
                tower1Box.getBoundsInParent().getMinY() - 35,
                tower1Box.getBoundsInParent().getWidth() + 80,
                tower1Box.getBoundsInParent().getHeight() + 80) || enemyBox2.getBoundsInParent().intersects(
                tower2Box.getBoundsInParent().getMinX() - 35,
                tower2Box.getBoundsInParent().getMinY() - 35,
                tower2Box.getBoundsInParent().getWidth() + 50,
                tower2Box.getBoundsInParent().getHeight() + 50) || enemyBox2.getBoundsInParent().intersects(
                tower3Box.getBoundsInParent().getMinX() - 35,
                tower3Box.getBoundsInParent().getMinY() - 35,
                tower3Box.getBoundsInParent().getWidth() + 60,
                tower3Box.getBoundsInParent().getHeight() + 60)) {
            decreaseEnemy2Health(attack);
        } else if (enemyBox2.getBoundsInParent().intersects(monument.getBoundsInParent())) {
            transition2.stop();
            decreaseHealth(5);
        }
    }

    public void checkEnemy3InRange(double attack) {
        if (enemyBox3.getBoundsInParent().intersects(
                tower1Box.getBoundsInParent().getMinX() - 35,
                tower1Box.getBoundsInParent().getMinY() - 35,
                tower1Box.getBoundsInParent().getWidth() + 80,
                tower1Box.getBoundsInParent().getHeight() + 80) || enemyBox3.getBoundsInParent().intersects(
                tower2Box.getBoundsInParent().getMinX() - 35,
                tower2Box.getBoundsInParent().getMinY() - 35,
                tower2Box.getBoundsInParent().getWidth() + 50,
                tower2Box.getBoundsInParent().getHeight() + 50) || enemyBox3.getBoundsInParent().intersects(
                tower3Box.getBoundsInParent().getMinX() - 35,
                tower3Box.getBoundsInParent().getMinY() - 35,
                tower3Box.getBoundsInParent().getWidth() + 60,
                tower3Box.getBoundsInParent().getHeight() + 60)) {
            decreaseEnemy3Health(attack);
        } else if (enemyBox3.getBoundsInParent().intersects(monument.getBoundsInParent())) {
            transition3.stop();
            decreaseHealth(5);
        }
    }

    public void checkBossInRange(double attack) {
        if (finalBox.getBoundsInParent().intersects(
                tower1Box.getBoundsInParent().getMinX() - 35,
                tower1Box.getBoundsInParent().getMinY() - 35,
                tower1Box.getBoundsInParent().getWidth() + 80,
                tower1Box.getBoundsInParent().getHeight() + 80) || finalBox.getBoundsInParent().intersects(
                tower2Box.getBoundsInParent().getMinX() - 35,
                tower2Box.getBoundsInParent().getMinY() - 35,
                tower2Box.getBoundsInParent().getWidth() + 50,
                tower2Box.getBoundsInParent().getHeight() + 50) || finalBox.getBoundsInParent().intersects(
                tower3Box.getBoundsInParent().getMinX() - 35,
                tower3Box.getBoundsInParent().getMinY() - 35,
                tower3Box.getBoundsInParent().getWidth() + 60,
                tower3Box.getBoundsInParent().getHeight() + 60)) {
            decreaseFinalBossHealth(attack);
        } else if (finalBox.getBoundsInParent().intersects(monument.getBoundsInParent())) {
            transition4.stop();
            decreaseHealth(5);
        }
    }

    public void decreaseHealth(int damage) {
        int monumentHealth = Integer.parseInt(healthLabel.getText());
        if (monumentHealth > 0) {
            monumentHealth -= 5;
        }

        if (monumentHealth <= 0) {
            healthLabel.setText("0");
            quitButton.setDisable(false);
            transition.stop();
            transition2.stop();
            transition3.stop();
            transition4.stop();
            collisionTimer2.stop();
            collisionTimer3.stop();
            collisionTimer4.stop();
            collisionTimer5.stop();
            gameOverTrigger.fire();
        } else {
            healthLabel.setText(Integer.toString(
                    (Integer.parseInt(healthLabel.getText()) - damage)));
        }
    }

    public void switchToConfigScreen(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("configurationscreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void move(ImageView enemy, ImageView enemy2, ImageView enemy3) {
        //Enemy Path 1
        path01 = new Path();
        transition = new PathTransition();

        path01.getElements().add(new MoveTo(0, 25));
        path01.getElements().add(new LineTo(200, 25));
        path01.getElements().add(new LineTo(200, 170));
        path01.getElements().add(new LineTo(370, 170));
        path01.getElements().add(new LineTo(370, 310));
        path01.getElements().add(new LineTo(140, 310));
        path01.getElements().add(new LineTo(140, 430));

        transition.setNode(enemyBox);
        transition.setDuration(Duration.seconds(30));
        transition.setPath(path01);
        transition.setCycleCount(PathTransition.INDEFINITE);
        collisionTimer2.start();

        //Enemy Path 2
        path02 = new Path();
        transition2 = new PathTransition();

        path02.getElements().add(new MoveTo(0, 25));
        path02.getElements().add(new LineTo(250, 25));
        path02.getElements().add(new LineTo(250, 170));
        path02.getElements().add(new LineTo(420, 170));
        path02.getElements().add(new LineTo(420, 310));
        path02.getElements().add(new LineTo(190, 310));
        path02.getElements().add(new LineTo(190, 430));

        transition2.setNode(enemyBox2);
        transition2.setDuration(Duration.seconds(35));
        transition2.setPath(path02);
        transition2.setCycleCount(PathTransition.INDEFINITE);
        collisionTimer3.start();

        //Enemy Path 3
        path03 = new Path();
        transition3 = new PathTransition();

        path03.getElements().add(new MoveTo(0, 25));
        path03.getElements().add(new LineTo(300, 25));
        path03.getElements().add(new LineTo(300, 170));
        path03.getElements().add(new LineTo(470, 170));
        path03.getElements().add(new LineTo(470, 310));
        path03.getElements().add(new LineTo(240, 310));
        path03.getElements().add(new LineTo(240, 430));

        transition3.setNode(enemyBox3);
        transition3.setDuration(Duration.seconds(40));
        transition3.setPath(path03);
        transition3.setCycleCount(PathTransition.INDEFINITE);
        collisionTimer4.start();

        //Start Transitions
        transition.play();
        transition2.play();
        transition3.play();
    }

    public void moveFinalBoss(ImageView boss) {
        path04 = new Path();
        transition4 = new PathTransition();

        path04.getElements().add(new MoveTo(0, 25));
        path04.getElements().add(new LineTo(550, 25));
        path04.getElements().add(new LineTo(550, 170));
        path04.getElements().add(new LineTo(725, 170));
        path04.getElements().add(new LineTo(725, 310));
        path04.getElements().add(new LineTo(490, 310));
        path04.getElements().add(new LineTo(490, 430));

        transition4.setNode(finalBox);
        transition4.setDuration(Duration.seconds(46));
        transition4.setPath(path04);
        transition4.setCycleCount(PathTransition.INDEFINITE);
        collisionTimer5.start();
        transition4.play();
    }

    public void battle() {

        gameOverTrigger.setOnAction(event -> {
            GameOverController gameOver = new GameOverController();
            try {
                String time = countLabel.getText();

                int current = Integer.valueOf(moneyLabel.getText());
                String money = Integer.toString(current);

                String health = healthLabel.getText();

                gameOver.switchToGameOver(event, time, money, health);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        });

        winTrigger.setOnAction(event2 -> {
            WinController winner = new WinController();
            try {
                String time = countLabel.getText();

                int current = Integer.valueOf(moneyLabel.getText());
                String money = Integer.toString(current);

                String health = healthLabel.getText();

                winner.switchToWin(event2, time, money, health);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        });

        enemyBox.setImage(enemy);
        enemyBox2.setImage(enemy2);
        enemyBox3.setImage(enemy3);
        finalBox.setImage(finalBoss);

        tower1Box.setOnMouseDragged(null);
        tower2Box.setOnMouseDragged(null);
        tower3Box.setOnMouseDragged(null);
        startGame.setDisable(true);
        quitButton.setDisable(true);

        timer.scheduleAtFixedRate(task, 0, 1000L);
        move(enemyBox, enemyBox3, enemyBox3);
        moveFinalBoss(finalBox);
    }

    public void decreaseEnemy1Health(double attack) {

        try {
            Lighting lighting = new Lighting(
                    new Light.Distant(45, 100, Color.color(red1, green1, blue1)));
            enemyBox.setEffect(lighting);
            if (blue1 > 0.0 && green1 > 0.0) {
                blue1 -= attack;
                green1 -= attack;
                int current = Integer.valueOf(moneyLabel.getText());
                moneyLabel.setText(Integer.toString(++current));
            }

        } catch (IllegalArgumentException e) {
            transition.stop();
            enemyBox.setVisible(false);
            enemyBox.setImage(null);
        }
    }

    public void decreaseEnemy2Health(double attack) {
        try {
            Lighting lighting = new Lighting(
                    new Light.Distant(45, 100, Color.color(red2, green2, blue2)));
            enemyBox2.setEffect(lighting);
            if (blue2 > 0.0 && green2 > 0.0) {
                blue2 -= attack;
                green2 -= attack;
                int current = Integer.valueOf(moneyLabel.getText());
                moneyLabel.setText(Integer.toString(++current));
            }

        } catch (IllegalArgumentException e) {
            transition2.stop();
            enemyBox2.setVisible(false);
            enemyBox2.setImage(null);
        }
    }

    public void decreaseEnemy3Health(double attack) {
        try {
            Lighting lighting = new Lighting(
                    new Light.Distant(45, 100, Color.color(red3, green3, blue3)));
            enemyBox3.setEffect(lighting);
            if (blue3 > 0.0 && green3 > 0.0) {
                blue3 -= attack;
                green3 -= attack;
                int current = Integer.valueOf(moneyLabel.getText());
                moneyLabel.setText(Integer.toString(++current));
            }

        } catch (IllegalArgumentException e) {
            transition3.stop();
            enemyBox3.setVisible(false);
            enemyBox3.setImage(null);
        }
    }

    public void decreaseFinalBossHealth(double attack) {
        try {
            Lighting lighting = new Lighting(
                    new Light.Distant(45, 100, Color.color(red4, green4, blue4)));
            finalBox.setEffect(lighting);
            if (blue4 > 0.0 && green4 > 0.0) {
                blue4 -= attack;
                green4 -= attack;
                int current = Integer.valueOf(moneyLabel.getText());
                moneyLabel.setText(Integer.toString(++current));
            }

        } catch (IllegalArgumentException e) {
            transition4.stop();
            finalBox.setVisible(false);
            finalBox.setImage(null);

            if (!enemyBox.isVisible() && !enemyBox2.isVisible() && !enemyBox3.isVisible() && !finalBox.isVisible()) {
                task.cancel();
                transition.stop();
                transition2.stop();
                transition3.stop();
                transition4.stop();
                collisionTimer2.stop();
                collisionTimer3.stop();
                collisionTimer4.stop();
                collisionTimer5.stop();
                winTrigger.fire();
            }
        }
    }

    /**
     * Timer statistic for game over screen
     */
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            seconds++;

            Platform.runLater(() -> {
                countLabel.setText(Integer.toString(seconds));
            });
        }
    };
}
