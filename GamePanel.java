import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel
        implements MouseListener, KeyListener {
    private final MainFrame root;
    public static final int WIDTH  = 1000;
    public static final int HEIGHT = 800;
    private static final int GRID_SIZE = 5;

    private final ArrayList<Region> regions = new ArrayList<>();
    private final SeasonManager seasonManager = new SeasonManager();
    private final GameStateManager gameState;

    private final JProgressBar   satisfactionBar = new JProgressBar(0,100);
    private final JLabel         villagerLabel   = new JLabel();
    private String currentRequest = "";

    public GamePanel(MainFrame root){
        this.root = root;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setLayout(null);
        setBackground(Color.BLACK);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);

        buildRegions();
        gameState = new GameStateManager(regions,30);

        buildUI();
        updateRequest();
    }

    private void buildRegions(){
        int size = 100;
        for(int r=0;r<GRID_SIZE;r++)
            for(int c=0;c<GRID_SIZE;c++)
                regions.add(new Region(c*size, r*size+50, size,size,
                        "R"+r+","+c));
    }

    private void buildUI(){
        JButton rain  = makeButton("Call Rain" , 600,100, e->seasonManager.forcePrecipitation(true));
        JButton sun   = makeButton("Increase Sun",600,140, e->seasonManager.increaseTemperature());
        JButton wind  = makeButton("Summon Wind",600,180, e->seasonManager.increaseWind());
        add(rain); add(sun); add(wind);
        JButton tempChange = makeButton("Change Temperature", 600, 220, e -> seasonManager.getTemperature());

        satisfactionBar.setBounds(600,30,300,20);
        satisfactionBar.setStringPainted(true);
        add(satisfactionBar);

        villagerLabel.setBounds(600,60,300,20);
        villagerLabel.setForeground(Color.WHITE);
        add(villagerLabel);
    }
    private JButton makeButton(String txt,int x,int y,ActionListener al){
        JButton b=new JButton(txt); b.setBounds(x,y,120,30); b.addActionListener(al); return b;
    }

    private void updateRequest(){
        String[] msgs={
                "We pray for rain to nourish the fields.",
                "The heat scorches our homes — shield us.",
                "Let the winds carry seeds to our lands.",
                "Balance is all we ask, Warden."
        };
        currentRequest = msgs[new Random().nextInt(msgs.length)];
    }

    private void advanceTurn(){
        seasonManager.nextTurn();
        gameState.nextTurn(seasonManager,regions);
        satisfactionBar.setValue(gameState.avgApp());
        villagerLabel.setText("Villagers alive: "+gameState.living());
        updateRequest();
        repaint();

        if(gameState.gameOver()){
            JOptionPane.showMessageDialog(this,"All villagers have perished.");
            root.showMenu();
        }
    }
    private void drawVillagers(Graphics g){
        for(Village v: gameState.getVillages()) v.render(g);
    }

    @Override protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Region r:regions) r.render(g);
        drawVillagers(g);

        g.setColor(Color.WHITE);
        g.drawString("Season: "+seasonManager.getCurrentSeason()
                +" | Turn: "+seasonManager.getTurn(), 10,720);
        g.drawString("Current Villager Request: "+currentRequest,10,740);
    }
    @Override public void mouseClicked(MouseEvent e){
        regions.stream().filter(r->r.containsPoint(e.getX(),e.getY()))
                .findFirst().ifPresent(r->{
                    r.applyWeather(seasonManager);
                    advanceTurn();
                });
    }
    @Override public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_M) root.showMenu();
    }
    @Override public void keyReleased(KeyEvent e){} @Override public void keyTyped(KeyEvent e){}
    @Override public void mousePressed(MouseEvent e){} @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){} @Override public void mouseExited(MouseEvent e){}
}
