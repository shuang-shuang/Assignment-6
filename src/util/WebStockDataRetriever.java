package util;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This class represents a stock retriever module. It is a singleton, and so to
 * get the one (and only) object call getStockDataRetriever().
 */
public class WebStockDataRetriever implements StockDataRetriever {

  /**
   * Constructs a WebStockDataRetriever.
   */
  public WebStockDataRetriever() {
    // do nothing.
  }

  @Override
  public double getCurrentPrice(String stockSymbol) {
    String output = "";

    try {
      URL url = new URL("https://download.finance.yahoo.com/d/quotes.csv?s=" + stockSymbol
              + "&f=l1&e=.csv");
      // if no such stock, will get N/A.
      output = new Scanner(url.openStream()).next();
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }

    return Double.parseDouble(output);
  }

  @Override
  public String getName(String stockSymbol) {
    String output = "";

    try {
      URL url = new URL("https://download.finance.yahoo.com/d/quotes.csv?s=" + stockSymbol
              + "&f=n&e=.csv");
      // if no such stock, will get N/A.
      output = new Scanner(url.openStream()).next();
    } catch (Exception e) {
      e.printStackTrace();
      return "N/A";
    }

    return output;
  }

  @Override
  public Map<Integer, PriceRecord> getHistoricalPrices(
          String stockSymbol,
          int fromDay,
          int fromMonth,
          int fromYear,
          int toDay,
          int toMonth,
          int toYear) {

    Map<Integer, PriceRecord> prices = new TreeMap<>();

    if (isInvalidDate(fromDay, fromMonth, fromYear, toDay, toMonth, toYear)) {
      return prices;
    }

    String str = "https://www.google.com/finance/historical?output=csv&q=" + stockSymbol
            + "&startdate=" + fromMonth + "+" + fromDay + "+" + fromYear + "&enddate="
            + toMonth + "+" + toDay + "+" + toYear;

    try {
      URL url = new URL(str);
      Scanner sc = new Scanner(url.openStream());
      String output = sc.next();  // get labels

      while (sc.hasNext()) {
        output = sc.next();

        String[] data = output.split(",");

        PriceRecord record = new PriceRecord(
                Double.parseDouble(data[1]),
                Double.parseDouble(data[4]),
                Double.parseDouble(data[3]),
                Double.parseDouble(data[2])
        );

        Integer date = getDate(data[0]);
        prices.put(date, record);
      }
    } catch (Exception e) {
      System.out.println("Error from web when opening the URL.");
    }
    // https://www.google.com/finance/historical?output=csv&q=F&startdate=1+1+2017&enddate=1+1+2017
    // if no such stock, will get 404 error. if no business day, will have only labels.

    return prices;
  }


  /**
   * Converts a String representation of month into integer.
   *
   * @param month the string representation of a month, in the format of the first three letters.
   * @return an integer representation of a month.
   */
  private int toMonth(String month) {
    switch (month) {
      case "Jan":
        return 1;
      case "Feb":
        return 2;
      case "Mar":
        return 3;
      case "Apr":
        return 4;
      case "May":
        return 5;
      case "Jun":
        return 6;
      case "Jul":
        return 7;
      case "Aug":
        return 8;
      case "Sep":
        return 9;
      case "Oct":
        return 10;
      case "Nov":
        return 11;
      case "Dec":
        return 12;
      default:
        return -1;
    }
  }

  /**
   * Gets the Integer of date in the format of [YYYYMMDD], given a string representation of date.
   *
   * @param date the string representation of date.
   * @return an Integer representation of date in the format of [YYYYMMDD].
   */
  private Integer getDate(String date) {
    String[] splitdate = date.split("-");
    int actualDate = Integer.parseInt(splitdate[0]);
    int actualYear = Integer.parseInt(splitdate[2]);
    int actualMonth = toMonth(splitdate[1]);
    if (actualYear <= LocalDate.now().getYear() % 100) {
      actualYear = LocalDate.now().getYear() / 100 * 100 + actualYear;
    } else {
      actualYear = (LocalDate.now().getYear() / 100 - 1) * 100 + actualYear;
    }
    return (actualYear * 100 + actualMonth) * 100 + actualDate;
  }

  /**
   * Determines whether a date is a valid date for google finance.
   * Below are our observations on the reactions of google finance.
   * 1. Below are when google finance will act strangely and output data of a year from now:
   * any negative input, a month more than 12, a date more than 31, a day when this stock was
   * not public yet such as year 1500, or when a day has not come yet such as year 2020.
   * 2. It assumes every month has 31 days. If you try Feb 31, it will give the result of March 3.
   * We regard this as expected and correct as google.
   * The problem is:
   * If the month or date is negative or larger than 99, then the price retrieved will
   * coincidentally be another valid day's price but not as we expect.
   * An example is: date = 3, month = -90, year = 2017. After converting it to date Integer,
   * it will be 20171003. The retrieved price will then be the price on Oct 3, 2017.
   * This is not what we want. So this method is designed to filter out such inputs.
   *
   * @return true if the date is a valid date for google finance, and false otherwise.
   */
  private boolean isInvalidDate(int fromDay, int fromMonth, int fromYear,
                                int toDay, int toMonth, int toYear) {
    int from = fromDay + fromMonth * 100 + fromYear * 10000;
    int to = toDay + toMonth * 100 + toYear * 10000;
    int today = LocalDate.now().getYear() * 10000 + LocalDate.now().getMonthValue() * 100
            + LocalDate.now().getDayOfMonth();

    return (fromDay < 0 || fromMonth < 0 || fromDay > 31 || fromMonth > 12
            || toDay < 0 || toMonth < 0 || toDay > 31 || toMonth > 12
            || from > to || to > today);
  }
}
