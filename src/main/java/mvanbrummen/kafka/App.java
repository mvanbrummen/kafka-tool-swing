package mvanbrummen.kafka;


import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.util.SystemInfo;
import mvanbrummen.kafka.view.MainView;

import javax.swing.plaf.FontUIResource;
import java.awt.*;

import static mvanbrummen.kafka.utills.FontUtils.setFont;

public class App {

    public static void main(String[] args) {
        // on macOS enable screen menu bar
        if (SystemInfo.isMacOS && System.getProperty("apple.laf.useScreenMenuBar") == null)
            System.setProperty("apple.laf.useScreenMenuBar", "true");

        FlatLightLaf.setup();

        setFont(new FontUIResource(new Font("Cabin", Font.PLAIN, 14)));

        EventQueue.invokeLater(MainView::new);
    }


}
