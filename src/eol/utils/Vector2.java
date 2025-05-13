package eol.utils;

public class Vector2 {
    public static final Vector2 zero = new Vector2(0, 0);
    public static final Vector2 up = new Vector2(0, 1);
    public static final Vector2 down = new Vector2(0, -1);
    public static final Vector2 left = new Vector2(-1, 0);
    public static final Vector2 right = new Vector2(1, 0);

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
        return new Vector2(x + v.x, y + v.y);
    }

    public Vector2 subtract(Vector2 v) {
        return new Vector2(v.x - x, v.y - y);
    }
    
    public Vector2 multiply(float scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2 normalize() {
        float mag = magnitude();
        if (mag == 0) return Vector2.zero; // might not work
        return new Vector2(x/ mag, y/ mag);
    }

    public void print() {
        System.out.println("x: " + x + "y: " + y);
    }

}
