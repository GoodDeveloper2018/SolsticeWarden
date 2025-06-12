import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel implements ActionListener {
    private final MainFrame root;
    private final Timer anim = new Timer(30,this);
    private int cloudX = -200;

    public MainMenu(MainFrame root){
        this.root = root;
        setPreferredSize(new Dimension(1000,800));
        setLayout(null);

        JButton newB = new JButton("New Game");
        JButton resB = new JButton("Resume");
        JButton settings = new JButton("Settings");
        JButton quit = new JButton("Quit");

        newB .setBounds(400,300,200,50);
        resB .setBounds(400,370,200,50);
        settings.setBounds(400, 440, 200, 50);
        quit.setBounds(400,510,200,50);

        add(newB); add(resB); add(quit);

        newB.addActionListener(e-> root.startNewGame());
        resB.addActionListener(e-> root.showGame());
        settings.addActionListener(e -> invokeSettings());
        quit.addActionListener(e-> System.exit(0));

        anim.start();
    }

    @Override public void actionPerformed(ActionEvent e){
        cloudX=(cloudX+2)%1200; repaint();
    }

    public void invokeSettings() {

    }

    @Override protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(135,206,235)); g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.YELLOW); g.fillOval(800,50,80,80);
        g.setColor(Color.WHITE);
        g.fillOval(cloudX,100,150,60);
        g.fillOval(cloudX+30,80,120,70);
    }
}
