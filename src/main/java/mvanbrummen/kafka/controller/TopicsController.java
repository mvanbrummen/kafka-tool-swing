package mvanbrummen.kafka.controller;

import mvanbrummen.kafka.clients.Kafka;
import mvanbrummen.kafka.view.topics.TopicsPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

        this.topicsPanel.getTopicsToolbar().showInternalTopicsButton(e -> {
            if (this.topicsPanel.getTopicsToolbar().getShowInternalTopicsButton().isSelected()) {
                topicsPanel.getTopicsTableSorter().setRowFilter(null);
            } else {
                topicsPanel.getTopicsTableSorter().setRowFilter(RowFilter.notFilter(RowFilter.regexFilter("^_", 0)));
            }
        });


        this.topicsPanel.getTopicsToolbar().getTopicSearchTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Event in search field");
                var text = topicsPanel.getTopicsToolbar().getTopicSearchTextField().getText();
                if (text.trim().length() == 0) {
                    if (topicsPanel.getTopicsToolbar().getShowInternalTopicsButton().isSelected()) {
                        topicsPanel.getTopicsTableSorter().setRowFilter(null);
                    } else {
                        topicsPanel.getTopicsTableSorter().setRowFilter(RowFilter.notFilter(RowFilter.regexFilter("^_", 0)));
                    }
                } else {
                    if (!topicsPanel.getTopicsToolbar().getShowInternalTopicsButton().isSelected()) {
                        topicsPanel.getTopicsTableSorter().setRowFilter(RowFilter.andFilter(
                                        Set.of(
                                                RowFilter.notFilter(RowFilter.regexFilter("^_", 0)),
                                                RowFilter.regexFilter("(?i)" + text)
                                        )
                                )
                        );

                    } else {
                        topicsPanel.getTopicsTableSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }
            }
        });

        this.topicsPanel.getTopicsToolbar().refreshButton(a ->
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

                adminClient.describeTopics(List.of(topicName))
                        .all()
                        .whenComplete(
                                (t, ee) -> {
                                    System.out.println("topic is " + t);

                                    topicsPanel.getTopicsDetails().setTopicConfiguration(t.get(topicName).partitions().get(0).leader().host());

                                }
                        );


            }
        });
    }
}
