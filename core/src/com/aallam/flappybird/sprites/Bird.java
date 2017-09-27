package com.aallam.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import static com.aallam.flappybird.FlappyBird.SFX_VOLUME;

/**
 * Created by mouaad on 25/09/17.
 */

public class Bird implements Disposable {

  private static final String ANIMATION = "animation_bird.png";
  private static final String SFX_WING = "sfx_wing.ogg";
  private static final String SFX_HIT = "sfx_hit.ogg";
  private static final String SFX_DIE = "sfx_die.ogg";

  private static final int GRAVITY = -15;
  private static final int MOVEMENT = 100;
  private static final int JUMP_VELOCITY = 300;

  private Vector2 position;
  private Vector2 velocity;
  private Rectangle bounds;
  private Texture textureAnimation;
  private Animation animation;
  private Sound soundWing;
  private Sound soundHit;
  private Sound soundDie;

  public Bird(float x, float y) {
    textureAnimation = new Texture(ANIMATION);
    position = new Vector2(x, y);
    velocity = new Vector2(0, 0);
    bounds = new Rectangle(x, y, textureAnimation.getWidth() / 3, textureAnimation.getHeight());
    animation = new Animation(textureAnimation, 3, 0.5f);
    initSounds();
  }

  private void initSounds() {
    soundWing = Gdx.audio.newSound(Gdx.files.internal(SFX_WING));
    soundHit = Gdx.audio.newSound(Gdx.files.internal(SFX_HIT));
    soundDie = Gdx.audio.newSound(Gdx.files.internal(SFX_DIE));
  }

  public void update(float deltaTime) {
    animation.update(deltaTime);
    if (position.y > 0) { //Add gravity only if above y axis
      velocity.add(0, GRAVITY);
    }
    velocity.scl(deltaTime); //Scale the velocity by delta time
    position.add(MOVEMENT * deltaTime, velocity.y);
    if (position.y < 0) { //Check to not go down out of the screen
      position.y = 0;
    }
    velocity.scl(1 / deltaTime); //Add the scaled version to be used next
    bounds.setPosition(position.x, position.y); //Update bounds as the bird moves
  }

  @Override public void dispose() {
    animation.dispose();
    textureAnimation.dispose();
    soundWing.dispose();
  }

  public void jump() {
    velocity.y = JUMP_VELOCITY;
    soundWing.play(SFX_VOLUME);
  }

  public Rectangle getBounds() {
    return bounds;
  }

  public Vector2 getPosition() {
    return position;
  }

  public TextureRegion getTexture() {
    return animation.getFrame();
  }

  public void playHit() {
    soundHit.play(SFX_VOLUME);
  }

  public void playDie() {
    soundDie.play(SFX_VOLUME);
  }
}
