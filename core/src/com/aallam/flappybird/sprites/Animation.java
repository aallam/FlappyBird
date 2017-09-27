package com.aallam.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
  private Array<TextureRegion> frames;
  private float maxFrameTime, currentFrameTime;
  private int frameCount;
  private int frame;

  public Animation(Texture texture, int frameCount, float cycleTime) {
    TextureRegion textureRegion = new TextureRegion(texture);
    this.frameCount = frameCount;
    this.maxFrameTime = cycleTime / frameCount;
    this.frame = 0; //start frame
    this.frames = new Array<TextureRegion>();
    int frameWidth = textureRegion.getRegionWidth() / frameCount; //Get a single frame width
    for (int i = 0; i < frameCount; i++) { // Create frames from the every single texture of the animation
      this.frames.add(new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight()));
    }
  }

  public void update(float deltaTime) {
    currentFrameTime += deltaTime;
    if (currentFrameTime > maxFrameTime) {
      frame++;
      if (frame >= frameCount) {
        frame = 0;
      }
      currentFrameTime = 0;
    }
  }

  public TextureRegion getFrame() {
    return frames.get(frame);
  }

  public void dispose() {
    for (TextureRegion frame : frames) {
      frame.getTexture().dispose();
    }
  }
}
