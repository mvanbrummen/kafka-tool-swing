package mvanbrummen.kafka.view;

import mvanbrummen.kafka.controller.TopicsController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        initUI();
    }

    private void initUI() {
        var topicsPanel = new TopicsPanel();
        var clusterPanel = new ClusterPanel();

        var splitPane = new JSplitPane();

        splitPane.setLeftComponent(clusterPanel);
        splitPane.setRightComponent(topicsPanel);

        add(BorderLayout.CENTER, splitPane);

        var bottomToolbar = new JToolBar();
        bottomToolbar.add(new JLabel("v0.0.1"));
        add(BorderLayout.SOUTH, bottomToolbar);

        add(splitPane);

        new TopicsController(clusterPanel, topicsPanel);

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
