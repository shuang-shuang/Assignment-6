package model.trader;

import java.util.List;

/**
 * Just the common methods of stock and basket.
 */
public interface Model {
  void addBasket(String name, int date);

  void updateBasket(String name, String ticker, int shares) throws Exception;

  String printBasket(String name) throws NullPointerException;

  String getTrend(String name, int start, int end) throws IllegalArgumentException;

  void addPlot(String name);

  void removePlot(String name);

  List<List<Integer>> plots(int startingDate, int endingDate);
}
