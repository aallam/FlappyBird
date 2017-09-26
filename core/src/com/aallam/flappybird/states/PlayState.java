package com.aallam.flappybird.states;

import com.aallam.flappybird.FlappyBird;
import com.aallam.flappybird.sprites.Bird;
import com.aallam.flappybird.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by mouaad on 25/09/17.
 */

public class PlayState extends State {

  private static final String BACKGROUND = "background.png";
  private static final float WIDTH = FlappyBird.WIDTH / 2;
  private static final float HEIGHT = FlappyBird.HEIGHT / 2;

  private Texture background;
  private Bird bird;
  private Tube tube;

  public PlayState(GameStateManager gameStateManager) {
    super(gameStateManager);
    bird = new Bird(50, 300);
    tube = new Tube(150);
    background = new Texture(BACKGROUND);
    camera.setToOrtho(false, WIDTH, HEIGHT);
  }

  @Override protected void handleInput() {
    if (Gdx.input.justTouched()) {
      bird.jump();
    }
  }

  @Override public void update(float deltaTime) {
    handleInput();
    bird.update(deltaTime);
  }

  @Override public void render(SpriteBatch spriteBatch) {
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    spriteBatch.draw(background, camera.position.x - camera.viewportWidth / 2, 0);
    spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
    spriteBatch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
    spriteBatch.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
    spriteBatch.end();
  }

  @Override public void dispose() {
    bird.dispose();
    tube.dispose();
    background.dispose();
  }
}
