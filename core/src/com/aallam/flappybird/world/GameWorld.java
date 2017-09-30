package com.aallam.flappybird.world;

import com.aallam.flappybird.helpers.AssetLoader;
import com.aallam.flappybird.helpers.ScrollHandler;
import com.aallam.flappybird.objects.Bird;
import com.badlogic.gdx.utils.Disposable;

import static com.aallam.flappybird.objects.Ground.GROUND_OFFSET_Y;

/**
 * Created by mouaad on 28/09/17.
 */

public class GameWorld implements Disposable {

  private static final int BIRD_DEFAULT_X = 80;
  private static final int BIRD_DEFAULT_Y = 300;

  private Bird bird;
  private ScrollHandler scroller;

  public GameWorld() {
    bird = new Bird(BIRD_DEFAULT_X, BIRD_DEFAULT_Y,
        AssetLoader.TEXTURE_BIRD_ANIMATION.getWidth() / AssetLoader.ANIMATION_BIRD_FRAME_COUNT,
        AssetLoader.TEXTURE_BIRD_ANIMATION.getHeight());
    scroller = new ScrollHandler(GROUND_OFFSET_Y);
  }

  public void update(float delta) {
    bird.update(delta);
    scroller.update(delta);
  }

  public Bird getBird() {
    return bird;
  }

  public ScrollHandler getScroller() {
    return scroller;
  }

  @Override public void dispose() {

  }
}
