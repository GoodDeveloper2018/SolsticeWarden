import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GamePanel extends JPanel implements MouseListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private final int GRID_SIZE = 5;

    private ArrayList<Region> regions;
    private SeasonManager seasonManager;
    private GameStateManager gameState;
    private JLabel weatherLabel;
    private JProgressBar satisfactionBar;
    private String currentVillagerRequest = "";

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);
        setBackground(Color.BLACK);
        addMouseListener(this);
        setFocusable(true);

        regions = new ArrayList<>();
        seasonManager = new SeasonManager();
        gameState = new GameStateManager(10); // 10 initial villagers

        initGame();
        initUI();
    }

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private void initGame() {
        int cellSize = 100;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                regions.add(new Region(col * cellSize, row * cellSize + 50, cellSize, cellSize, "Region " + row + "," + col));
            }
        }
    }

    private void initUI() {
        weatherLabel = new JLabel();
        weatherLabel.setBounds(10, 10, 400, 30);
        weatherLabel.setForeground(Color.WHITE);
        add(weatherLabel);

        JButton rainBtn = new JButton("Call Rain");
        rainBtn.setBounds(600, 100, 120, 30);
        rainBtn.addActionListener(e -> manuallyChangeWeather("rain"));
        add(rainBtn);

        JButton sunBtn = new JButton("Increase Sun");
        sunBtn.setBounds(600, 140, 120, 30);
        sunBtn.addActionListener(e -> manuallyChangeWeather("sun"));
        add(sunBtn);

        JButton windBtn = new JButton("Summon Wind");
        windBtn.setBounds(600, 180, 120, 30);
        windBtn.addActionListener(e -> manuallyChangeWeather("wind"));
        add(windBtn);

        satisfactionBar = new JProgressBar(0, 100);
        satisfactionBar.setBounds(600, 30, 300, 20);
        satisfactionBar.setValue(gameState.getAverageHappiness());
        satisfactionBar.setStringPainted(true);
        add(satisfactionBar);
    }

    private void manuallyChangeWeather(String type) {
        switch (type) {
            case "rain" -> seasonManager.forcePrecipitation(true);
            case "sun" -> seasonManager.increaseTemperature();
            case "wind" -> seasonManager.increaseWind();
        }
        repaint();
    }

    private void updateVillagerRequest() {
        String[] messages = {
                "We pray for rain to nourish the fields.",
                "The heat scorches our homes — shield us.",
                "Let the winds carry seeds to our lands.",
                "Balance is all we ask, Warden."
        };
        currentVillagerRequest = messages[new Random().nextInt(messages.length)];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Region r : regions) {
            r.render(g);
        }

        g.setColor(Color.WHITE);
        g.drawString("Season: " + seasonManager.getCurrentSeason() + " | Turn: " + seasonManager.getTurn(), 10, 720);
        g.drawString("Current Villager Request: " + currentVillagerRequest, 10, 740);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Region r : regions) {
            if (r.containsPoint(e.getX(), e.getY())) {
                r.applyWeather(seasonManager);
                gameState.updateVillagers(regions);
                updateVillagerRequest();
                satisfactionBar.setValue(gameState.getAverageHappiness());
                seasonManager.nextTurn();
                repaint();

                if (gameState.isGameOver()) {
                    JOptionPane.showMessageDialog(this, "All villagers have perished. Game Over.");
                    System.exit(0);
                }
                break;
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
