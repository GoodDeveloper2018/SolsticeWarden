import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cards = new CardLayout();
    private final JPanel deck      = new JPanel(cards);

    private GamePanel gamePanel;
    private final MainMenu menuPanel;

    public MainFrame(){
        setTitle("Solstice Warden");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        menuPanel = new MainMenu(this);
        deck.add(menuPanel,"MENU");

        gamePanel = new GamePanel(this);
        deck.add(gamePanel,"GAME");

        add(deck);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        showMenu();  // start here
    }

    /* panel control */
    public void startNewGame(){
        gamePanel = new GamePanel(this);
        deck.add(gamePanel,"GAME");
        showGame();
    }
    public void showMenu(){ cards.show(deck,"MENU"); menuPanel.requestFocusInWindow(); }
    public void showGame(){ cards.show(deck,"GAME"); gamePanel.requestFocusInWindow(); }
}
