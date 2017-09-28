package com.aallam.flappybird.gameworld;

import com.aallam.flappybird.helpers.AssetLoader;
import com.aallam.flappybird.objects.Bird;
import com.aallam.flappybird.objects.Ground;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by mouaad on 28/09/17.
 */

public class GameWorld implements Disposable {

  private static final int BIRD_DEFAULT_X = 80;
  private static final int BIRD_DEFAULT_Y = 300;
  private static final int GROUND_OFFSET_Y = -50;

  private Bird bird;
  private Ground ground;

  public GameWorld() {
    ground =
        new Ground(GROUND_OFFSET_Y, AssetLoader.GROUND.getWidth(), AssetLoader.GROUND.getHeight());
    bird = new Bird(BIRD_DEFAULT_X, BIRD_DEFAULT_Y,
        AssetLoader.TEXTURE_BIRD_ANIMATION.getWidth() / AssetLoader.ANIMATION_BIRD_FRAME_COUNT,
        AssetLoader.TEXTURE_BIRD_ANIMATION.getHeight());
  }

  public void update(float delta) {
    bird.update(delta);
  }

  public Bird getBird() {
    return bird;
  }

  public Ground getGround() {
    return ground;
  }

  @Override public void dispose() {

  }
}
