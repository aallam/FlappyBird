package com.aallam.flappybird.gameworld;

import com.aallam.flappybird.helpers.AssetLoader;
import com.aallam.flappybird.objects.Bird;
import com.aallam.flappybird.objects.Ground;
import com.aallam.flappybird.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by mouaad on 28/09/17.
 */

public class GameRenderer implements Disposable {

  private GameWorld world;
  private OrthographicCamera camera;
  private ShapeRenderer shapeRenderer;
  private SpriteBatch spriteBatch;

  public GameRenderer(GameWorld world) {
    this.world = world;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, GameScreen.WIDTH, GameScreen.HEIGHT);

    spriteBatch = new SpriteBatch();
    spriteBatch.setProjectionMatrix(camera.combined);

    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(camera.combined);

    world.getGround().init(camera.position.x - camera.viewportWidth / 2);
  }

  public void render(float runTime) {
    Gdx.app.log("GameRenderer", "render");

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    Bird bird = world.getBird();
    Ground ground = world.getGround();

    // Begin SpriteBatch
    spriteBatch.begin();

    // Draw background
    spriteBatch.disableBlending(); //Disable transparency
    spriteBatch.draw(AssetLoader.BACKGROUND, 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);

    // Draw ground
    spriteBatch.draw(AssetLoader.GROUND, ground.getPositionOne().x, ground.getPositionOne().y,
        ground.getWidth(), ground.getHeight());
    spriteBatch.draw(AssetLoader.GROUND, ground.getPositionTwo().x, ground.getPositionTwo().y,
        ground.getWidth(), ground.getHeight());

    // Draw bird
    spriteBatch.enableBlending();
    spriteBatch.draw(AssetLoader.BIRD_ANIMATION.getKeyFrame(runTime), bird.getX(), bird.getY(),
        bird.getWidth(), bird.getHeight());

    // End SpriteBatch
    spriteBatch.end();
  }

  @Override public void dispose() {
    spriteBatch.dispose();
    shapeRenderer.dispose();
  }
}
