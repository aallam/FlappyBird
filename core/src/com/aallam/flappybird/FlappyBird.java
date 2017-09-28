package com.aallam.flappybird;

import com.aallam.flappybird.helpers.AssetLoader;
import com.aallam.flappybird.screens.GameScreen;
import com.aallam.flappybird.states.GameStateManager;
import com.aallam.flappybird.states.MenuState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends Game {

  public static final String TITLE = "Flappy Bird";
  public static float MUSIC_VOLUME = 0.1f; //10%
  public static float SFX_VOLUME = 0.25f; //25%
  public static final int WIDTH = 480;
  public static final int HEIGHT = 800;

  private GameStateManager gameStateManager;
  private SpriteBatch spriteBatch;
  private MenuState menuState;

  @Override public void create() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    AssetLoader.load();
    setScreen(new GameScreen());
    initMusic();
  }

  private void initMusic() {
    AssetLoader.MUSIC.setVolume(MUSIC_VOLUME); //10%
    AssetLoader.MUSIC.play();
  }

  @Override public void dispose() {
    super.dispose();
    AssetLoader.dispose();
  }
}
