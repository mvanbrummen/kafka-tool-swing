package mvanbrummen.kafka.controller;

import mvanbrummen.kafka.clients.Kafka;
import mvanbrummen.kafka.view.TopicsPanel;

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
    }
}
