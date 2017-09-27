package com.aallam.flappybird.states;

import com.aallam.flappybird.FlappyBird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by mouaad on 25/09/17.
 */

public class MenuState extends State {

  private static final String BACKGROUND = "background.png";
  private static final String PLAY_BUTTON = "button_play.png";
  private static final float WIDTH = FlappyBird.WIDTH / 2;
  private static final float HEIGHT = FlappyBird.HEIGHT / 2;

  private Texture background;
  private Texture playButton;

  public MenuState(GameStateManager gameStateManager) {
    super(gameStateManager);
    camera.setToOrtho(false, WIDTH, HEIGHT);
    background = new Texture(BACKGROUND);
    playButton = new Texture(PLAY_BUTTON);
  }

  @Override protected void handleInput() {
    if(Gdx.input.justTouched()) {
      gameStateManager.set(new PlayState(gameStateManager));
    }
  }

  @Override public void update(float deltaTime) {
    handleInput();
  }

  @Override public void render(SpriteBatch spriteBatch) {
    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    //Display the background
    spriteBatch.draw(background, 0, 0);
    //Display the play button
    float playButtonX = camera.position.x - playButton.getWidth() / 2;
    float playButtonY = camera.position.y - playButton.getHeight()/ 2;
    spriteBatch.draw(playButton, playButtonX, playButtonY);
    spriteBatch.end();
  }

  @Override public void dispose() {
    background.dispose();
    playButton.dispose();
  }
}
