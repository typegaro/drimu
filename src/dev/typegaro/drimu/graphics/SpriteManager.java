package dev.typegaro.drimu.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import dev.typegaro.drimu.geometry.Vector2D;

public class SpriteManager<S extends Enum<S>> implements SpriteManagerInterface<S> {
    private final Map<S, BufferedImage[]> sprites;
    private final Path resourceBase;
    private S currentState;
    private int currentFrame;
    private int spriteSize;
    

    public SpriteManager(int spriteSize) {
        this(Paths.get("res"), spriteSize);
    }

    public SpriteManager(Path resourceBase,int spriteSize) {
        this.spriteSize = spriteSize;
        this.resourceBase = resourceBase;
        this.sprites = new HashMap<>();
    }

    @Override
    public void loadSprite(S state, String dirPath) {
        Path spriteDir = resourceBase.resolve(dirPath).normalize();
        if (!Files.isDirectory(spriteDir)) {
            throw new IllegalArgumentException("Sprite directory not found: " + spriteDir.toAbsolutePath());
        }
        try (Stream<Path> paths = Files.list(spriteDir)) {
            BufferedImage[] frames = paths
                    .filter(Files::isRegularFile)
                    .sorted()
                    .map(path -> {
                        try {
                            return ImageIO.read(path.toFile());
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    })
                    .toArray(BufferedImage[]::new);
            sprites.put(state, frames);
        } catch (IOException | UncheckedIOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setState(S state) {
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        if (this.currentState != state) {
            this.currentState = state;
            this.currentFrame = 0;
        }
    }

    @Override
    public void setState(S state, int frame) {
        this.currentState = state;
        this.currentFrame = frame;
    }

    @Override
    public void nextFrame() {
        BufferedImage[] frames = sprites.get(currentState);
        if (frames != null && frames.length > 0) {
            this.currentFrame = (this.currentFrame + 1) % frames.length;
        }
    }

    @Override
    public void previousFrame() {
        BufferedImage[] frames = sprites.get(currentState);
        if (frames != null && frames.length > 0) {
            currentFrame = (currentFrame - 1 + frames.length) % frames.length;
        }
    }

    @Override
    public void drow(Graphics2D g2, Vector2D position) {
        BufferedImage[] frames = sprites.get(currentState);
        g2.drawImage(frames[this.currentFrame], position.x, position.y,spriteSize,spriteSize, null);
    }

    @Override
    public void update() {
        nextFrame();
        System.out.println("Current State: " + currentState + ", Current Frame: " + currentFrame);
    }
}
