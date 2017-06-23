package model.trader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * contains methods of model.
 */
public class ModelImpl implements Model {
  private Map<String, Investment> inv;
  private List<String> drawn;

  public ModelImpl() {
    this.inv = new TreeMap<>();
    this.drawn = new ArrayList<>();
  }

  public void addBasket(String name, int date) {
    inv.put(name, new Basket(name, date));
    // if basket already exists, will overwrite it
  }

  public void updateBasket(String name, String ticker, int shares) throws Exception {
    Basket b = (Basket) inv.get(name);     // null pointer exception
    b.addStock(ticker, shares);            // illegal argument exception
  }

  public String printBasket(String name) throws NullPointerException {
    Basket b = (Basket) inv.get(name);     // null pointer exception
    return b.toString() + "Basket value as of June 20 2017: "
            + b.getClosePrice(20, 6, 2017) + "\n";
  }

  private Investment find(String name) throws IllegalArgumentException {
    Investment investment;
    if (inv.containsKey(name)) {
      investment = inv.get(name);
    } else {
      investment = new Stock(name);     // illegal argument exception
    }
    return investment;
  }

  public String getTrend(String name, int startingDate, int endingDate)
          throws IllegalArgumentException {
    Investment investment = find(name);
    return investment.trending(startingDate % 100, startingDate / 100 % 100,
            startingDate / 10000, endingDate % 100,
            endingDate / 100 % 100, endingDate / 10000);
  }

  private List<Integer> getPrices(String name, int startingDate, int endingDate) {
    Investment investment = find(name);
    List<Double> hist = new ArrayList<>(investment.getHistoricalClosePrices(
            startingDate % 100, startingDate / 100 % 100,
            startingDate / 10000, endingDate % 100,
            endingDate / 100 % 100, endingDate / 10000).values());

    List<Integer> prices = new ArrayList<>();
    for (Double e : hist) {
      prices.add(e.intValue());
    }
    return prices;
  }

  public void addPlot(String name) {
    drawn.add(name);
  }

  public void removePlot(String name) {
    drawn.remove(name);
  }

  public List<List<Integer>> plots(int startingDate, int endingDate) {
    List<List<Integer>> plots = new ArrayList<>();

    for (String name : drawn) {
      plots.add(getPrices(name, startingDate, endingDate));
    }

    return plots;
  }

}
