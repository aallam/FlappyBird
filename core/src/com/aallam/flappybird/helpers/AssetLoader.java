package com.aallam.flappybird.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by mouaad on 28/09/17.
 */

public class AssetLoader {

  private static final String TEXTURE_PLAY_BUTTON = "img/button_play.png";
  private static final String TEXTURE_TUBE = "img/tube.png";
  private static final String TEXTURE_GROUND = "img/ground.png";
  private static final String TEXTURE_BACKGROUND = "img/background.png";
  private static final String TEXTURE_REGION_BIRD_FLY = "img/animation_bird.png";

  private static final String MUSIC_SOUND = "sound/music.mp3";
  private static final String SFX_WING_OGG = "sound/sfx_flap.ogg";
  private static final String SFX_DIE_OGG = "sound/sfx_die.ogg";
  private static final String SFX_POINT_OGG = "sound/sfx_point.ogg";
  private static final String SFX_HIT_OGG = "sound/sfx_hit.ogg";

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
  public static Sound SFX_DIE;
  public static Sound SFX_HIT;
  public static Sound SFX_POINT;
  public static BitmapFont FONT;
  public static BitmapFont SHADOW;

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

    SFX_POINT = Gdx.audio.newSound(Gdx.files.internal(SFX_POINT_OGG));
    SFX_WING = Gdx.audio.newSound(Gdx.files.internal(SFX_WING_OGG));
    SFX_DIE = Gdx.audio.newSound(Gdx.files.internal(SFX_DIE_OGG));
    SFX_HIT = Gdx.audio.newSound(Gdx.files.internal(SFX_HIT_OGG));
    MUSIC = Gdx.audio.newMusic(Gdx.files.internal(MUSIC_SOUND));
    MUSIC.setLooping(true);

    FONT = new BitmapFont(Gdx.files.internal("text/text.fnt"));
    FONT.getData().setScale(.25f, .25f);
    SHADOW = new BitmapFont(Gdx.files.internal("text/shadow.fnt"));
    SHADOW.getData().setScale(.25f, .25f);
  }

  public static void dispose() {
    TEXTURE_BIRD_ANIMATION.dispose();
    PLAY_BUTTON.dispose();
    TUBE.dispose();
    GROUND.dispose();
    BACKGROUND.dispose();
    MUSIC.dispose();
    SFX_WING.dispose();
    SFX_DIE.dispose();
    SFX_POINT.dispose();
    SFX_HIT.dispose();
    FONT.dispose();
    SHADOW.dispose();
  }
}
