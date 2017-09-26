package com.aallam.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Tube implements GameObject {

  public static final float WIDTH = 52; //Texture (image) width
  private static final String TUBE_TOP = "tube_top.png";
  private static final String TUBE_BOTTOM = "tube_bottom.png";
  private static final int FLUCTUATION = 130;
  private static final int TUBE_GAP = 100; //Distance between top and bottom tubes
  private static final int LOWEST_OPENING = 120; //Lowest point to draw the top tube from

  private static final Texture TEXTURE_TUBE_TOP = new Texture(TUBE_TOP);
  private static final Texture TEXTURE_TUBE_BOTTOM = new Texture(TUBE_BOTTOM);

  private Vector2 positionTopTube, positionBottomTube;
  private Rectangle boundsTopTube, boundsBottomTube;
  private Random random;

  public Tube(float position) {
    random = new Random();
    positionTopTube = new Vector2();
    positionBottomTube = new Vector2();
    boundsTopTube = new Rectangle();
    boundsTopTube.setWidth(TEXTURE_TUBE_TOP.getWidth());
    boundsTopTube.setHeight(TEXTURE_TUBE_TOP.getHeight());
    boundsBottomTube = new Rectangle();
    boundsBottomTube.setWidth(TEXTURE_TUBE_BOTTOM.getWidth());
    boundsBottomTube.setHeight(TEXTURE_TUBE_BOTTOM.getHeight());
    generatePosition(position);
  }

  public Vector2 getPositionTopTube() {
    return positionTopTube;
  }

  public Vector2 getPositionBottomTube() {
    return positionBottomTube;
  }

  public Texture getTopTube() {
    return TEXTURE_TUBE_TOP;
  }

  public Texture getBottomTube() {
    return TEXTURE_TUBE_BOTTOM;
  }

  @Override public void update(float deltaTime) {
    //TODO
  }

  @Override public void dispose() {
    TEXTURE_TUBE_TOP.dispose();
    TEXTURE_TUBE_BOTTOM.dispose();
  }

  public void generatePosition(float position) {
    //Set Top tube Y position
    positionTopTube.set(position, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
    //Set bottom tube Y position depending on top tube position, the tube will be drawn from the bottom, we remove the height to draw it correctly
    positionBottomTube.set(position,
        positionTopTube.y - TUBE_GAP - TEXTURE_TUBE_BOTTOM.getHeight());
    boundsTopTube.setPosition(positionTopTube.x, positionTopTube.y);
    boundsBottomTube.setPosition(positionBottomTube.x, positionBottomTube.y);
  }

  public boolean isCollide(Rectangle bird) {
    return bird.overlaps(boundsTopTube) || bird.overlaps(boundsBottomTube);
  }
}
