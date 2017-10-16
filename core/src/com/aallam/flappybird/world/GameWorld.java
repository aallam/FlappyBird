package com.aallam.flappybird.world;

import com.aallam.flappybird.helpers.AssetLoader;
import com.aallam.flappybird.helpers.ScrollHandler;
import com.aallam.flappybird.objects.Bird;
import com.aallam.flappybird.world.interfaces.GameObjects;
import com.aallam.flappybird.world.interfaces.Status;

import static com.aallam.flappybird.FlappyBird.SFX_VOLUME;
import static com.aallam.flappybird.objects.Ground.GROUND_OFFSET_Y;

/**
 * Created by mouaad on 28/09/17.
 */

public class GameWorld implements GameObjects, Status {

  private static final int BIRD_DEFAULT_X = 80;
  private static final int BIRD_DEFAULT_Y = 300;

  private Bird bird;
  private ScrollHandler scroller;
  private int score;
  private GameState state;

  public GameWorld() {
    state = GameState.READY;
    bird = new Bird(BIRD_DEFAULT_X, BIRD_DEFAULT_Y, AssetLoader.TEXTURE_BIRD_ANIMATION.getWidth() / AssetLoader.ANIMATION_BIRD_FRAME_COUNT,
        AssetLoader.TEXTURE_BIRD_ANIMATION.getHeight());
    scroller = new ScrollHandler(this, GROUND_OFFSET_Y);
  }

  public void update(float delta) {
    switch (state) {
      case READY:
        updateReady(delta);
        break;

      case PLAYING:
      default:
        updatePlaying(delta);
        break;
    }
  }

  private void updateReady(float delta) {
    scroller.updateGround(delta);
  }

  private void updatePlaying(float delta) {
    if (delta > .15f) {
      delta = .15f;
    }

    bird.update(delta);
    scroller.update(delta);
    // Stop in case of collision
    if (bird.isAlive() && scroller.collides(bird)) {
      scroller.stop();
      AssetLoader.SFX_HIT.play(SFX_VOLUME / 20);
      AssetLoader.SFX_DIE.play(SFX_VOLUME / 5);
      bird.die();
    }

    if (!bird.isAlive() && scroller.groundCollides(bird) && !isHighScore()) {
      bird.stop();
      state = GameState.OVER;
      if (score > AssetLoader.getHighScore()) {
        AssetLoader.setHighScore(score);
        state = GameState.SCORE;
      }
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

  @Override public boolean isReady() {
    return state == GameState.READY;
  }

  @Override public boolean isGameOver() {
    return state == GameState.OVER;
  }

  @Override public boolean isHighScore() {
    return state == GameState.SCORE;
  }

  @Override public void start() {
    state = GameState.PLAYING;
  }

  @Override public void restart() {
    state = GameState.READY;
    score = 0;
    bird.onRestart(BIRD_DEFAULT_X, BIRD_DEFAULT_Y);
    scroller.onRestart(GROUND_OFFSET_Y);
  }
}
