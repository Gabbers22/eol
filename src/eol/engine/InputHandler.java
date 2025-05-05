package eol.engine;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class InputHandler extends KeyAdapter {
    private Set<Integer> keysPressed;
    private Set<Integer> keysDown;

    public InputHandler() {
        keysPressed = new HashSet<>();
        keysDown = new HashSet<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keysPressed.add(keyCode);
        keysDown.add(keyCode);
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
}
