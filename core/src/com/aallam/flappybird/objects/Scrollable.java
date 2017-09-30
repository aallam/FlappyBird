package com.aallam.flappybird.objects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by mouaad on 30/09/17.
 */

public abstract class Scrollable {

  protected Vector2 position;
  protected Vector2 velocity;
  protected int width;
  protected int height;
  protected boolean isScrolledLeft;

  public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
    this.width = width;
    this.height = height;
    position = new Vector2(x, y);
    velocity = new Vector2(scrollSpeed, 0);
    isScrolledLeft = false;
  }

  public void update(float delta) {
    position.add(velocity.cpy().scl(delta));

    // If the Scrollable object is no longer visible:
    if (position.x + width < 0) {
      isScrolledLeft = true;
    }
  }

  // Reset: Should Override in subclass for more specific behavior.
  public void reset(float newX) {
    position.x = newX;
    isScrolledLeft = false;
  }

  // Getters for instance variables
  public boolean isScrolledLeft() {
    return isScrolledLeft;
  }

  public float getTailX() {
    return position.x + width;
  }

  public float getX() {
    return position.x;
  }

  public float getY() {
    return position.y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

}
