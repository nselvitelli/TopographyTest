package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.EnsureField;
import model.TopographyMap;

public class SaveColorSingleShadePNG implements FileSave {

  private final Color baseColor;

  public SaveColorSingleShadePNG(Color baseColor) {
    EnsureField.ensureNotNull(baseColor, "The given color must not be null.");
    this.baseColor = baseColor;
  }


  @Override
  public void saveMap(TopographyMap map, String fileLocation) {
    EnsureField.ensureNotNull(map, "The given map must not be null");

    BufferedImage img = new BufferedImage(map.getMapWidth(), map.getMapHeight(), BufferedImage.TYPE_INT_RGB);

    for(int x = 0; x < map.getMapWidth(); x++) {
      for (int y = 0; y < map.getMapHeight(); y++) {

        double altitude = map.getAltitude(x, y);

        //get color shade

        Color color = new Color((int)(altitude * baseColor.getRed()),
            (int)(altitude * baseColor.getGreen()),
            (int)(altitude * baseColor.getBlue()));

        if(isHeightEdge(map, x, y)) {
          color = Color.WHITE;
        }

        img.setRGB(x, y, color.getRGB());
      }
    }

    try {
      ImageIO.write(img, "png", new File(fileLocation));
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot save image");
    }

  }

  private boolean isHeightEdge(TopographyMap map, int xOrigin, int yOrigin) {

    double currentAltitude = map.getAltitude(xOrigin, yOrigin);

    for(int x = xOrigin - 1; x <= xOrigin + 1; x++) {
      for(int y = yOrigin - 1; y <= yOrigin + 1; y++) {
        if(x >= 0 && y >= 0 && x < map.getMapWidth() && y < map.getMapHeight()) {
          if(map.getAltitude(x, y) < currentAltitude) {
            return true;
          }
        }
      }
    }

    return false;
  }
}
