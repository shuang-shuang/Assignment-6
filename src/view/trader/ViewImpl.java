package view.trader;

import java.util.List;

/**
 * Created by laura on 6/20/17.
 */
public class ViewImpl implements View {
  private TextView text;
  private GraphicalView graphical;

  public ViewImpl(Appendable out) {
    this.text = new TextView(out);
  }

  public void printMenu() {
    text.printMenu();
  }

  public void print(String str) {
    text.print(str);
  }

  public void printException() {
    text.printException();
  }

  public void graphMenu() {
    text.graphMenu();
  }

  public void openWindow(List<List<Integer>> prices) {
    graphical = new GraphicalView(prices);
  }

  public void drawPrice(List<List<Integer>> plots) {
    graphical.update(plots);
  }

  public boolean windowIsShowing() {
    return graphical.windowIsShowing();
  }
}
