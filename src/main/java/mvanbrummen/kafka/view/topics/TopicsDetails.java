package mvanbrummen.kafka.view.topics;

import mvanbrummen.kafka.models.DataTableModel;

import javax.swing.*;
import java.awt.*;

public class TopicsDetails extends JTabbedPane {

    private JLabel topicConfiguration;

    public TopicsDetails() {
        initUI();
    }

    private void initUI() {
        var b = new JPanel(new BorderLayout());
        var dataTable = new JScrollPane(new JTable(new DataTableModel()));

        var t = new JToolBar();
        t.add(new JButton("Play"));
        t.add(new JButton("Pause"));

        b.add(BorderLayout.NORTH, t);
        b.add(BorderLayout.CENTER, dataTable);

        topicConfiguration = new JLabel("Configuration");

        add("Data", b);
        add("Partitions", new JLabel("partitions"));
        add("Consumers", new JLabel("consumers"));
        add("Configuration", topicConfiguration);
    }

    public void setTopicConfiguration(String text) {
        this.topicConfiguration.setText(text);
    }
}
