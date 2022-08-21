**Test 1:** Check if Game Over Screen displays on Easy - Sanjeev

**Test 2:** Check if Game Over Screen displays on Medium - Sanjeev

**Test 3:** Check if Game Over Screen displays on Hard - Tuan

**Test 4:** Check if Start Combat Button initiates game on Easy - Mah

**Test 5:** Check if Start Combat Button initiates game on Medium - Khoa

**Test 6:** Check if Start Combat Button initiates game on Hard - Khoa

**Test 7:** Check whether monument’s health is decreasing or not - Tuan

**Test 8:** Check if game over screen displays Exit Button - Max

**Test 9:** Check if restart button takes user to config screen - Max

**Test 10:** Check if game over screen displays Restart Button - Mah

We chose to implement testing for the easy, medium, and hard screen game over screens because they play a critical role in loading the ultimate game for the user to play. We also selected to test the functionality of the start combat button and monument health logic in order to ensure clarity for the user when watching the game. The Game Over Screen tests (1, 2, and 3) are performed by reducing the monument’s health to 0 and checking what scene is being displayed by JavaFX. If the Game Over Screen scene is being displayed, then the test is a success. The Start Combat Button tests (4, 5, and 6) are performed by triggering the button and seeing if enemies begin to spawn on the map. If enemies begin to spawn, then the button has worked successfully. The monument health test is evaluated by having an enemy attack the tower, and checking if the monument health has changed from its initial value. Tests 8, 9, and 10 relate to the Game Over Screen’s functionality, and check to see if their respective ID’d buttons are displayed. Test 9 specifically checks if the restart button on the Game Over Screen takes the user back to the Configuration scene - if JavaFX is displaying the Config scene, then the test has succeeded.
`

