package view.trader;

import java.util.List;
import java.util.Map;

/**
 * Created by laura on 6/20/17.
 */
public interface View {

  void printMenu();

  void print(String str);

  void printException();

  void graphMenu();

  void openWindow(List<List<Integer>> prices);

  void drawPrice(List<List<Integer>> plots);

  boolean windowIsShowing();

}
