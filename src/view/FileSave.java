package view;

import model.TopographyMap;

/**
 * Interface used for different methods of saving a Topography Map.
 */
public interface FileSave {

  /**
   * Saves the given TopographyMap to a specific file type.
   *
   * @param map the topography map to be saved
   * @throws IllegalArgumentException if the given map is null or the image could not be saved
   */
  void saveMap(TopographyMap map, String fileLocation) throws IllegalArgumentException;

}
