package mvanbrummen.kafka.controller;

import mvanbrummen.kafka.clients.Kafka;
import mvanbrummen.kafka.view.BrokersPanel;
import org.apache.kafka.common.Node;

import java.util.ArrayList;
import java.util.List;

public class BrokersController {
    private BrokersPanel brokersPanel;

    public BrokersController(BrokersPanel brokersPanel) {
        this.brokersPanel = brokersPanel;

        var adminClient = Kafka.adminClient();

        adminClient.describeCluster().nodes().whenComplete((t, e) -> {
            List<Node> brokerNodes = new ArrayList<>(t);

            brokersPanel.setBrokers(brokerNodes);
        });

    }
}
