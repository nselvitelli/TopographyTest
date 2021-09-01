package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * basic implementation of the PerlinNoise interface. Includes declaring a seed and the ability to
 * space samples from perlin noise a certain amount.
 */
public class TopographyPerlin implements PerlinNoise {

  private static int RANDOM_SEED_AMT = 255;

  private final int seed;
  private final double spread;

  private final BufferedImage noiseImage;


  /**
   * Main constructor for Topography adjusted perlin noise. Initializes the seed and spread of this
   * TopographyPerlin.
   *
   * @param seed   the seed of the perlin noise
   * @param spread how spread apart the perlin samples are from each other
   * @throws IllegalArgumentException if the spread is not greater than zero or the seed is
   *                                  negative
   */
  public TopographyPerlin(int seed, double spread) throws IllegalArgumentException {

    EnsureField.ensureSign(seed, true, "Seed must be positive.");
    EnsureField.ensureGreater(spread, 0, "Spread must be greater than zero.");

    this.seed = seed;
    this.spread = spread;

    try {
      noiseImage = ImageIO.read(new File("res/noise.png"));
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not load perlin noise image. Place file under [res/noise.png]");
    }

    System.out.println("Perlin init with seed: " + seed + ", spread: " + spread);
  }

  /**
   * Convenience constructor that gives a default spread.
   *
   * @param seed the seed for this TopographyPerlin
   */
  public TopographyPerlin(int seed) {
    this(seed, 1.0);
  }

  /**
   * Convenience constructor that gives a random seed.
   *
   * @param spread the spread for this TopographyPerlin
   */
  public TopographyPerlin(double spread) {
    this((int) (Math.random() * RANDOM_SEED_AMT), spread);
  }

  /**
   * Convenience constructor with random seed and default spread.
   */
  public TopographyPerlin() {
    this((int) (Math.random() * RANDOM_SEED_AMT), 1.0);
  }


  @Override
  public double sampleNoise(int x, int y) {

    int xPixel = (x + seed) % noiseImage.getWidth();
    int yPixel = (y + seed) % noiseImage.getHeight();

    Color col = new Color(noiseImage.getRGB(xPixel, yPixel));
    return col.getRed() / 255.0;
  }
}
