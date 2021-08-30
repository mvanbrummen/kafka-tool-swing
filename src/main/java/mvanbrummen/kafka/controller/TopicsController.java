package mvanbrummen.kafka.controller;

import mvanbrummen.kafka.view.ClusterPanel;
import mvanbrummen.kafka.view.TopicsPanel;

import javax.swing.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopicsController {
    private ClusterPanel clusterPanel;
    private TopicsPanel topicsPanel;

    // TODO inject kafka client
    public TopicsController(ClusterPanel clusterPanel, TopicsPanel topicsPanel) {
        this.topicsPanel = topicsPanel;
        this.clusterPanel = clusterPanel;

        // TODO fetch topics
        var topics = List.of(
                "property.foo.foo.foo-topic",
                "property.foo.foo.foo-topic1",
                "property.foo.foo.foo-topic2",
                "property.foo.foo.foo-topic3",
                "property.foo.foo.foo-topic4",
                "property.bar.bar.bar-topic",
                "property.bar.bar.bar-topic2",
                "property.bar.bar.bar-topic3",
                "property.bar.bar.bar-topic4",
                "_confluent.command",
                "_confluent.metrics"
        );

        this.topicsPanel.setTopics(topics);

        this.topicsPanel.refreshButton(e -> topicsPanel.setTopics(
                Stream.of(topics, List.of("foobar.topic"))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
        ));

        // TODO load cluster
        this.clusterPanel.addClusterButton(e -> {
            JOptionPane.showMessageDialog(this.clusterPanel, "Adding new cluster", "New",
                    JOptionPane.INFORMATION_MESSAGE);
        });

    }
}
