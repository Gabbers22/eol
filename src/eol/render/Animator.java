package eol.render;

import java.awt.image.BufferedImage;

public class Animator {
    private final BufferedImage[] frames;
    private final float frameDuration;
    private float elapsed = 0f;
    private int currentFrame = 0;
    private boolean looping = true;
    private boolean endReached = false;
    private boolean endReported = false;

    public Animator(BufferedImage[] frames, float frameDuration) {
        if (frames == null || frames.length == 0) throw new IllegalArgumentException("Must supply at least one frame");
        this.frames = frames;
        this.frameDuration = frameDuration;
    }

    public void update(float deltaTime) {
        if (frames.length <= 1) return;
    
        elapsed += deltaTime;

        while (!endReached && elapsed >= frameDuration) {
            elapsed -= frameDuration;
            currentFrame++;
            if (currentFrame >= frames.length) {
                if (looping) {
                    currentFrame = 0;
                } else {
                    currentFrame = frames.length - 1;
                    endReached = true;
                }
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[currentFrame];
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public void reset() {
        elapsed = 0f;
        currentFrame = 0;
        endReached = false;
        endReported = false;
    }

    public boolean isFinished() {
        if (looping) return false;
        if (endReached && !endReported) {
            endReported = true;
            return true;
        }
        return false;
    }
}