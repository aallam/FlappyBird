package com.aallam.flappybird.helpers;

import com.aallam.flappybird.objects.Bird;
import com.aallam.flappybird.objects.Ground;
import com.aallam.flappybird.objects.Tube;
import com.aallam.flappybird.screens.GameScreen;

import static com.aallam.flappybird.helpers.AssetLoader.GROUND;
import static com.aallam.flappybird.helpers.AssetLoader.TUBE;
import static com.aallam.flappybird.objects.Tube.TUBE_GAP;

/**
 * Created by mouaad on 30/09/17.
 */

public class ScrollHandler {

  private static final int SCROLL_SPEED = -60;

  private Ground frontGround, backGround;
  private Tube tube1, tube2, tube3;

  public ScrollHandler(float yPos) {
    frontGround = new Ground(0, yPos, GROUND.getWidth(), GROUND.getHeight(), SCROLL_SPEED);
    backGround = new Ground(frontGround.getTailX(), yPos, GROUND.getWidth(), GROUND.getHeight(),
        SCROLL_SPEED);

    tube1 = new Tube(GameScreen.WIDTH, 0, TUBE.getWidth(), TUBE.getHeight(), SCROLL_SPEED);
    tube2 =
        new Tube(tube1.getTailX() + TUBE_GAP, 0, TUBE.getWidth(), TUBE.getHeight(), SCROLL_SPEED);
    tube3 =
        new Tube(tube2.getTailX() + TUBE_GAP, 0, TUBE.getWidth(), TUBE.getHeight(), SCROLL_SPEED);
  }

  public void update(float delta) {
    frontGround.update(delta);
    backGround.update(delta);
    tube1.update(delta);
    tube2.update(delta);
    tube3.update(delta);

    // Check if scrolled left and reset accordingly
    if (tube1.isScrolledLeft()) {
      tube1.reset(tube3.getTailX() + TUBE_GAP);
    } else if (tube2.isScrolledLeft()) {
      tube2.reset(tube1.getTailX() + TUBE_GAP);
    } else if (tube3.isScrolledLeft()) {
      tube3.reset(tube2.getTailX() + TUBE_GAP);
    }

    if (frontGround.isScrolledLeft()) {
      frontGround.reset(backGround.getTailX());
    } else if (backGround.isScrolledLeft()) {
      backGround.reset(frontGround.getTailX());
    }
  }

  public Ground getFrontGround() {
    return frontGround;
  }

  public Ground getBackGround() {
    return backGround;
  }

  public Tube getTube1() {
    return tube1;
  }

  public Tube getTube2() {
    return tube2;
  }

  public Tube getTube3() {
    return tube3;
  }

  public void stop() {
    frontGround.stop();
    backGround.stop();
    tube1.stop();
    tube2.stop();
    tube3.stop();
  }

  public boolean collides(Bird bird) {
    return (tube1.collides(bird)
        || tube2.collides(bird)
        || tube3.collides(bird)
        || backGround.collides(bird)
        || frontGround.collides(bird));
  }
}
