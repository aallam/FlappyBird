package com.aallam.flappybird.world;

import com.aallam.flappybird.helpers.AssetLoader;
import com.aallam.flappybird.helpers.ScrollHandler;
import com.aallam.flappybird.objects.Bird;
import com.aallam.flappybird.world.interfaces.GameObjects;

import static com.aallam.flappybird.FlappyBird.SFX_VOLUME;
import static com.aallam.flappybird.objects.Ground.GROUND_OFFSET_Y;

/**
 * Created by mouaad on 28/09/17.
 */

public class GameWorld implements GameObjects {

  private static final int BIRD_DEFAULT_X = 80;
  private static final int BIRD_DEFAULT_Y = 300;

  private Bird bird;
  private ScrollHandler scroller;
  private int score;

  public GameWorld() {
    bird = new Bird(BIRD_DEFAULT_X, BIRD_DEFAULT_Y,
        AssetLoader.TEXTURE_BIRD_ANIMATION.getWidth() / AssetLoader.ANIMATION_BIRD_FRAME_COUNT,
        AssetLoader.TEXTURE_BIRD_ANIMATION.getHeight());
    scroller = new ScrollHandler(this, GROUND_OFFSET_Y);
  }

  public void update(float delta) {
    bird.update(delta);
    scroller.update(delta);
    // Stop in case of collision
    if (bird.isAlive() && scroller.collides(bird)) {
      scroller.stop();
      AssetLoader.SFX_HIT.play(SFX_VOLUME / 20);
      AssetLoader.SFX_DIE.play(SFX_VOLUME / 5);
      bird.die();
    }

    if (!bird.isAlive() && scroller.groundCollides(bird)) {
      bird.stop();
    }
  }

  @Override public Bird getBird() {
    return bird;
  }

  @Override public ScrollHandler getScroller() {
    return scroller;
  }

  @Override public int getScore() {
    return score;
  }

  @Override public void addScore(int increment) {
    score += increment;
  }
}
