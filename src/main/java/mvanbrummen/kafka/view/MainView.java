package mvanbrummen.kafka.view;

import mvanbrummen.kafka.controller.TopicsController;

import javax.swing.*;

public class MainView extends JFrame {

    public MainView() {
        initUI();
    }

    private void initUI() {
        var topicsPanel = new TopicsPanel();

        add(topicsPanel);

        new TopicsController(topicsPanel);

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
