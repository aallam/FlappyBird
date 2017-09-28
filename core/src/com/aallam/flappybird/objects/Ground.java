package com.aallam.flappybird.objects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by mouaad on 30/09/17.
 */

public class Ground {

  private Vector2 positionOne;
  private Vector2 positionTwo;
  private int offset;
  private int width;
  private int height;

  public Ground(int offset, int width, int height) {
    this.offset = offset;
    this.width = width;
    this.height = height;
  }

  public void init(float position) {
    positionOne = new Vector2(position, offset);
    positionTwo = new Vector2(position + width, offset);
  }

  public Vector2 getPositionOne() {
    return positionOne;
  }

  public Vector2 getPositionTwo() {
    return positionTwo;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
