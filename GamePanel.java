import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements MouseListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private final int GRID_SIZE = 5;

    private ArrayList<Region> regions;
    private SeasonManager seasonManager;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addMouseListener(this);
        setFocusable(true);

        regions = new ArrayList<>();
        seasonManager = new SeasonManager();

        initGame();
    }

    private void initGame() {
        int cellSize = WIDTH / GRID_SIZE;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                regions.add(new Region(col * cellSize, row * cellSize, cellSize, cellSize, "Region " + row + "," + col));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Region r : regions) {
            r.render(g);
        }

        g.setColor(Color.WHITE);
        g.drawString("Season: " + seasonManager.getCurrentSeason() + " | Turn: " + seasonManager.getTurn(), 10, 20);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Region r : regions) {
            if (r.containsPoint(e.getX(), e.getY())) {
                r.applyWeather(seasonManager);
                seasonManager.nextTurn();
                repaint();
                break;
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
