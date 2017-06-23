package model.trader;

import java.util.Map;

/**
 * This is an interface representing an investment.
 */
public interface Investment {

  /**
   * Gets the open price of this investment on a given date.
   *
   * @param date  the given day.
   * @param month the given month.
   * @param year  the given year.
   * @return the open price as a double.
   */
  double getOpenPrice(int date, int month, int year);


  /**
   * Gets the close price of this investment on a given date.
   *
   * @param date  the given day.
   * @param month the given month.
   * @param year  the given year.
   * @return the close price as a double.
   */
  double getClosePrice(int date, int month, int year);


  /**
   * Gets historical close prices of this stock during the given period.
   * Non-business days during this period are omitted.
   * If no day is in this period, return an empty map.
   *
   * @param fromDate  the starting date.
   * @param fromMonth the starting month.
   * @param fromYear  the starting year.
   * @param toDate    the ending date.
   * @param toMonth   the ending month.
   * @param toYear    the ending year.
   * @return a map where dates are keys and close prices are values.
   */
  Map<Integer, Double> getHistoricalClosePrices(
          int fromDate,
          int fromMonth,
          int fromYear,
          int toDate,
          int toMonth,
          int toYear);


  /**
   * Converts the given historical price map into a string in the format of [date closePrice] per
   * line. Only for test use.
   *
   * @param historicalPrices the map of dates as keys and historical close prices as values.
   * @return the string representation of the given map.
   */
  String historicalPricesToString(Map<Integer, Double> historicalPrices);


  /**
   * Computes the moving average of the given days, given the historical close price map.
   *
   * @param numDays        the number of days to compute moving average.
   * @param closePricesMap the map with dates as keys and historical close prices as values.
   * @return the moving average of the given days on this stock as a double.
   */
  double movingAverage(int numDays, Map<Integer, Double> closePricesMap);


  /**
   * Determines whether the given day is a business day.
   *
   * @param date  the given date.
   * @param month the given month.
   * @param year  the given year.
   * @return true if the given day is a business day, and false otherwise.
   */
  boolean isBusinessDay(int date, int month, int year);


  /**
   * Determines whether the given day is a buying opportunity day.
   * A buying opportunity day is the day when the 50-day moving average is equal to or above
   * the 200-day moving average of this stock.
   *
   * @param date  the given day.
   * @param month the given month.
   * @param year  the given year.
   * @return true if this date is a buying opportunity day, and false otherwise.
   */
  boolean isBuyingOpportunityDay(int date, int month, int year);


  /**
   * Determine the trend of this investment during a given period.
   * Trend is determined by linking the close price on the first day and on the last day.
   * If the input starting date or ending date is not business day, throw an exception.
   *
   * @param fromDate  the starting date.
   * @param fromMonth the starting month.
   * @param fromYear  the starting year.
   * @param toDate    the ending date.
   * @param toMonth   the ending month.
   * @param toYear    the ending year.
   * @return the trend as an enum.
   */
  String trending(int fromDate, int fromMonth, int fromYear,
                  int toDate, int toMonth, int toYear);

}
