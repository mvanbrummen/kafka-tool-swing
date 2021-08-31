package mvanbrummen.kafka.controller;

import mvanbrummen.kafka.clients.Kafka;
import mvanbrummen.kafka.view.ConsumersPanel;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.common.Node;

import java.util.ArrayList;
import java.util.List;

public class ConsumersController {
    private ConsumersPanel consumerPanel;

    public ConsumersController(ConsumersPanel consumerPanel) {
        this.consumerPanel = consumerPanel;

        var adminClient = Kafka.adminClient();

        adminClient.listConsumerGroups().all().whenComplete((t, e) -> {
            List<ConsumerGroupListing> consumerGroups = new ArrayList<>(t);

            consumerPanel.setConsumers(consumerGroups);
        });

    }
}
