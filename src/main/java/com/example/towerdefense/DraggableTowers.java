package com.example.towerdefense;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

interface DraggableTowers {

    /**
     * Draggable class default method. This allows for an ImageView to be passed in 
     * and make it draggable around the screen.
     * 
     * @param img ImageView of the image that will become draggable
     */
    default void enableDrag(final ImageView img) {
        Coords cd = new Coords();
        
        //Moused is clicked down
        img.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                cd.x = img.getX() - mouseEvent.getX();
                cd.y = img.getY() - mouseEvent.getY();
                img.getScene().setCursor(Cursor.DEFAULT);
            }
        });

        //Mouse is released
        img.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                img.getScene().setCursor(Cursor.HAND);
            }
        });

        //Mouse is being dragged
        //Image is constantly being set to the old position plus the new coordinates
        img.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                img.setX(mouseEvent.getX() + cd.x);
                img.setY(mouseEvent.getY() + cd.y);
            }
        });

        //Mouse hovers over the object
        img.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    img.getScene().setCursor(Cursor.HAND);
                }
            }
        });

        //Mouse is not hovering over the object 
        img.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    img.getScene().setCursor(Cursor.DEFAULT);
                }
            }
        });
    }
    static class Coords {
        private double x;
        private double y;
    }
}
