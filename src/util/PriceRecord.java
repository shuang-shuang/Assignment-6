package util;

/**
 * This class represents the record of a price of a single stock item in a day.
 */
public class PriceRecord {
  private final double open;
  private final double close;
  private final double highest;
  private final double lowest;

  /**
   * Constructs a price record with the given open, close, lowest and highest prices.
   *
   * @param open    open price.
   * @param close   close price.
   * @param lowest  lowest price.
   * @param highest highest price.
   * @throws IllegalArgumentException if the given prices are not positive, or highest price is
   *                                  lower than any of the other three price, or lowest price is
   *                                  higher than any of the other three price.
   */
  public PriceRecord(double open, double close, double lowest, double highest)
          throws IllegalArgumentException {
    if (open <= 0 || close <= 0 || lowest <= 0 || highest <= 0) {
      throw new IllegalArgumentException("Prices cannot be zero or negative.");
    }
    if (lowest > open || lowest > close || lowest > highest) {
      throw new IllegalArgumentException("Lowest price cannot be higher than the other three.");
    }
    if (highest < open || highest < close || highest < lowest) {
      throw new IllegalArgumentException("Highest price cannot be lower than the other three");
    }

    this.open = open;
    this.close = close;
    this.highest = highest;
    this.lowest = lowest;
  }

  /**
   * Gets the open price of the price record.
   *
   * @return the open price of the price record.
   */
  public double getOpenPrice() {
    return open;
  }

  /**
   * Gets the close price of the price record.
   *
   * @return the close price of the price record.
   */
  public double getClosePrice() {
    return close;
  }

  /**
   * Gets the lowest price of the price record.
   *
   * @return the lowest price of the price record.
   */
  public double getLowestDayPrice() {
    return lowest;
  }

  /**
   * Gets the highest price of the price record.
   *
   * @return the highest price of the price record.
   */
  public double getHighestDayPrice() {
    return highest;
  }
}

