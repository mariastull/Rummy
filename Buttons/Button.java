/*
CSCI 4448/5448 OOAD
Lara Chunko, Maria Stull, Jake Swartwout
Project 6-7
*/

package Buttons;

public abstract class Button {
    int screenPositionX;
    int screenPositionY;
    int width;
    int height;
    String buttonText;

    abstract void drawButton();
    abstract void onClick();
}