package com.aallam.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Tube implements GameObject {

  private static final String TUBE_TOP = "tube_top.png";
  private static final String TUBE_Bottom = "tube_bottom.png";
  private static final int FLUCTUATION = 130;
  private static final int TUBE_GAP = 100; //Distance between top and bottom tubes
  private static final int LOWEST_OPENING = 120; //Lowest point to draw the top tube from

  private Vector2 positionTopTube, positionBottomTube;
  private Texture topTube, bottomTube;
  private Random random;

  public Tube(float position) {
    topTube = new Texture(TUBE_TOP);
    bottomTube = new Texture(TUBE_Bottom);
    random = new Random();
    //Set Top tube Y position
    positionTopTube = new Vector2(position, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
    //Set bottom tube Y position depending on top tube position, the tube will be drawn from the bottom, we remove the height to draw it correctly
    positionBottomTube = new Vector2(position, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());
  }

  public Vector2 getPositionTopTube() {
    return positionTopTube;
  }

  public Vector2 getPositionBottomTube() {
    return positionBottomTube;
  }

  public Texture getTopTube() {
    return topTube;
  }

  public Texture getBottomTube() {
    return bottomTube;
  }

  @Override public void update(float deltaTime) {
    //TODO
  }

  @Override public void dispose() {
    topTube.dispose();
    bottomTube.dispose();
  }
}
