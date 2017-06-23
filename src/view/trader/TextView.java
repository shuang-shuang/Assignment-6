package view.trader;

import java.util.Map;

import model.trader.Basket;

/**
 * Text view.
 */
public class TextView {
  private Appendable out;

  public TextView(Appendable out) {
    this.out = out;
  }

  public void printMenu() {
    try {
      this.out.append("\nMenu: (Example: enter c to create a new basket.)\n");
      this.out.append("c - create a new basket.\n");
      this.out.append("a - add shares of stocks to an existing basket.\n");
      this.out.append("p - print the contents and value of an existing basket.\n");
      this.out.append("t - get the trend of a particular stock within a specific date range.\n");
      this.out.append("o - open a window and create a blank graph.\n");
      this.out.append("q - quit the program.\n\n");

    /*
    System.out.println("g - open a window and create a blank graph.");
    System.out.println("d - draw the plot of the closing prices of a stock or basket.");
    System.out.println("r - remove an existing plot of a stock or basket.");
    System.out.println("ma50 - plot 50-day moving average of a stock or basket with its closing prices.");
    System.out.println("ma200 - plot 200-day moving average of a stock or basket with its closing prices.\n");
    */
      this.out.append("Please make an option:\n");
    } catch (Exception e) {
      return;
    }
  }

  public void print(String str) {
    try {
      this.out.append(str);
    } catch (Exception e) {
      return;
    }
  }

  public void printException() {
    try {
      this.out.append("Oops! Something is wrong. Try again.\n");
    } catch (Exception e) {
      return;
    }
  }

  public void graphMenu() {
    try {
      this.out.append("\nGraph menu: (Example: enter d to draw a plot on the graph window.)\n");
      this.out.append("d - draw the plot of the closing prices of a stock or basket "
              + "in the specified date range.\n");
      this.out.append("r - remove an existing plot of a stock or basket.\n");
      this.out.append("ma50 - plot 50-day moving average of a stock or basket "
              + "with its closing prices.\n");
      this.out.append("ma200 - plot 200-day moving average of a stock or basket "
              + "with its closing prices.\n");
      this.out.append("Please make an option: "
              + "(if you have closed the graph window, press any key to return the home menu.)\n");
    } catch (Exception e) {
      return;
    }
  }

}
