package model;

/**
 * Interface used to receive Perlin Noise better formatted for TopographyMaps.
 */
public interface PerlinNoise {

  /**
   * Retrieves a pseudo-random double from [0.0, 1.0)
   * @param x the x-coordinate used to find the perlin noise sample
   * @param y the y-coordinate used to find the perlin noise sample
   * @return the perlin sample at the coordinate (x, y)
   */
  double sampleNoise(int x, int y);

}
