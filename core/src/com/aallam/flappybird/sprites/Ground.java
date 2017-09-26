package com.aallam.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by mouaad on 26/09/17.
 */

public class Ground implements Disposable {
  public static final float WIDTH = 336; //Texture (image) width
  public static final int OFFSET_Y = -50;
  private static final String GROUND = "ground.png";

  private Texture texture;
  private Vector2 positionOne, positionTwo;

  public Ground(float position) {
    texture = new Texture(GROUND);
    positionOne = new Vector2(position, OFFSET_Y);
    positionTwo = new Vector2(position + WIDTH, OFFSET_Y);
  }

  public Vector2 getPositionOne() {
    return positionOne;
  }

  public Vector2 getPositionTwo() {
    return positionTwo;
  }

  public Texture getTexture() {
    return texture;
  }

  @Override public void dispose() {
    texture.dispose();
  }
}
