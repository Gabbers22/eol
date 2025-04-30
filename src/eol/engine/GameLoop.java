package eol.engine;

public class GameLoop implements Runnable {
    private EntityManager entityManager;
    /*
     * other objects
     */
    private boolean running;
    private final int targetFps = 60;
    private final long targetTime = 1000 / targetFps; //ms per frame

    public GameLoop(EntityManager entityManager) {
        this.running = false;
        this.entityManager = entityManager;
    }

    public void start() {
        if (!running) {
            running = true;
            Thread gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void stop() {
        running = false;
    }

    // GameLoop entry point
    @Override
    public void run() { 
        long lastUpdateTime = System.currentTimeMillis();
        long fpsTimer = System.currentTimeMillis();
        int frameCount = 0;

        while (running) {
            long currentTime = System.currentTimeMillis();
            long elaspedTime = currentTime - lastUpdateTime;
            lastUpdateTime = currentTime;
            float deltaTime = elaspedTime / 1000.0f;

            update(deltaTime);
            // render();
            frameCount++;

            // FPS counter
            if (currentTime - fpsTimer >= 1000) {
                System.out.println("FPS: " + frameCount);
                frameCount = 0;
                fpsTimer = currentTime;
            }

            try {
                long sleepTime = targetTime - (System.currentTimeMillis() - currentTime);
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(float deltaTime) {
        /*
         * handle inputs
         * check collisions
         */

         entityManager.updateAll(deltaTime);
    }

}
