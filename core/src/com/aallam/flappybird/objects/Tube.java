package com.aallam.flappybird.objects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

/**
 * Created by mouaad on 30/09/17.
 */

public class Tube extends Scrollable {

  public static final int TUBE_GAP = 100; //Distance between top and bottom tubes
  private static final int LOWEST_OPENING = -180; //Lowest point to draw the top tube from
  private static final int OPENING_MAX = 200;

  private Random random;
  private Rectangle boundsBottom, boundsTop;

  public Tube(float x, float y, int width, int height, float scrollSpeed) {
    super(x, y, width, height, scrollSpeed);
    random = new Random();
    position.y = generateY();
    boundsBottom = new Rectangle(x, y, width, height);
    boundsTop = new Rectangle(x, y + TUBE_GAP + height, width, height);
  }

  @Override public void update(float delta) {
    // Call the update method in the superclass (Scrollable)
    super.update(delta);
    boundsBottom.setPosition(position.x, position.y);
    boundsTop.setPosition(position.x, position.y + TUBE_GAP + height);
  }

  @Override public void reset(float newX) {
    super.reset(newX);
    position.y = generateY();
  }

  public Rectangle getBoundsBottom() {
    return boundsBottom;
  }

  public Rectangle getBoundsTop() {
    return boundsTop;
  }

  private int generateY() {
    return random.nextInt(OPENING_MAX) + LOWEST_OPENING;
  }

  @Override public boolean collides(Bird bird) {
    return position.x < bird.getX() + bird.getWidth() && (Intersector.overlaps(bird.getBounds(),
        boundsTop) || Intersector.overlaps(bird.getBounds(), boundsBottom));
  }
}
