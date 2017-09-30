package com.aallam.flappybird.objects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by mouaad on 30/09/17.
 */

public class Ground extends Scrollable {

  public static final int GROUND_OFFSET_Y = -20;

  private Rectangle bounds;

  public Ground(float x, float y, int width, int height, float scrollSpeed) {
    super(x, y, width, height, scrollSpeed);
    bounds = new Rectangle(x, y, width, height);
  }

  @Override public void update(float delta) {
    super.update(delta);
    bounds.setPosition(position.x, position.y);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Rectangle getBounds() {
    return bounds;
  }
}
