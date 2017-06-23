package util;

import java.util.Map;

/**
 * This interface represents all the operations offered by a component that
 * can be used to get stock data.
 */
public interface StockDataRetriever {
  /**
   * Gets the current price of the given stock.
   *
   * @param stockSymbol the ticker of this stock.
   * @return the current price of this stock.
   */
  double getCurrentPrice(String stockSymbol);

  /**
   * Gets the name of the stock given its ticker.
   *
   * @param stockSymbol the ticker of this stock.
   * @return a string representing the name of the stock.
   */
  String getName(String stockSymbol);

  /**
   * Gets the historical prices of the given stock during the given period.
   *
   * @param stockSymbol the ticker of this stock.
   * @param fromDay    the starting date.
   * @param fromMonth   the starting month.
   * @param fromYear    the starting year.
   * @param toDay      the ending date.
   * @param toMonth     the ending month.
   * @param toYear      the ending year.
   * @return a map structure with dates as keys and PriceRecord as values.
   */
  Map<Integer, PriceRecord> getHistoricalPrices(
          String stockSymbol,
          int fromDay,
          int fromMonth,
          int fromYear,
          int toDay,
          int toMonth,
          int toYear);

}
