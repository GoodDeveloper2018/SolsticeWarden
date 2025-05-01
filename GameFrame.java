import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Solstice Warden");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel panel = new GamePanel();
        add(panel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
