package controller;

import java.awt.Color;
import model.StepMap;
import model.TopographyMap;
import model.TopographyPerlin;
import view.FileSave;
import view.SaveColorSingleShadePNG;

public class TestController implements Controller{


  public static void main(String[] args) {
    FileSave saveMethod = new SaveColorSingleShadePNG(new Color(0, 229, 255));
    TopographyMap map = new StepMap(new TopographyPerlin(), 1920, 1080, 10);
    saveMethod.saveMap(map, "res/test.png");
  }


  @Override
  public void saveMap() {

  }
}
