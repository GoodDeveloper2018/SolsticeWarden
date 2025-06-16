import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel(MainFrame root, GamePanel game){
        setLayout(null);
        setPreferredSize(new Dimension(1000,800));

        JButton back = new JButton("Back");
        back.setBounds(30,30,100,30);
        add(back);

        /* background colour picker */
        JLabel bgLbl = new JLabel("Background:");
        bgLbl.setBounds(300,200,100,30);
        add(bgLbl);

        JButton pick = new JButton("Choose");
        pick.setBounds(400,200,100,30);
        add(pick);

        JLabel pixLbl = new JLabel("Pixel size:");
        pixLbl.setBounds(300,250,100,30);
        add(pixLbl);

        JSlider pix = new JSlider(1,20,5);
        pix.setBounds(400,250,200,40);
        add(pix);

        /* listeners */
        pick.addActionListener(e->{
            Color c = JColorChooser.showDialog(this,"Pick background", game.getBackground());
            if(c!=null) game.setBackground(c);
        });
        pix.addChangeListener(e-> game.setPixelSize(pix.getValue()));
        back.addActionListener(e-> root.showMenu());
    }
}