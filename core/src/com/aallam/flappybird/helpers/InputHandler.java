package com.aallam.flappybird.helpers;

import com.aallam.flappybird.objects.Bird;
import com.aallam.flappybird.world.interfaces.Status;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by mouaad on 28/09/17.
 */

public class InputHandler implements InputProcessor {

  private Bird bird;
  private Status status;

  public InputHandler(Bird bird, Status status) {
    this.bird = bird;
    this.status = status;
  }

  @Override public boolean keyDown(int keycode) {
    return keycode == Input.Keys.SPACE && handleBird();
  }

  @Override public boolean keyUp(int keycode) {
    return false;
  }

  @Override public boolean keyTyped(char character) {
    return false;
  }

  @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return handleBird();
  }

  @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override public boolean scrolled(int amount) {
    return false;
  }

  private boolean handleBird() {
    if (status.isReady()) {
      status.start();
    }

    bird.onClick();

    if (status.isGameOver() || status.isHighScore()) {
      status.restart();
    }

    return true;
  }
}
