package com.aallam.flappybird.states;

import com.aallam.flappybird.FlappyBird;
import com.aallam.flappybird.sprites.Bird;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by mouaad on 25/09/17.
 */

public class PlayState extends State {

  private static final float WIDTH = FlappyBird.WIDTH / 2;
  private static final float HEIGHT = FlappyBird.HEIGHT / 2;

  private Bird bird;

  public PlayState(GameStateManager gameStateManager) {
    super(gameStateManager);
    bird = new Bird(100, 300);
    camera.setToOrtho(false, WIDTH, HEIGHT);
  }

  @Override protected void handleInput() {

  }

  @Override public void update(float deltaTime) {
    handleInput();
    bird.update(deltaTime);
  }

  @Override public void render(SpriteBatch spriteBatch) {
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
    spriteBatch.end();
  }

  @Override public void dispose() {
    bird.dispose();
  }
}
