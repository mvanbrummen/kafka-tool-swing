package mvanbrummen.kafka;


import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import mvanbrummen.kafka.components.TopicsPanel;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App() {
        initUI();
    }

    private void initUI() {
        add(new TopicsPanel());

        pack();

        setTitle("KafkaTool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
//        FlatLightLaf.setup(new FlatDarkLaf());

        EventQueue.invokeLater(() -> {
            var ex = new App();
            ex.setVisible(true);
        });
    }
}
