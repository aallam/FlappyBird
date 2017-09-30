package com.aallam.flappybird.world;

import com.aallam.flappybird.helpers.AssetLoader;
import com.aallam.flappybird.helpers.ScrollHandler;
import com.aallam.flappybird.objects.Bird;
import com.aallam.flappybird.objects.Ground;
import com.aallam.flappybird.objects.Tube;
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

import static com.aallam.flappybird.objects.Tube.TUBE_GAP;

/**
 * Created by mouaad on 28/09/17.
 */

public class GameRenderer implements Disposable {

  private GameWorld world;
  private OrthographicCamera camera;
  private ShapeRenderer shapeRenderer;
  private SpriteBatch spriteBatch;

  // Game Objects
  private Bird bird;
  private ScrollHandler scroller;
  private Tube tube1, tube2, tube3;
  private Ground frontGround, backGround;

  // Game Assets
  private Texture groundTxtr;
  private Texture backgroundTxtr;
  private Texture tubeTxtr;
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
    scroller = world.getScroller();
    frontGround = scroller.getFrontGround();
    backGround = scroller.getBackGround();
    tube1 = scroller.getTube1();
    tube2 = scroller.getTube2();
    tube3 = scroller.getTube3();
  }

  private void initAssets() {
    backgroundTxtr = AssetLoader.BACKGROUND;
    groundTxtr = AssetLoader.GROUND;
    tubeTxtr = AssetLoader.TUBE;
    birdAnim = AssetLoader.BIRD_ANIMATION;
  }

  public void render(float runTime) {
    //Gdx.app.log("GameRenderer", "render");

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Begin SpriteBatch
    spriteBatch.begin();

    spriteBatch.disableBlending();
    drawBackground();

    spriteBatch.enableBlending();
    drawTubes();

    spriteBatch.disableBlending();
    drawGround();

    spriteBatch.enableBlending();
    drawBird(runTime);

    // End SpriteBatch
    spriteBatch.end();
  }

  private void drawBackground() {
    spriteBatch.draw(backgroundTxtr, 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
  }

  private void drawBird(float runTime) {

    if (bird.doFlap()) {
      spriteBatch.draw(birdAnim.getKeyFrame(runTime), bird.getX(), bird.getY(),
          bird.getWidth() / 2.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1,
          bird.getRotation());
    } else {
      spriteBatch.draw(birdAnim.getKeyFrames()[1], bird.getX(), bird.getY(), bird.getWidth() / 2.0f,
          bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
    }
  }

  private void drawGround() {
    spriteBatch.draw(groundTxtr, frontGround.getX(), frontGround.getY(), frontGround.getWidth(),
        frontGround.getHeight());
    spriteBatch.draw(groundTxtr, backGround.getX(), backGround.getY(), backGround.getWidth(),
        backGround.getHeight());
  }

  private void drawTubes() {
    // Draw Tube 1
    spriteBatch.draw(tubeTxtr, tube1.getX(), tube1.getY(), tube1.getWidth(), tube1.getHeight());
    spriteBatch.draw(tubeTxtr, tube1.getX(), tube1.getY() + tube1.getHeight() * 2 + TUBE_GAP,
        tube1.getWidth(),  - tube1.getHeight());

    // Draw Tube 2
    spriteBatch.draw(tubeTxtr, tube2.getX(), tube2.getY(), tube2.getWidth(), tube2.getHeight());
    spriteBatch.draw(tubeTxtr, tube2.getX(), tube2.getY() + tube2.getHeight() * 2 + TUBE_GAP,
        tube2.getWidth(),  - tube2.getHeight());

    // Draw Tube 3
    spriteBatch.draw(tubeTxtr, tube3.getX(), tube3.getY(), tube3.getWidth(), tube3.getHeight());
    spriteBatch.draw(tubeTxtr, tube3.getX(), tube3.getY() + tube3.getHeight() * 2 + TUBE_GAP,
        tube3.getWidth(),  - tube3.getHeight());
  }

  @Override public void dispose() {
    spriteBatch.dispose();
    shapeRenderer.dispose();
  }
}
