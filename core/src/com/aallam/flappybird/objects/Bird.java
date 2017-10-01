package com.aallam.flappybird.objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mouaad on 28/09/17.
 */

public class Bird {

  // Class constants
  private static final int FLAP = 230;
  private static final int GRAVITY = -460;
  private static final int VELOCITY_LIMIT = -345;
  private static final int ROTATION_CW = 480;
  private static final int ROTATION_CCW = 360;
  private static final int ROTATION_MAX_UP = 20;
  private static final int ROTATION_MAX_DOWN = -90;
  private static final int FALL = -110;
  private static final int FLAP_AT = -70;

  // Instance variables
  private Vector2 position;
  private Vector2 velocity;
  private Vector2 acceleration;
  private Circle bounds;

  private float rotation;
  private int width;
  private int height;
  private boolean alive;

  public Bird(float x, float y, int width, int height) {
    this.width = width;
    this.height = height;
    position = new Vector2(x, y);
    velocity = new Vector2(0, 0);
    acceleration = new Vector2(0, GRAVITY);
    bounds = new Circle();
    alive = true;
  }

  public void update(float delta) {
    //Fall
    velocity.add(acceleration.cpy().scl(delta));
    if (velocity.y < VELOCITY_LIMIT) {
      velocity.y = VELOCITY_LIMIT;
    }
    position.add(velocity.cpy().scl(delta));

    bounds.set(position.x + 18, position.y + 12, 12.5f);

    // Rotate counterclockwise
    if (velocity.y > 0) {
      rotation += ROTATION_CW * delta;
      if (rotation > ROTATION_MAX_UP) {
        rotation = ROTATION_MAX_UP;
      }
    }

    // Rotate clockwise
    if (isFalling()) {
      rotation -= ROTATION_CCW * delta;
      if (rotation < ROTATION_MAX_DOWN) {
        rotation = ROTATION_MAX_DOWN;
      }
    }
  }

  public boolean onClick() {
    velocity.y = FLAP;
    return true;
  }

  public boolean isFalling() {
    return velocity.y < FALL;
  }

  public boolean doFlap() {
    return velocity.y > FLAP_AT;
  }

  public float getX() {
    return position.x;
  }

  public float getY() {
    return position.y;
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public float getRotation() {
    return rotation;
  }

  public Circle getBounds() {
    return bounds;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }
}
