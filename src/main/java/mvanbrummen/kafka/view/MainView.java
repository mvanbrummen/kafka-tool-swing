package mvanbrummen.kafka.view;

import mvanbrummen.kafka.components.TopicsPanel;

import javax.swing.*;

public class MainView extends JFrame {

    public MainView() {
        initUI();
    }

    private void initUI() {
        add(new TopicsPanel());

        pack();

        setTitle("KafkaTool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // frame width & height
        int FRAME_WIDTH = 1200;
        int FRAME_HEIGHT = 700;

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
    }

}
