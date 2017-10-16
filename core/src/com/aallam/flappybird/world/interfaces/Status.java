package com.aallam.flappybird.world.interfaces;

/**
 * Created by Mouaad on 01/10/17.
 */

public interface Status {

  boolean isReady();

  boolean isGameOver();

  boolean isHighScore();

  void start();

  void restart();

}
