package com.aallam.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import java.util.Random;

public class Tube implements Disposable {

  public static final float WIDTH = 52; //Texture (image) width
  private static final String TUBE_TOP = "tube_top.png";
  private static final String TUBE_BOTTOM = "tube_bottom.png";
  private static final int FLUCTUATION = 130;
  private static final int TUBE_GAP = 100; //Distance between top and bottom tubes
  private static final int LOWEST_OPENING = 120; //Lowest point to draw the top tube from

  private Texture tubeTop, tubeBottom;
  private Vector2 positionTopTube, positionBottomTube;
  private Rectangle boundsTopTube, boundsBottomTube;
  private Random random;

  public Tube(float position) {
    random = new Random();
    tubeTop = new Texture(TUBE_TOP);
    tubeBottom = new Texture(TUBE_BOTTOM);
    positionTopTube = new Vector2();
    positionBottomTube = new Vector2();
    boundsTopTube = new Rectangle();
    boundsTopTube.setWidth(tubeTop.getWidth());
    boundsTopTube.setHeight(tubeTop.getHeight());
    boundsBottomTube = new Rectangle();
    boundsBottomTube.setWidth(tubeBottom.getWidth());
    boundsBottomTube.setHeight(tubeBottom.getHeight());
    generatePosition(position);
  }

  public Vector2 getPositionTopTube() {
    return positionTopTube;
  }

  public Vector2 getPositionBottomTube() {
    return positionBottomTube;
  }

  public Texture getTopTube() {
    return tubeTop;
  }

  public Texture getBottomTube() {
    return tubeBottom;
  }

  @Override public void dispose() {
    tubeTop.dispose();
    tubeBottom.dispose();
  }

  public void generatePosition(float position) {
    //Set Top tube Y position
    positionTopTube.set(position, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
    //Set bottom tube Y position depending on top tube position, the tube will be drawn from the bottom, we remove the height to draw it correctly
    positionBottomTube.set(position, positionTopTube.y - TUBE_GAP - tubeBottom.getHeight());

    boundsTopTube.setPosition(positionTopTube.x, positionTopTube.y);
    boundsBottomTube.setPosition(positionBottomTube.x, positionBottomTube.y);
  }

  public boolean isCollide(Rectangle bird) {
    return bird.overlaps(boundsTopTube) || bird.overlaps(boundsBottomTube);
  }
}
