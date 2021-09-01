package model;

/**
 * Interface to represent a topography map. Main functions include retrieving the dimensions of the
 * map and the altitude of each point.
 */
public interface TopographyMap {

  /**
   * Retrieves the width of this Topography Map
   *
   * @return the width map dimension
   */
  int getMapWidth();

  /**
   * Retrieves the height of this Topography Map
   *
   * @return the height map dimension
   */
  int getMapHeight();

  /**
   * Retrieves the altitude at the given specific point.
   *
   * @param x the x coordinate of the specified point
   * @param y the y coordinate of the specified point
   * @return the altitude at the point (x, y) as a double from [0.0, 1.0)
   * @throws IllegalArgumentException if the given parameters form an invalid point out of bounds of
   *                                  this map
   */
  double getAltitude(int x, int y) throws IllegalArgumentException;
}
