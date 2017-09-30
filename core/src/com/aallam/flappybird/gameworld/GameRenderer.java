package com.aallam.flappybird.gameworld;

import com.aallam.flappybird.helpers.AssetLoader;
import com.aallam.flappybird.objects.Bird;
import com.aallam.flappybird.objects.Ground;
import com.aallam.flappybird.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

  private Bird bird;
  private Ground ground;

  private Texture groundTxtr;
  private Texture backgroundTxtr;
  private Animation<TextureRegion> birdAnim;

  public GameRenderer(GameWorld world) {
    this.world = world;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, GameScreen.WIDTH, GameScreen.HEIGHT);

    spriteBatch = new SpriteBatch();
    spriteBatch.setProjectionMatrix(camera.combined);

    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(camera.combined);

    initGameObjects();
    initAssets();
  }

  private void initGameObjects() {
    bird = world.getBird();
    ground = world.getGround();
    ground.init(camera.position.x - camera.viewportWidth / 2);
  }

  private void initAssets() {
    backgroundTxtr = AssetLoader.BACKGROUND;
    groundTxtr = AssetLoader.GROUND;
    birdAnim = AssetLoader.BIRD_ANIMATION;
  }

  public void render(float runTime) {
    Gdx.app.log("GameRenderer", "render");

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Begin SpriteBatch
    spriteBatch.begin();

    // Draw background
    spriteBatch.disableBlending(); //Disable transparency
    spriteBatch.draw(backgroundTxtr, 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);

    // Draw ground
    spriteBatch.draw(groundTxtr, ground.getPositionOne().x, ground.getPositionOne().y,
        ground.getWidth(), ground.getHeight());
    spriteBatch.draw(groundTxtr, ground.getPositionTwo().x, ground.getPositionTwo().y,
        ground.getWidth(), ground.getHeight());

    // Draw bird
    spriteBatch.enableBlending(); // enable transparency
    if (bird.doFlap()) {
      spriteBatch.draw(birdAnim.getKeyFrame(runTime), bird.getX(), bird.getY(),
          bird.getWidth() / 2.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1,
          bird.getRotation());
    } else {
      spriteBatch.draw(birdAnim.getKeyFrames()[1], bird.getX(), bird.getY(),
          bird.getWidth() / 3.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1,
          bird.getRotation());
    }

    // End SpriteBatch
    spriteBatch.end();
  }

  @Override public void dispose() {
    spriteBatch.dispose();
    shapeRenderer.dispose();
  }
}
