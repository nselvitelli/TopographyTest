package view;

import java.awt.Color;
import model.TopographyMap;

public interface TopographyColor extends TopographyMap {

  Color getColor(int x, int y) throws IllegalArgumentException;

}
