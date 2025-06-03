package eol.engine;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import eol.utils.Vector2;

public class InputHandler extends KeyAdapter {
    private Set<Integer> keysPressed;
    private Set<Integer> keysDown;

    public InputHandler() {
        keysPressed = new HashSet<>(); // Tracks one time presses, is cleared every game tick
        keysDown = new HashSet<>(); // Tracks currently held keys, keys are removed when released
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (!keysDown.contains(keyCode)) {
            String keyName = KeyEvent.getKeyText(keyCode);
            System.out.println("Key press: " + keyName);
            keysPressed.add(keyCode);
            keysDown.add(keyCode);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keysDown.remove(keyCode);
    }

    public boolean isKeyPressed(int keyCode) {
        return keysPressed.contains(keyCode);
    }

    public boolean isKeyDown(int keyCode) {
        return keysDown.contains(keyCode);
    }

    public void clearKeysPressed() {
        keysPressed.clear();
    }

    public Set<Integer> getKeysDown() {
        return keysDown;
    }

    public Set<Integer> getKeysPressed() {
        return keysPressed;
    }

    public Vector2 getDirectionalInput() {
        Vector2 direction = Vector2.zero;

        if (isKeyDown(KeyEvent.VK_LEFT) || isKeyDown(KeyEvent.VK_A)) {
            direction = direction.add(Vector2.left);
        }
        if (isKeyDown(KeyEvent.VK_RIGHT) || isKeyDown(KeyEvent.VK_D)) {
            direction = direction.add(Vector2.right);
        }
        return direction;
    }

    public boolean isAttackKeyPressed() {
        return isKeyPressed(KeyEvent.VK_X) || isKeyPressed(KeyEvent.VK_P);
    }

    public boolean isAttackKeyDown() {
        return isKeyDown(KeyEvent.VK_X) || isKeyDown(KeyEvent.VK_P);
    }

}
