package model.trader;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents a basket of stocks.
 */
public class Basket extends AbstractInvestment {
  private String name;
  private int creationDate;
  private Map<Stock, Integer> stocks;


  public Basket(String name, int creationDate) {
    this.name = name;
    this.creationDate = creationDate;
    this.stocks = new TreeMap<>(Comparator.comparing(Stock::getTicker));
  }


  public String getName() {
    return name;
  }


  public int getCreationDate() {
    return creationDate;
  }


  public void addStock(String ticker, int shares) throws IllegalArgumentException {
    if (shares < 0) {
      throw new IllegalArgumentException("Shares cannot be negative.");
    }

    Stock s = new Stock(ticker);     // also throws IllegalArgumentException

    if (stocks.containsKey(s)) {
      stocks.replace(s, stocks.get(s) + shares);
    } else {
      stocks.put(s, shares);
    }
  }


  @Override
  public double getOpenPrice(int day, int month, int year) {
    double open = 0;

    for (Stock s : stocks.keySet()) {
      open += s.getOpenPrice(day, month, year) * stocks.get(s);
    }

    return open;
  }


  @Override
  public double getClosePrice(int day, int month, int year) {
    double close = 0;

    for (Stock s : stocks.keySet()) {
      close += s.getClosePrice(day, month, year) * stocks.get(s);
    }

    return close;
  }


  @Override
  public Map<Integer, Double> getHistoricalClosePrices(
          int fromDate,
          int fromMonth,
          int fromYear,
          int toDate,
          int toMonth,
          int toYear) {

    Map<Integer, Double> closePricesMap = new TreeMap<>();

    Map<Integer, Double> stockPrices = new Stock("AAPL")
            .getHistoricalClosePrices(fromDate, fromMonth, fromYear, toDate, toMonth, toYear);

    for (Integer d : stockPrices.keySet()) {
      closePricesMap.put(d, getClosePrice(d % 100, d / 100 % 100, d / 10000));
    }

    return closePricesMap;
  }


  /**
   * Converts the Basket to string in the format of [stockSymbol  n shares] per line.
   *
   * @return the string representation of this basket.
   */
  @Override
  public String toString() {
    String str = "Basket name: " + name + "\t\t\t"
            + "Creation date[YYYYMMDD]: " + creationDate + "\n";
    for (Stock stock : stocks.keySet()) {
      str = str + "Stock ticker: " + stock.getTicker() + "\t\t"
              + "Number of shares: " + stocks.get(stock) + "\n";
    }
    return str;
  }
}
