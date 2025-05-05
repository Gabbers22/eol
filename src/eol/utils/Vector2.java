package eol.utils;

public class Vector2 {
    public static final Vector2 zero = new Vector2(0, 0);
    public static final Vector2 up = new Vector2(0, 1);
    public static final Vector2 down = new Vector2(0, -1);
    public static final Vector2 left = new Vector2(-1, 0);
    public static final Vector2 right = new Vector2(1.0f, 1.0f);

    private float x;
    private float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2 add(Vector2 v) {
        System.out.println("adding vector: " + x + " + " + v.x);
        return new Vector2(x + v.x, y + v.y);
    }
    
    public Vector2 multiply(float scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    public void print() {
        System.out.println("x: " + x + "y: " + y);
    }

}
