package model.trader;


import java.util.Map;
import java.util.TreeMap;

import util.PriceRecord;
import util.StockDataRetriever;
import util.WebStockDataRetriever;

/**
 * This class represents the stock.
 */
public class Stock extends AbstractInvestment {
  private String ticker;
  private StockDataRetriever retriever;

  public Stock(String ticker) throws IllegalArgumentException {
    retriever = new WebStockDataRetriever();
    if ("N/A".equals(retriever.getName(ticker))) {
      throw new IllegalArgumentException("No such stock.");
    }
    this.ticker = ticker;
  }

  public String getTicker() {
    return ticker;
  }

  @Override
  public double getOpenPrice(int day, int month, int year) {
    double p = 0;
    // handle null pointer exception.
    try {
      p = getAllPrices(day, month, year).getOpenPrice();
    } catch (Exception e) {
      return -1;
    }
    return p;
  }

  @Override
  public double getClosePrice(int day, int month, int year) {
    double p = 0;
    // handle null pointer exception.
    try {
      p = getAllPrices(day, month, year).getClosePrice();
    } catch (Exception e) {
      return -1;
    }
    return p;
  }

  /**
   * Gets the lowest price of this investment on a given date.
   *
   * @param day  the given day.
   * @param month the given month.
   * @param year  the given year.
   * @return the lowest price as a double.
   */
  public double getLowPrice(int day, int month, int year) {
    double p = 0;
    // handle null pointer exception.
    try {
      p = getAllPrices(day, month, year).getLowestDayPrice();
    } catch (Exception e) {
      return -1;
    }
    return p;
  }

  /**
   * Gets the highest price of this investment on a given date.
   *
   * @param day  the given day.
   * @param month the given month.
   * @param year  the given year.
   * @return the highest price as a double.
   */
  public double getHighPrice(int day, int month, int year) {
    double p = 0;
    // handle null pointer exception.
    try {
      p = getAllPrices(day, month, year).getHighestDayPrice();
    } catch (Exception e) {
      return -1;
    }
    return p;
  }

  @Override
  public Map<Integer, Double> getHistoricalClosePrices(
          int fromDay,
          int fromMonth,
          int fromYear,
          int toDay,
          int toMonth,
          int toYear) {

    Map<Integer, PriceRecord> allPricesMap = retriever.getHistoricalPrices(ticker,
            fromDay, fromMonth, fromYear, toDay, toMonth, toYear);

    Map<Integer, Double> closePricesMap = new TreeMap<>();

    for (Integer date : allPricesMap.keySet()) {
      closePricesMap.put(date, allPricesMap.get(date).getClosePrice());
    }

    return closePricesMap;
  }

  /**
   * Gets the price records of this stock on a given day.
   *
   * @param day  the given date.
   * @param month the given month.
   * @param year  the given year.
   * @return the price record of this stock.
   */
  private PriceRecord getAllPrices(int day, int month, int year) {
    return retriever.getHistoricalPrices(ticker, day, month, year, day, month, year)
            .get(year * 10000 + month * 100 + day);   // pr can be null
  }

}
