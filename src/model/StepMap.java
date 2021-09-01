package model;

/**
 * A simple stepping implementation of the Topography Map that floors each height value to a certain
 * number of steps.
 */
public class StepMap implements TopographyMap {

  private final PerlinNoise noise;
  private final int width;
  private final int height;
  private final int numSteps;

  /**
   * Constructs a StepMap given the noise, dimensions, and amt of steps the map is divided into.
   *
   * @param noise    the Perlin Noise used to generate the map
   * @param width    the width of the map
   * @param height   the height of the map
   * @param numSteps the total of steps used to floor the perlin noise
   * @throws IllegalArgumentException if the given PerlinNoise is null, the dimensions are not at
   *                                  least 1x1, or the numSteps is not more than zero
   */
  public StepMap(PerlinNoise noise, int width, int height, int numSteps)
      throws IllegalArgumentException {

    EnsureField.ensureNotNull(noise, "PerlinNoise must not be null.");
    EnsureField.ensureGreater(width, 0, "Width must be greater than zero.");
    EnsureField.ensureGreater(height, 0, "Height must be greater than zero.");
    EnsureField.ensureGreater(numSteps, 0, "NumSteps must be greater than zero.");

    this.noise = noise;
    this.width = width;
    this.height = height;
    this.numSteps = numSteps;
  }

  @Override
  public int getMapWidth() {
    return this.width;
  }

  @Override
  public int getMapHeight() {
    return this.height;
  }

  @Override
  public double getAltitude(int x, int y) throws IllegalArgumentException {

    if (x < 0 || y < 0 || x >= this.width || y >= this.height) {
      throw new IllegalArgumentException(
          "Given coordinate point (" + x + ", " + y + ") is out of bounds.");
    }

    double baseNoise = this.noise.sampleNoise(x, y);
    double sample = (int) ((baseNoise * this.numSteps)) / (double)this.numSteps;

    System.out.println("noise: " + baseNoise + " floored to step: " + sample);

    return sample;
  }
}
