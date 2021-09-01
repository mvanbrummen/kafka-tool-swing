package mvanbrummen.kafka.controller;

import mvanbrummen.kafka.clients.Kafka;
import mvanbrummen.kafka.view.TopicsPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TopicsController {
    private TopicsPanel topicsPanel;

    public TopicsController(TopicsPanel topicsPanel) {
        this.topicsPanel = topicsPanel;

        var adminClient = Kafka.adminClient();

        adminClient.listTopics().names().whenComplete((t, e) -> {
            List<String> topicNames = new ArrayList<>(t);
            this.topicsPanel.setTopics(topicNames);
            this.topicsPanel.setTopicCount(topicNames.size());
        });

        this.topicsPanel.refreshButton(a ->
                adminClient.listTopics().names().whenComplete((t, e) -> this.topicsPanel.setTopics(new ArrayList<>(t)))
        );

        this.topicsPanel.topicsTable(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var topicsTable = topicsPanel.getTopicsTable();

                var topicName = (String) topicsTable.getValueAt(topicsTable.getSelectedRow(), 0);

                if (!topicsPanel.getDetailsSplitPane().getBottomComponent().isVisible()) {
                    System.out.println("showing details");
                    topicsPanel.showDetailsSplitPane();
                }

            }
        });
    }
}
