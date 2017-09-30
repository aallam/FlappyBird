package com.aallam.flappybird.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by mouaad on 28/09/17.
 */

public class AssetLoader {

  private static final String TEXTURE_PLAY_BUTTON = "button_play.png";
  private static final String TEXTURE_TUBE = "tube.png";
  private static final String TEXTURE_GROUND = "ground.png";
  private static final String TEXTURE_BACKGROUND = "background.png";
  private static final String TEXTURE_REGION_BIRD_FLY = "animation_bird.png";

  private static final String MUSIC_SOUND = "music.mp3";
  private static final String SFX_WING_OGG = "sfx_wing.ogg";

  public static final int ANIMATION_BIRD_FRAME_COUNT = 3;
  public static Texture TEXTURE_BIRD_ANIMATION;

  public static TextureRegion[] BIRD;
  public static Animation<TextureRegion> BIRD_ANIMATION;
  public static Texture PLAY_BUTTON;
  public static Texture TUBE;
  public static Texture GROUND;
  public static Texture BACKGROUND;
  public static Music MUSIC;
  public static Sound SFX_WING;

  public static void load() {
    PLAY_BUTTON = new Texture(Gdx.files.internal(TEXTURE_PLAY_BUTTON));
    TUBE = new Texture(Gdx.files.internal(TEXTURE_TUBE));
    GROUND = new Texture(Gdx.files.internal(TEXTURE_GROUND));
    BACKGROUND = new Texture(Gdx.files.internal(TEXTURE_BACKGROUND));

    BIRD = new TextureRegion[ANIMATION_BIRD_FRAME_COUNT];
    TEXTURE_BIRD_ANIMATION = new Texture(TEXTURE_REGION_BIRD_FLY);
    TextureRegion textureRegion = new TextureRegion(TEXTURE_BIRD_ANIMATION);
    int frameWidth = TEXTURE_BIRD_ANIMATION.getWidth() / ANIMATION_BIRD_FRAME_COUNT;
    // Create frames from the every single texture of the animation
    for (int i = 0; i < ANIMATION_BIRD_FRAME_COUNT; i++) {
      BIRD[i] = new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth,
          textureRegion.getRegionHeight());
    }

    BIRD_ANIMATION = new Animation<TextureRegion>(0.06f, BIRD);
    BIRD_ANIMATION.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    SFX_WING = Gdx.audio.newSound(Gdx.files.internal(SFX_WING_OGG));
    MUSIC = Gdx.audio.newMusic(Gdx.files.internal(MUSIC_SOUND));
    MUSIC.setLooping(true);
  }

  public static void dispose() {
    TEXTURE_BIRD_ANIMATION.dispose();
    PLAY_BUTTON.dispose();
    TUBE.dispose();
    GROUND.dispose();
    BACKGROUND.dispose();
    MUSIC.dispose();
    SFX_WING.dispose();
  }
}
