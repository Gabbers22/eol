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
    private float phaseInterval = 15.0f;
    private float stunInterval = 7.0f;
    private float attackTimer = 0;
    private float circleInterval = 2.5f;
    private float spreadInterval = 0.8f;
    private float spiralInterval = 0.05f;
    private float spiralAngle = 0.0f;
    private boolean introFinished = false;
    private boolean stun = false;
    private boolean rising = false;
    private AnimationComponent anims = new AnimationComponent();

    private float angleStep = 0;

    public Boss(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager, StatsComponent stats) {
        super(position, offset, width, height, entityManager, stats);

        BufferedImage[] idleFrames = new BufferedImage[8];
        for(int i = 0; i < 8; i++) {
            idleFrames[i] = SpriteManager.getInstance().getSprite("boss_idle_" + i);
        }

        anims.addAnimation("idle", new Animator(idleFrames, 0.1f));
    }

    public void update(float deltaTime) {
        anims.update(deltaTime);

        if (health.getCurrentHealth() == 1) {
            phase = Phase.dead;
        }

        if (stun && phase != Phase.dead) {
            stun(deltaTime);
            return;
        }
        phaseInterval -= deltaTime;
        if (phaseInterval <= 0 && phase != Phase.dead) {
            stun = true;
            stunInterval = 7.0f;
            rising = false;
            return;
        }
        
        checkPhaseTransition();
        attackTimer += deltaTime;
        switch(phase) {
            case intro: introAnimation(deltaTime); break;
            case p1: circleShot(deltaTime, 32, 300); break;
            case p2: spreadShot(deltaTime, 12, 300); break;
            case p3: spiralShot(deltaTime, 300); break;
            case dead: deathAnimation(deltaTime); break;
        }
    }

    private void introAnimation(float deltaTime) {
        movement.update(deltaTime);
        if (position.getY() > 150) introFinished = true;
    }

    private void checkPhaseTransition() {
        float pct = (float)health.getCurrentHealth() / health.getMaxHealth();
        if (health.getCurrentHealth() == 1) phase = Phase.dead;
        else if (pct < 0.33f) phase = Phase.p3;
        else if (pct < 0.66f) phase = Phase.p2;
        else if (introFinished) phase = Phase.p1;
    }

    private void spreadShot(float dt, int count, float speed) {
        movement.bossFloat(dt);
        if (attackTimer < spreadInterval) return;
        attackTimer = 0;

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
        if (attackTimer < circleInterval) return;
        attackTimer = 0;

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

    private void spiralShot(float deltaTime, float speed) {
        movement.bossFloat(deltaTime);
        if (attackTimer < spiralInterval + angleStep) return;
        attackTimer = 0;
        
        Vector2 origin = new Vector2(getPosition().getX() + 40.0f, getPosition().getY() + 25.0f);
        float angleStep = (float)(Math.PI / 12);
        float spawnDistance = -15f;
        spiralAngle = spiralAngle + angleStep;
        Vector2 dir = new Vector2((float)Math.cos(spiralAngle), (float)Math.sin(spiralAngle));

        Vector2 spawnPos = origin.add(dir.multiply(spawnDistance));
        Vector2 vel = dir.multiply(speed);
        entityManager.addEntity(new Projectile(spawnPos, offset, 10, 10, vel.multiply(-1), 10, this, entityManager));
        entityManager.addEntity(new Projectile(spawnPos, offset, 10, 10, vel, 10, this, entityManager));
    }

    private void extremeSpiralShot(float deltaTime, float speed) {
        angleStep = angleStep - (float)(Math.PI / 2000);
        if (attackTimer < spiralInterval / 2) return;
        attackTimer = 0;
        
        Vector2 origin = new Vector2(getPosition().getX() + 40.0f, getPosition().getY() + 25.0f);
        float spawnDistance = -15f;
        spiralAngle = spiralAngle + angleStep;
        Vector2 dir = new Vector2((float)Math.cos(spiralAngle), (float)Math.sin(spiralAngle));

        Vector2 spawnPos = origin.add(dir.multiply(spawnDistance));
        Vector2 vel = dir.multiply(speed);
        entityManager.addEntity(new Projectile(spawnPos, offset, 10, 10, vel.multiply(-1), 10, this, entityManager));
        entityManager.addEntity(new Projectile(spawnPos, offset, 10, 10, vel, 10, this, entityManager));
    }

    private void stun(float deltaTime) {
        stunInterval -= deltaTime;
        if (stunInterval <= 0 && !rising) {
            rising = true;
        }

        if (rising) {
            position = position.add(new Vector2(0f, -3.0f));
            if (position.getY() <= 150f) {
                stun = false;
                phaseInterval = 15.0f; 
            }
        } else {
            movement.update(deltaTime);
        }
    }


    private void deathAnimation(float deltaTime) {
        if (!rising) {
            rising = true;
        }

        position = position.add(new Vector2(0f, -1.5f));
        extremeSpiralShot(deltaTime, 600);

        if (position.getY() <= -100f) {
            health.setCurrentHealth(0);
        }
    }

    public AnimationComponent getAnimationComponent() {
        return anims;
    }
    
}
