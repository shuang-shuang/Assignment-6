package view.trader;

import java.util.List;
import java.util.Map;

import javax.swing.*;

import util.DrawLine;

/**
 * to draw all kinds of graphs.
 */
public class GraphicalView {
  private DrawLine drawLine;

  public GraphicalView(List<List<Integer>> prices) {
    drawLine = new DrawLine(prices);
  }

  public void update(List<List<Integer>> plots) {
    drawLine.updatePanel(plots);
  }

  public boolean windowIsShowing() {
    return drawLine.isShowing();
  }
}
