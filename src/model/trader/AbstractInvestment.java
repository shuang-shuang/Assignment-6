package model.trader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is an abstract class implementing an investment.
 */
public abstract class AbstractInvestment implements Investment {

  @Override
  public String historicalPricesToString(Map<Integer, Double> historicalPrices) {
    String str = "";

    for (Integer date : historicalPrices.keySet()) {
      str = str + date + " " + historicalPrices.get(date) + "\n";
    }

    return str.trim();
  }

  @Override
  public double movingAverage(int numDays, Map<Integer, Double> closePricesMap) {
    List<Integer> dates = new ArrayList<>(closePricesMap.keySet());
    double sum = 0;

    for (int i = dates.size() - 1; i >= dates.size() - numDays; --i) {
      sum += closePricesMap.get(dates.get(i));
    }

    return sum / numDays;
  }

  @Override
  public boolean isBusinessDay(int day, int month, int year) {
    Map<Integer, Double> closePricesMap = getHistoricalClosePrices(day, month, year,
            day, month, year);

    return closePricesMap.containsKey(year * 10000 + month * 100 + day);
  }

  @Override
  public boolean isBuyingOpportunityDay(int day, int month, int year) {
    if (!isBusinessDay(day, month, year)) {
      return false;
    }

    Map<Integer, Double> map = getHistoricalClosePrices(day, month,
            year - 1, day, month, year);

    return movingAverage(50, map) >= movingAverage(200, map);
  }

  @Override
  public String trending(int fromDay, int fromMonth, int fromYear,
                         int toDay, int toMonth, int toYear) {

    List<Double> prices = new ArrayList<>(getHistoricalClosePrices(fromDay,
            fromMonth, fromYear, toDay, toMonth, toYear).values());

    if (prices.isEmpty()) {
      return "Invalid dates";
    }

    double fromClosePrice = prices.get(0);
    double toClosePrice = prices.get(prices.size() - 1);

    if (fromClosePrice > toClosePrice) {
      return "DECLINE";
    }
    if (fromClosePrice < toClosePrice) {
      return "INCLINE";
    }
    return "FLAT";
  }

}
