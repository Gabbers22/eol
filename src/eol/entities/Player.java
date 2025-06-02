package eol.entities;

import eol.components.StatsComponent;
import eol.render.Animator;
import eol.render.SpriteManager;

import java.awt.image.BufferedImage;

import eol.components.AnimationComponent;
import eol.components.CombatComponent;
import eol.components.HealthComponent;
import eol.utils.Vector2;
import eol.weapons.Weapon;

public class Player extends Character {
    private enum State {IDLE, WALK, JUMP, ATTACK}
    private State state;
    private AnimationComponent anims;
    private String playerType;
    private Weapon weapon;
    private boolean autoAim;
    private int[] weaponStats = {0, 0, 0, 0};

    public Player(Vector2 position, Vector2 offset, int width, int height, StatsComponent stats, String playerType, Weapon weapon) {
        super(position, offset, width, height, stats);
        this.playerType = playerType;
        anims = new AnimationComponent();
        state = State.IDLE;
        this.weapon = weapon;
        autoAim = false;

        BufferedImage[] idleFrames = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            idleFrames[i] = SpriteManager.getInstance().getSprite("player_idle_" + i);
        }
        BufferedImage[] walkFrames = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            walkFrames[i] = SpriteManager.getInstance().getSprite("player_walk_" + i);
        }
        BufferedImage[] jumpFrames = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            jumpFrames[i] = SpriteManager.getInstance().getSprite("player_jump_" + i);
        }
        BufferedImage[] attackFrames = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            attackFrames[i] = SpriteManager.getInstance().getSprite("player_attack_" + i);
        }

        anims.addAnimation("idle", new Animator(idleFrames, 0.2f));
        anims.addAnimation("walk", new Animator(walkFrames, 0.2f));
        anims.addAnimation("jump", new Animator(jumpFrames, 0.2f));
        anims.addAnimation("attack", new Animator(attackFrames, 0.05f));

        this.combat = new CombatComponent(this, 10, 1.5f, playerType, weapon);
        //combat.initPhaseTimes(anims.get("attack").getFrameDuration());

    }

    public void update(float deltaTime) {
        movement.update(deltaTime);
        handleAnimations();
        anims.update(deltaTime);
        updateEffects(deltaTime);
    }

    public void handleAnimations() {
        State prev = state;
        if (anims.getActiveKey().equals("attack") && !anims.getActive().isFinished()) {
            state = State.ATTACK;
        } else if (getCombatComponent().getJustAttacked()) {
            state = State.ATTACK;
        } else if (!getMovementComponent().isGrounded()) {
            state = State.JUMP;
        } else if (getMovementComponent().getVelocity().getX() != 0f) {
            state = State.WALK;
        } else if (!anims.getActiveKey().equals("idle")) {
            state = State.IDLE;
        }

        if (state != prev) {
            boolean loop = (state != State.ATTACK && state != State.JUMP);
            anims.play(state.name().toLowerCase(), loop);
        }
    }

    public AnimationComponent getAnimationComponent() {
        return anims;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        combat.setWeapon(weapon);
    }

    public void setWeaponStats(int[] newStats) {
        stats.setHealth(stats.getHealth() - weaponStats[0] + newStats[0]);
        stats.setSpeed(stats.getSpeed() - weaponStats[1] + newStats[1]);
        stats.setStrength(stats.getStrength() - weaponStats[2] + newStats[2]);
        stats.setDexterity(stats.getDexterity() - weaponStats[3] + newStats[3]);
        for (int i = 0; i < 4; i++) {
            weaponStats[i] = newStats[i];
        }
    }

    public void setAutoAim(boolean b) { this.autoAim = b; }
    public boolean isAutoAimEnabled() { return autoAim; }

    public String getType() {
        return playerType;
    }
}
