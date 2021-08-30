package mvanbrummen.kafka;


import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.util.SystemInfo;
import mvanbrummen.kafka.view.MainView;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public static void main(String[] args) {
        // on macOS enable screen menu bar
        if (SystemInfo.isMacOS && System.getProperty("apple.laf.useScreenMenuBar") == null)
            System.setProperty("apple.laf.useScreenMenuBar", "true");

        FlatLightLaf.setup();
//        FlatLightLaf.setup(new FlatDarkLaf());

        EventQueue.invokeLater(MainView::new);
    }
}
