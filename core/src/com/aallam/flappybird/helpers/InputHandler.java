package com.aallam.flappybird.helpers;

import com.aallam.flappybird.objects.Bird;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by mouaad on 28/09/17.
 */

public class InputHandler implements InputProcessor {

  private Bird bird;

  public InputHandler(Bird bird) {
    this.bird = bird;
  }

  @Override public boolean keyDown(int keycode) {
    if (keycode == Input.Keys.SPACE) {
      bird.onClick();
      return true;
    }
    return false;
  }

  @Override public boolean keyUp(int keycode) {
    return false;
  }

  @Override public boolean keyTyped(char character) {
    return false;
  }

  @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    bird.onClick();
    return true;
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
}
