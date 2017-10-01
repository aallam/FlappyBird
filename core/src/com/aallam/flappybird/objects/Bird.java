package com.aallam.flappybird.objects;

import com.aallam.flappybird.helpers.AssetLoader;
import com.aallam.flappybird.screens.GameScreen;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import static com.aallam.flappybird.FlappyBird.SFX_VOLUME;

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
    bounds.set(position.x + 18, position.y + 12, 12.5f);

    // Fall
    velocity.add(acceleration.cpy().scl(delta));
    if (velocity.y < VELOCITY_LIMIT) {
      velocity.y = VELOCITY_LIMIT;
    }

    // Ceiling
    if (position.y > GameScreen.HEIGHT - 15) {
      position.y = GameScreen.HEIGHT - 15;
      velocity.y = 0;
    }

    position.add(velocity.cpy().scl(delta));

    // Rotate counterclockwise
    if (velocity.y > 0) {
      rotation += ROTATION_CW * delta;
      if (rotation > ROTATION_MAX_UP) {
        rotation = ROTATION_MAX_UP;
      }
    }

    // Rotate clockwise
    if (isFalling() && alive) {
      rotation -= ROTATION_CCW * delta;
      if (rotation < ROTATION_MAX_DOWN) {
        rotation = ROTATION_MAX_DOWN;
      }
    }
  }

  public boolean onClick() {
    if (alive) {
      AssetLoader.SFX_WING.play(SFX_VOLUME);
      velocity.y = FLAP;
    }
    return true;
  }

  public boolean isFalling() {
    return velocity.y < FALL;
  }

  public boolean doFlap() {
    return alive && velocity.y > FLAP_AT;
  }

  public void die() {
    alive = false;
    velocity.y = 0;
  }

  public void decelerate() {
    acceleration.y = 0;
  }

  public void stop() {
    velocity.y = 0;
    acceleration.y = 0;
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

  private void checkCeiling() {

  }
}
