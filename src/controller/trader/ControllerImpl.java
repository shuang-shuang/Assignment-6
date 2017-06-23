package controller.trader;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.trader.Model;
import model.trader.ModelImpl;
import view.trader.View;
import view.trader.ViewImpl;

/**
 * ControllerImpl.
 */
public class ControllerImpl implements Controller {
  private Model model;
  private View view;
  private Readable in;

  public ControllerImpl(Readable in, Appendable out) {
    this.model = new ModelImpl();
    this.view = new ViewImpl(out);
    this.in = in;
  }

  public void go() {
    String option = "?";
    Scanner scan = new Scanner(this.in);

    do {
      // print menu
      view.printMenu();

      // scan option
      option = scan.next();

      // create new basket
      if ("c".equals(option)) {
        createBasket(scan);
      }

      // add stock to an existing basket
      if ("a".equals(option)) {
        updateBasket(scan);
      }

      // print basket
      if ("p".equals(option)) {
        printBasket(scan);
      }

      // get trend
      if ("t".equals(option)) {
        getTrend(scan);
      }

      // open graph
      if ("o".equals(option)) {
        openGraph(scan);
      }
      // FIXME: implement more graphical options

    } while (!"q".equals(option));

    view.print("You quit the program.\n");
    scan.close();
  }

  // create new basket helper
  private void createBasket(Scanner scan) {
    String name;
    int date;
    String outcome;

    try {
      view.print("You chose to create a new basket.\n");
      view.print("Enter basket name:\n"
              + "(Note: if you enter an existing name, this basket will be overwritten.)\n");
      name = scan.next();
      view.print("Enter creation date in the format of YYYYMMDD:\n");
      date = scan.nextInt();

      model.addBasket(name, date);
      outcome = "A new basket called " + name + " has been created on " + date + ".\n";
      view.print(outcome);
    } catch (Exception e) {
      view.printException();
    }
  }

  // add stock to basket helper
  private void updateBasket(Scanner scan) {
    String name;
    String ticker;
    int shares;
    String outcome;

    try {
      view.print("You chose to add shares of stock to an existing basket.\n");
      view.print("Enter basket name:\n");
      name = scan.next();
      view.print("Enter stock ticker:\n");
      ticker = scan.next();
      view.print("Enter number of shares to add:\n");
      shares = scan.nextInt();

      model.updateBasket(name, ticker, shares);
      outcome = shares + " shares of stock " + ticker + " have been added into basket "
              + name + ".\n";
      view.print(outcome);
    } catch (Exception e) {
      view.printException();
    }
  }

  // print basket
  private void printBasket(Scanner scan) {
    String name;

    try {
      view.print("You chose to print the contents and value of a basket.\n");
      view.print("Enter the basket name:\n");
      name = scan.next();

      view.print(model.printBasket(name));
    } catch (Exception e) {
      view.printException();
    }
  }

  // get trend
  private void getTrend(Scanner scan) {
    String name;
    int start;
    int end;
    String outcome;

    try {
      view.print("You asked for the trend of a stock/basket in a specific date range.\n");
      view.print("Enter the stock ticker or basket name:\n");
      name = scan.next();
      view.print("Enter the starting date in the format of YYYYMMDD:\n");
      start = scan.nextInt();
      view.print("Enter the ending date in the format of YYYYMMDD:\n");
      end = scan.nextInt();

      outcome = "The trend of " + name + " is: " + model.getTrend(name, start, end) + ".\n";
      view.print(outcome);
    } catch (Exception e) {
      view.printException();
    }
  }

  // open graph
  private void openGraph(Scanner scan) {
    String graphOption = "?";
    boolean windowIsShowing = true;
    int startingDate;
    int endingDate;
    String outcome;

    view.print("Enter the starting date in the format of YYYYMMDD: \n");
    startingDate = scan.nextInt();
    view.print("Enter the ending date in the format of YYYYMMDD: \n");
    endingDate = scan.nextInt();

    view.openWindow(new ArrayList<>());
    view.print("A graph window is open now.\n");

    do {
      // print graph menu
      view.graphMenu();
      // scan option
      graphOption = scan.next();
      // draw price plot
      if ("d".equals(graphOption)) {
        String name;
        view.print("You chose to draw a price plot.\n");
        view.print("Enter the stock ticker or basket name:\n");
        name = scan.next();

        model.addPlot(name);
        view.openWindow(model.plots(startingDate, endingDate));

        outcome = "A plot of the closing prices of " + name + " from " + startingDate + " to "
                + endingDate + " has been drawn in the graph window.\n";
        view.print(outcome);
      }
      // remove plot
      if ("r".equals(graphOption)) {
        String name;
        view.print("You chose to remove a plot.\n");
        view.print("Enter the stock ticker or basket name:\n");
        name = scan.next();

        model.removePlot(name);
        view.openWindow(model.plots(startingDate, endingDate));

        outcome = "A plot has been removed from the graph.\n";
        view.print(outcome);
      }
      // draw 50MA
      // draw 200MA
      windowIsShowing = view.windowIsShowing();
    } while (windowIsShowing);

    view.print("You have closed the graph window.\n");

  }


}
