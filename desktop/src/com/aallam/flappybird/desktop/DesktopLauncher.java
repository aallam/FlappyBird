package com.aallam.flappybird.desktop;

import com.aallam.flappybird.FlappyBird;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = FlappyBird.WIDTH;
    config.height = FlappyBird.HEIGHT;
    config.title = FlappyBird.TITLE;
    new LwjglApplication(new FlappyBird(), config);
  }
}
