package com.aallam.flappybird.objects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by mouaad on 28/09/17.
 */

public class Bird {

  private static final int FLAP = 230;
  private static final int GRAVITY = -460;
  private static final int VELOCITY_LIMIT = -345;

  private Vector2 position;
  private Vector2 velocity;
  private Vector2 acceleration;

  private float rotation;
  private int width;
  private int height;

  public Bird(float x, float y, int width, int height) {
    this.width = width;
    this.height = height;
    position = new Vector2(x, y);
    velocity = new Vector2(0, 0);
    acceleration = new Vector2(0, GRAVITY);
  }

  public void update(float delta) {
    velocity.add(acceleration.cpy().scl(delta));
    if (velocity.y < VELOCITY_LIMIT) {
      velocity.y = VELOCITY_LIMIT;
    }
    position.add(velocity.cpy().scl(delta));
  }

  public void onClick() {
    velocity.y = FLAP;
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
}
