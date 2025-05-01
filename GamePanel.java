import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements MouseListener, KeyListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private ArrayList<GameObject> gameObjects;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        gameObjects = new ArrayList<>();
        initGame();
    }

    private void initGame() {
        // Example: Add one region just to test
        gameObjects.add(new Region(100, 100, 100, 100, "Forest"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (GameObject obj : gameObjects) {
            obj.render(g);
        }
    }

    // Input handlers
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
