package util;  // FIXME

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class shows an example of creating a GUI using Java Swing. Feel free
 * to use and edit this code to suit your purpose
 *
 * It also breaks the tradition of putting main in a separate class by
 * putting main in this class itself, for brevity.
 */
public class DrawLine extends JFrame {
  private DrawPanel panel;

  public DrawLine(List<List<Integer>> prices) {
    super();
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    panel = new DrawPanel(prices);
    this.add(panel);
    this.pack();
    this.setVisible(true);
  }

  public void updatePanel(List<List<Integer>> prices) {
    this.add(new DrawPanel(prices));
    repaint();
  }
/*
  public static void main(String[] args) {
    DrawLine mainWindow = new DrawLine();
    List<List<Integer>> b = new ArrayList<>();
    List<Integer> a1 = new ArrayList<>();
    a1.add(500);
    a1.add(200);
    a1.add(500);
    List<Integer> a2 = new ArrayList<>();
    a2.add(30);
    a2.add(20);
    a2.add(30);
    b.add(a1);
    b.add(a2);

    mainWindow.updatePanel(b);

  }
  */
}


/////////////////////////////////////////////////////////////////////////////////////

class DrawPanel extends JPanel {
  private List<List<Integer>> prices;

  public DrawPanel(List<List<Integer>> prices) {
    super();
    this.setBackground(Color.WHITE);
    this.prices = prices;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(600, 600);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);

    for (int i = 0; i < prices.size(); ++i) {
      g2d.setColor(setColor(i));
      for (int j = 0; j < prices.get(0).size() - 1; ++j) {
        g2d.drawLine(j * 10, prices.get(i).get(j),
                (j + 1) * 10, prices.get(i).get(j + 1));
      }
    }


    //write some text
    // g2d.setColor(Color.BLACK);
    // g2d.drawString("Here is the string", 400, 400);

  }

  private Color setColor(int i) {
    List<Color> colors = new ArrayList<>();
    colors.add(Color.BLUE);
    colors.add(Color.RED);
    colors.add(Color.YELLOW);
    colors.add(Color.GRAY);
    colors.add(Color.GREEN);
    colors.add(Color.PINK);
    colors.add(Color.ORANGE);
    colors.add(Color.MAGENTA);
    return colors.get(i % 8);
  }
}