import javafx.animation.PathTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import com.example.towerdefense.HelloApplication;

import static org.testfx.api.FxAssert.verifyThat;

import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeUnit;

public class Testing extends ApplicationTest {
    private Stage myStage;
    private PathTransition transition;


    @Override
    public void start(Stage primaryStage) throws Exception {
        HelloApplication screen = new HelloApplication();
        screen.start(primaryStage);
        myStage = primaryStage;
    }
    @Test
    public void testPlay() {
        clickOn("#startButton");
        verifyThat("#nameField", NodeMatchers.isNotNull());
    }

    @Test
    public void testStartgame() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        verifyThat("#gameNameDisplay", NodeMatchers.isNotNull());
    }

    //Test 1: Check if Game Over Screen displays on Easy
    @Test
    public void gameOverScreenDisplayEasy() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        verifyThat("#gameNameDisplay", NodeMatchers.isNotNull());
        WaitForAsyncUtils.sleep(20, TimeUnit.SECONDS);
        verifyThat("#retry", NodeMatchers.isNotNull());
    }

    //Test 2: Check if Game Over Screen displays on Medium
    @Test
    public void gameOverScreenDisplayMedium() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#mediumToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        verifyThat("#gameNameDisplay", NodeMatchers.isNotNull());
        WaitForAsyncUtils.sleep(20, TimeUnit.SECONDS);
        verifyThat("#retry", NodeMatchers.isNotNull());
    }

    //Test 3: Check if Game Over Screen displays on Hard
    @Test
    public void gameOverScreenDisplayHard() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        verifyThat("#gameNameDisplay", NodeMatchers.isNotNull());
        WaitForAsyncUtils.sleep(20, TimeUnit.SECONDS);
        verifyThat("#retry", NodeMatchers.isNotNull());
    }

    //Test 4: Check if Start Combat Button initiates game on Easy

    @Test
    public void startCombatEasy() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        verifyThat("#enemyBox", NodeMatchers.isNotNull());
    }

    //Test 5: Check if Start Combat Button initiates game on Medium
    @Test
    public void startCombatMedium() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#mediumToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        verifyThat("#enemyBox", NodeMatchers.isNotNull());
    }

    //Test 6: Check if Start Combat Button initiates game on Hard
    @Test
    public void startCombatHard() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        verifyThat("#enemyBox", NodeMatchers.isNotNull());
    }

    //Test 7: Check whether monument's health is decreasing or not

    @Test
    public void decreaseHealthEasyScreen() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        Scene currentScene = myStage.getScene();
        sleep(18000);
        Label health = (Label) currentScene.lookup("#healthLabel");
        int value = Integer.valueOf(health.getText());
        Assertions.assertEquals(0, value);
    }

    @Test
    public void decreaseHealthMediumScreen() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#mediumToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        Scene currentScene = myStage.getScene();
        sleep(15000);
        Label health = (Label) currentScene.lookup("#healthLabel");
        int value = Integer.valueOf(health.getText());
        Assertions.assertEquals(0, value);
    }

    @Test
    public void decreaseHealthHardScreen() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        Scene currentScene = myStage.getScene();
        sleep(12000);
        Label health = (Label) currentScene.lookup("#healthLabel");
        int value = Integer.valueOf(health.getText());
        Assertions.assertEquals(0, value);
    }
    
    ////Test 8: Check if game over screen displays Exit Button
    @Test
    public void gameOverScreenDisplaysExitButton() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        verifyThat("#gameNameDisplay", NodeMatchers.isNotNull());
        WaitForAsyncUtils.sleep(20, TimeUnit.SECONDS);
        verifyThat("#exit", NodeMatchers.isNotNull());
    }

    //Test 9: Check if restart button takes user to config screen
    @Test
    public void restartTakesToConfigScreen() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        verifyThat("#gameNameDisplay", NodeMatchers.isNotNull());
        WaitForAsyncUtils.sleep(20, TimeUnit.SECONDS);
        clickOn("#retry");
        clickOn("#startButton");
        verifyThat("#nameField", NodeMatchers.isNotNull());
    }

    ////Test 10: Check if game over screen displays Restart Button
    @Test
    public void gameOverScreenDisplaysRestartButton() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        verifyThat("#gameNameDisplay", NodeMatchers.isNotNull());
        WaitForAsyncUtils.sleep(20, TimeUnit.SECONDS);
        verifyThat("#retry", NodeMatchers.isNotNull());
    }

    ///// M5 Testing
    ////Test 11: Test Tower Drag after starting the game on Easy
    @Test
    public void testDragEasy() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirmButton");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        clickOn("#option2");
        clickOn("#confirmButton");
        moveTo("#tower2Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation2");
        release(MouseButton.PRIMARY);
    }

    ////Test 12: Test Tower Drag after starting the game on Medium
    @Test
    public void testDragMedium() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#mediumToggle");
        clickOn("#nextButton");
        clickOn("#option2");
        clickOn("#confirmButton");
        moveTo("#tower2Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        clickOn("#option3");
        clickOn("#confirmButton");
        moveTo("#tower3Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation2");
        release(MouseButton.PRIMARY);
    }

    ////Test 13: Test Tower Drag after starting the game on Hard
    @Test
    public void testDragHard() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#option3");
        clickOn("#confirm");
        moveTo("#tower3Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        clickOn("#option2");
        clickOn("#confirm");
        moveTo("#tower2Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation2");
        release(MouseButton.PRIMARY);
    }

    ////Test 14: Check if money increases for each tower to enemy attack on Easy
    @Test
    public void testMoneyIncreaseEasy() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirmButton");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        Scene currentScene = myStage.getScene();
        sleep(4000);
        Label health = (Label) currentScene.lookup("#moneyLabel");
        int value = Integer.valueOf(health.getText());
        Assertions.assertTrue(value > 7000);
    }

    ////Test 15: Check if money increases for each tower to enemy attack on Medium
    @Test
    public void testMoneyIncreaseMedium() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#mediumToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirmButton");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation2");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        Scene currentScene = myStage.getScene();
        sleep(4000);
        Label health = (Label) currentScene.lookup("#moneyLabel");
        int value = Integer.valueOf(health.getText());
        Assertions.assertTrue(value > 500);
    }

    ////Test 16: Check if money increases for each tower to enemy attack on Hard
    @Test
    public void testMoneyIncreaseHard() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirm");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        Scene currentScene = myStage.getScene();
        sleep(4000);
        Label health = (Label) currentScene.lookup("#moneyLabel");
        int value = Integer.valueOf(health.getText());
        Assertions.assertTrue(value > 0);
    }

    ////Test 17: Check if there are multiple enemies on screen on Easy
    @Test
    public void testMultipleEnemiesEasy() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        sleep(3000);
        verifyThat("#enemyBox", NodeMatchers.isNotNull());
        verifyThat("#enemyBox2", NodeMatchers.isNotNull());
        verifyThat("#enemyBox3", NodeMatchers.isNotNull());
    }

    ////Test 19: Check if there are multiple enemies on screen on Medium
    @Test
    public void testMultipleEnemiesMedium() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#mediumToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        sleep(5000);
        verifyThat("#enemyBox", NodeMatchers.isNotNull());
        verifyThat("#enemyBox2", NodeMatchers.isNotNull());
        verifyThat("#enemyBox3", NodeMatchers.isNotNull());
    }

    ////Test 19: Check if there are multiple enemies on screen on Hard
    @Test
    public void testMultipleEnemiesHard() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        sleep(7000);
        verifyThat("#enemyBox", NodeMatchers.isNotNull());
        verifyThat("#enemyBox2", NodeMatchers.isNotNull());
        verifyThat("#enemyBox3", NodeMatchers.isNotNull());
    }

    ////Test 20: Check if enemies die
    @Test
    public void testEnemyAliveHard() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#option3");
        clickOn("#confirm");
        moveTo("#tower3Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        clickOn("#option2");
        clickOn("#confirm");
        moveTo("#tower2Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation2");
        release(MouseButton.PRIMARY);

        sleep(9000);
        verifyThat("#enemyBox", NodeMatchers.isInvisible());
        verifyThat("#enemyBox2", NodeMatchers.isInvisible());
        verifyThat("#enemyBox3", NodeMatchers.isInvisible());
    }

    //// M6 Testing
    // Test 1: Test whether win screen appears on easy screen
    @Test
    public void winScreenEasy() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirmButton");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#option2");
        clickOn("#confirmButton");
        moveTo("#tower2Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation2");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        sleep(23000);
        verifyThat("#newGame", NodeMatchers.isVisible());
    }

    // Test 2: Test whether win screen appears on medium screen
    @Test
    public void winScreenMedium() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#mediumToggle");
        clickOn("#nextButton");
        clickOn("#option2");
        clickOn("#confirmButton");
        moveTo("#tower2Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#option3");
        clickOn("#confirmButton");
        moveTo("#tower3Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation2");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        sleep(40000);
        verifyThat("#newGame", NodeMatchers.isVisible());
    }

    // Test 3: Test whether win screen appears on hard screen
    @Test
    public void winScreenHard() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirm");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        sleep(9000);
        clickOn("#option3");
        clickOn("#confirm");
        moveTo("#tower3Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation2");
        release(MouseButton.PRIMARY);
        sleep(4000);
        clickOn("#option2");
        clickOn("#confirm");
        moveTo("#tower2Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation3");
        release(MouseButton.PRIMARY);
        sleep(24000);
        verifyThat("#newGame", NodeMatchers.isVisible());
    }

    // Test 4: Test whether towers can be updated in easy screen
    @Test
    public void updateEasy() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirmButton");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        clickOn("#tower1Box");
        sleep(2000);
    }

    // Test 5: Test whether towers can be updated in medium screen
    @Test
    public void updateMedium() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#mediumToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirmButton");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        clickOn("#tower1Box");
        sleep(2000);
    }

    // Test 6: Test whether towers can be updated in hard screen
    @Test
    public void updateHard() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirm");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        clickOn("#tower1Box");
        sleep(1000);
    }

    // Test 7: Check final boss appears easy screen
    @Test
    public void bossEasy() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        sleep(13000);
        verifyThat("#finalBox", NodeMatchers.isVisible());
    }

    // Test 8: Check final boss appears medium screen
    @Test
    public void bossMedium() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#mediumToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        sleep(13000);
        verifyThat("#finalBox", NodeMatchers.isVisible());
    }

    // Test 9: Check final boss appears on hard screen
    @Test
    public void bossHard() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#hardToggle");
        clickOn("#nextButton");
        clickOn("#startGame");
        sleep(14000);
        verifyThat("#finalBox", NodeMatchers.isVisible());
    }

    // Test 10: Check whether monument health is 1000 when enemies are defeated
    @Test
    public void monumentHealth() {
        clickOn("#startButton");
        clickOn("#nameField").write("I am awesome");
        clickOn("#easyToggle");
        clickOn("#nextButton");
        clickOn("#option1");
        clickOn("#confirmButton");
        moveTo("#tower1Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation1");
        release(MouseButton.PRIMARY);
        clickOn("#option2");
        clickOn("#confirmButton");
        moveTo("#tower2Box");
        press(MouseButton.PRIMARY);
        moveTo("#dragLocation2");
        release(MouseButton.PRIMARY);
        clickOn("#startGame");
        sleep(23000);
        Scene currentScene = myStage.getScene();
        Label health = (Label) currentScene.lookup("#label3");
        int value = Integer.valueOf(health.getText());
        Assertions.assertEquals(1000, value);
    }
}