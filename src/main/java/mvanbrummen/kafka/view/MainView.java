package mvanbrummen.kafka.view;

import mvanbrummen.kafka.controller.ClusterController;
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

        var cards = new CardLayout();
        var cardLayout = new JPanel(cards);

        cardLayout.add("Topics", topicsPanel);
        cardLayout.add("Brokers", new BrokersPanel());
        cardLayout.add("Consumers", new ConsumersPanel());
        cardLayout.add("Schema Registry", new SchemaRegistryPanel());

        new ClusterController(clusterPanel, cardLayout);

        splitPane.setLeftComponent(clusterPanel);
        splitPane.setRightComponent(cardLayout);

        add(BorderLayout.CENTER, splitPane);

        var bottomToolbar = new JToolBar();
        bottomToolbar.add(new JLabel("v0.0.1"));
        add(BorderLayout.SOUTH, bottomToolbar);

        add(splitPane);

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
