package com.aallam.flappybird.world.interfaces;

import com.aallam.flappybird.helpers.ScrollHandler;
import com.aallam.flappybird.objects.Bird;

/**
 * Created by Mouaad on 01/10/17.
 */

public interface GameObjects extends Score {

  Bird getBird();

  ScrollHandler getScroller();
}
