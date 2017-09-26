package com.aallam.flappybird.states;

import com.aallam.flappybird.FlappyBird;
import com.aallam.flappybird.sprites.Bird;
import com.aallam.flappybird.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by mouaad on 25/09/17.
 */

public class PlayState extends State {

  private static final String BACKGROUND = "background.png";
  private static final float WIDTH = FlappyBird.WIDTH / 2;
  private static final float HEIGHT = FlappyBird.HEIGHT / 2;
  private static final float TUBES_COUNT = 3;
  private static final float TUBES_HORIZONTAL_SPACE = 125;
  private static final float CAMERA_OFFSET = 80;

  private Texture background;
  private Bird bird;
  private Array<Tube> tubes;

  public PlayState(GameStateManager gameStateManager) {
    super(gameStateManager);
    bird = new Bird(0, 300);
    background = new Texture(BACKGROUND);
    camera.setToOrtho(false, WIDTH, HEIGHT);
    tubes = new Array<Tube>();
    for (int i = 1; i <= TUBES_COUNT; i++) {
      tubes.add(new Tube(i * (TUBES_HORIZONTAL_SPACE + Tube.WIDTH)));
    }
  }

  @Override protected void handleInput() {
    if (Gdx.input.justTouched()) {
      bird.jump();
    }
  }

  @Override public void update(float deltaTime) {
    handleInput();
    bird.update(deltaTime);
    camera.position.x = bird.getPosition().x + CAMERA_OFFSET; //make the camera to follow the bird
    for (Tube tube : tubes) {
      if (camera.position.x - (camera.viewportWidth / 2)
          > tube.getPositionTopTube().x + tube.getTopTube().getWidth()) {
        tube.generatePosition(
            tube.getPositionTopTube().x + (Tube.WIDTH + TUBES_HORIZONTAL_SPACE) * TUBES_COUNT);
      }
      // Check every tube if it collides with the bird (not optimal for big wold)
      if (tube.isCollide(bird.getBounds())) {
        gameStateManager.set(new PlayState(gameStateManager));
      }
    }
    camera.update();
  }

  @Override public void render(SpriteBatch spriteBatch) {
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    spriteBatch.draw(background, camera.position.x - camera.viewportWidth / 2, 0);
    spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
    for (Tube tube : tubes) {
      spriteBatch.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
      spriteBatch.draw(tube.getBottomTube(), tube.getPositionBottomTube().x,
          tube.getPositionBottomTube().y);
    }
    spriteBatch.end();
  }

  @Override public void dispose() {
    bird.dispose();
    for (Tube tube : tubes) {
      tube.dispose();
    }
    background.dispose();
  }
}
