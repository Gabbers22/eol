package eol.entities;

import java.awt.image.BufferedImage;

import eol.audio.AudioManager;
import eol.components.AnimationComponent;
import eol.components.StatsComponent;
import eol.engine.EntityManager;
import eol.render.Animator;
import eol.render.SpriteManager;
import eol.utils.Vector2;

public class Boss extends Enemy {
    private enum Phase { intro, p1, p2, p3, dead  }
    private Phase phase = Phase.intro;
    private float phaseTimer = 0;
    private float spreadInterval = 2.0f;
    private boolean introFinished = false;
    private AnimationComponent anims = new AnimationComponent();

    public Boss(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager, StatsComponent stats) {
        super(position, offset, width, height, entityManager, stats);

        BufferedImage[] idleFrames = new BufferedImage[8];
        for(int i = 0; i < 8; i++) {
            idleFrames[i] = SpriteManager.getInstance().getSprite("boss_idle_" + i);
        }

        anims.addAnimation("idle", new Animator(idleFrames, 0.1f));
    }

    public void update(float deltaTime) {
        checkPhaseTransition();
        phaseTimer += deltaTime;
        switch(phase) {
            case intro: introAnimation(deltaTime); break;
            case p1: circleShot(deltaTime, 12, 300); break;
            case p2: spreadShot(deltaTime, 12, 300); break;
            case p3: fireWave(deltaTime); break;
            case dead: deathAnimation(deltaTime); break;
        }
        anims.update(deltaTime);
    }

    private void introAnimation(float deltaTime) {
        movement.update(deltaTime);
        if (position.getY() > 150) introFinished = true;
    }

    private void checkPhaseTransition() {
        float pct = (float)health.getCurrentHealth() / health.getMaxHealth();
        if (pct < 0.01f) phase = Phase.dead;
        else if (pct < 0.25f) phase = Phase.p3;
        else if (pct < 0.5f) phase = Phase.p2;
        else if (introFinished) phase = Phase.p1;
    }

    private void spreadShot(float dt, int count, float speed) {
        movement.bossFloat(dt);
        if (phaseTimer < spreadInterval) return;
        phaseTimer = 0;

        Vector2 origin = new Vector2(getPosition().getX() + 40.0f, getPosition().getY() + 25.0f);
        Vector2 toPlayer = player.getPosition().subtract(origin).normalize();

        float baseAngle = (float)Math.atan2(toPlayer.getY(), toPlayer.getX());
        float spreadStep = (float)(Math.PI / 12);
        float startAngle = baseAngle - spreadStep * (count - 1) / 2f;
        float spawnDistance = -15f;

        for (int i = 0; i < count; i++) {
            float angle = startAngle + spreadStep * i;
            Vector2 dir = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));
            Vector2 spawnPos = origin.add(dir.multiply(spawnDistance));

            Vector2 vel = dir.multiply(speed);
            entityManager.addEntity(new Projectile(spawnPos, offset, 10, 10, vel.multiply(-1), 10, this, entityManager));
        }
    }

    private void circleShot(float dt, int count, float speed) {
        movement.bossFloat(dt);
        if (phaseTimer < spreadInterval) return;
        phaseTimer = 0;

        Vector2 origin = new Vector2(getPosition().getX() + 40.0f, getPosition().getY() + 25.0f);
        Vector2 toPlayer = player.getPosition().subtract(origin).normalize();

        float baseAngle = (float)Math.atan2(toPlayer.getY(), toPlayer.getX());
        float spreadStep = (float)(Math.PI / 6);
        float startAngle = baseAngle - spreadStep * (count - 1) / 2f;
        float spawnDistance = -15f;

        for (int i = 0; i < count; i++) {
            float angle = startAngle + spreadStep * i;
            Vector2 dir = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));
            Vector2 spawnPos = origin.add(dir.multiply(spawnDistance));

            Vector2 vel = dir.multiply(speed);
            entityManager.addEntity(new Projectile(spawnPos, offset, 10, 10, vel.multiply(-1), 10, this, entityManager));
        }
    }

    private void fireWave(float deltaTime) {

    }

    private void deathAnimation(float deltaTime) {

    }

    public AnimationComponent getAnimationComponent() {
        return anims;
    }
    
}
