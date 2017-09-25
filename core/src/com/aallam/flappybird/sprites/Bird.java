package com.aallam.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mouaad on 25/09/17.
 */

public class Bird {

  private static final String BIRD = "bird.png";
  private static final int GRAVITY = -15;

  private Vector2 position;
  private Vector2 velocity;
  private Texture texture;

  public Bird(float x, float y) {
    position = new Vector2(x, y);
    velocity = new Vector2(0, 0);
    texture = new Texture(BIRD);
  }

  public void update(float deltaTime) {
    velocity.add(0, GRAVITY);
    velocity.scl(deltaTime); //Scale the velocity by delta time
    position.add(0, velocity.y);
    velocity.scl(1 / deltaTime); //Add the scaled version to be used next
  }

  public void dispose() {
    texture.dispose();
  }

  public Vector2 getPosition() {
    return position;
  }

  public Texture getTexture() {
    return texture;
  }
}
