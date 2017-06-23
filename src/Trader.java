import java.io.InputStreamReader;

import controller.trader.ControllerImpl;

/**
 * This class contains the main method that drives the trader program.
 */
public class Trader {

  public static void main(String[] args) {
    try {
      new ControllerImpl(new InputStreamReader(System.in), System.out).go();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


/*
Questions:
1. View interface but only need to use TextView? Options of graphical view are chosen in TextView?
   must be interface?
2. Add shares of stock to a basket that is presented in graph, refresh graph?
3. Plot two stocks in different date ranges?
4. In controller, to print, use this.out.append or view.printText?
5. If not specified, then not required or design up to us?
   e.g. create a basket with existing name, overwrite it or not allow?
6. How to handle exceptions? Must? throw is enough? should not stop
7. util package where?
8. value of basket
9. plot or line
10. store data
11. try catch in try catch
 */


// FIXME: everything need to append and test.