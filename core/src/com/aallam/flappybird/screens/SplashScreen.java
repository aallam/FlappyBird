package com.aallam.flappybird.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import com.aallam.flappybird.FlappyBird;
import com.aallam.flappybird.accessors.SpriteAccessor;
import com.aallam.flappybird.helpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mouaad on 04/10/17.
 */

public class SplashScreen implements Screen {

  private static final String TAG = "SplashScreen";
  private static final float SCALE = 0.7f;

  private TweenManager tweenManager;
  private SpriteBatch spriteBatch;
  private Sprite sprite;
  private FlappyBird game;

  public SplashScreen(FlappyBird flappyBird) {
    game = flappyBird;
  }

  @Override public void show() {
    spriteBatch = new SpriteBatch();
    sprite = new Sprite(AssetLoader.LOGO);
    sprite.setColor(1, 1, 1, 0);

    //Set the sprite's size
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    float scale = width * SCALE / sprite.getWidth();
    sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);

    //Set the sprite's position
    float positionX = width / 2 - sprite.getWidth() / 2;
    float positionY = height / 2 - sprite.getHeight() / 2;
    sprite.setPosition(positionX, positionY);

    setupTween();
  }

  private void setupTween() {
    Tween.registerAccessor(Sprite.class, new SpriteAccessor());
    tweenManager = new TweenManager();

    TweenCallback cb = new TweenCallback() {
      @Override public void onEvent(int type, BaseTween<?> source) {
        game.setScreen(new GameScreen());
      }
    };

    Tween.to(sprite, SpriteAccessor.ALPHA, 0.8f)
        .target(1)
        .ease(TweenEquations.easeInOutQuad)
        .repeatYoyo(1, 0.4f)
        .setCallback(cb)
        .setCallbackTriggers(TweenCallback.COMPLETE)
        .start(tweenManager);
  }

  @Override public void render(float delta) {
    tweenManager.update(delta);
    Gdx.gl.glClearColor(0, 0, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    spriteBatch.begin();
    sprite.draw(spriteBatch);
    spriteBatch.end();
  }

  @Override public void resize(int width, int height) {
    Gdx.app.log(TAG, "resize");
  }

  @Override public void hide() {
    Gdx.app.log(TAG, "hide");
  }

  @Override public void pause() {
    Gdx.app.log(TAG, "pause");
  }

  @Override public void resume() {
    Gdx.app.log(TAG, "resume");
  }

  @Override public void dispose() {
    spriteBatch.dispose();
  }
}
