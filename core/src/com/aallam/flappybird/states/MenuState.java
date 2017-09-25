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

  private Texture background;
  private Texture playButton;

  public MenuState(GameStateManager gameStateManager) {
    super(gameStateManager);
    background = new Texture(BACKGROUND);
    playButton = new Texture(PLAY_BUTTON);
  }

  @Override protected void handleInput() {
    if(Gdx.input.justTouched()) {
      gameStateManager.set(new PlayState(gameStateManager));
      dispose();
    }
  }

  @Override public void update(float deltaTime) {
    handleInput();
  }

  @Override public void render(SpriteBatch spriteBatch) {
    spriteBatch.begin();
    //Display the background
    spriteBatch.draw(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
    //Display the play button
    float playButtonX = FlappyBird.WIDTH / 2 - playButton.getWidth() / 2;
    float playButtonY = FlappyBird.HEIGHT / 2 - playButton.getHeight() / 2;
    spriteBatch.draw(playButton, playButtonX, playButtonY);
    spriteBatch.end();
  }

  @Override public void dispose() {
    background.dispose();
    playButton.dispose();
  }
}
