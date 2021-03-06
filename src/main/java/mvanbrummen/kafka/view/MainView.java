package mvanbrummen.kafka.view;

import mvanbrummen.kafka.controller.BrokersController;
import mvanbrummen.kafka.controller.ClusterController;
import mvanbrummen.kafka.controller.ConsumersController;
import mvanbrummen.kafka.controller.TopicsController;
import mvanbrummen.kafka.view.brokers.BrokersPanel;
import mvanbrummen.kafka.view.consumers.ConsumersPanel;
import mvanbrummen.kafka.view.schemas.SchemaRegistryPanel;
import mvanbrummen.kafka.view.topics.TopicsPanel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        initUI();
    }

    private void initUI() {
        var topicsPanel = new TopicsPanel();
        var clusterPanel = new ClusterPanel();
        var brokersPanel = new BrokersPanel();
        var consumersPanel = new ConsumersPanel();

        var splitPane = new JSplitPane();

        var cards = new CardLayout();
        var cardLayout = new JPanel(cards);

        cardLayout.add("Topics", topicsPanel);
        cardLayout.add("Brokers", brokersPanel);
        cardLayout.add("Consumers", consumersPanel);
        cardLayout.add("Schema Registry", new SchemaRegistryPanel());

        new ClusterController(clusterPanel, cardLayout);
        new BrokersController(brokersPanel);
        new ConsumersController(consumersPanel);

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
