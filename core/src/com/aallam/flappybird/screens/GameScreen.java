package com.aallam.flappybird.screens;

import com.aallam.flappybird.FlappyBird;
import com.aallam.flappybird.world.GameRenderer;
import com.aallam.flappybird.world.GameWorld;
import com.aallam.flappybird.helpers.InputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by mouaad on 28/09/17.
 */

public class GameScreen implements Screen {

  public static final int WIDTH = (int) (FlappyBird.WIDTH * 0.617);
  public static final int HEIGHT = (int) (FlappyBird.HEIGHT * 0.617);

  private GameWorld world;
  private GameRenderer renderer;
  private float runTime;

  public GameScreen() {
    Gdx.app.log("GameScreen", "attached");

    world = new GameWorld();
    renderer = new GameRenderer(world);
    Gdx.input.setInputProcessor(new InputHandler(world.getBird()));
  }

  @Override public void render(float delta) {
    runTime += delta;
    world.update(delta);
    renderer.render(runTime);
  }

  @Override public void resize(int width, int height) {

  }

  @Override public void show() {
    Gdx.app.log("GameScreen", "show");
  }

  @Override public void hide() {
    Gdx.app.log("GameScreen", "hide");
  }

  @Override public void pause() {
    Gdx.app.log("GameScreen", "pause");
  }

  @Override public void resume() {
    Gdx.app.log("GameScreen", "resume");
  }

  @Override public void dispose() {
    world.dispose();
  }
}
