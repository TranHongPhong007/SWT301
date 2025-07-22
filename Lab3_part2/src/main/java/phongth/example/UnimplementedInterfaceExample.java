package phongth.example;

import java.util.logging.Logger;

interface Drawable{
    public void draw();
}

class Circle implements Drawable {
    private static final Logger logger = Logger.getLogger(Circle.class.getName());

    @Override
    public void draw() {
        logger.info("Drawing a circle...");
    }
}