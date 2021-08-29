package mvanbrummen.kafka.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class TopicsPanel extends JPanel {

    public TopicsPanel() {
        super(new BorderLayout());
        initUI();
    }

    private void initUI() {
        var splitPane = new JSplitPane();

        var toolbar = new JToolBar();
        var addButton = new JButton();
        addButton.setToolTipText("Add a new cluster");
        addButton.setIcon(new FlatSVGIcon("icons/plus.svg"));
        toolbar.add(addButton);
        toolbar.addSeparator();

        var b = new JPanel(new BorderLayout());

        b.add(BorderLayout.NORTH, toolbar);
        b.add(BorderLayout.CENTER, buildTree());


        splitPane.setLeftComponent(b);
        splitPane.setRightComponent(buildTable());


        add(BorderLayout.CENTER, splitPane);

        setPreferredSize(new Dimension(1000, 600));

    }

    private JScrollPane buildTree() {

        var clusters = new DefaultMutableTreeNode("Clusters");

        var cluster1 = new DefaultMutableTreeNode("pkc-e09o6.australia-southeast1.gcp.confluent.cloud:9092");

        clusters.add(cluster1);

        var brokers = new DefaultMutableTreeNode("Brokers");
        var topics = new DefaultMutableTreeNode("Topics");
        var consumers = new DefaultMutableTreeNode("Consumers");
        var schemaRegistry = new DefaultMutableTreeNode("Schema Registry");

        cluster1.add(brokers);
        cluster1.add(topics);
        cluster1.add(consumers);
        cluster1.add(schemaRegistry);

        var tree = new JTree(clusters);

        return new JScrollPane(tree);
    }

    private JScrollPane buildTable() {

        // Data to be displayed in the JTable
        String[][] data = {
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic1", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic2", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic2", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
                {"property.foo.foo.foo-topic", "1", "0", "n/a", "34"},
        };

        // Column Names
        String[] columnNames = {"Name", "Partitions", "Count", "Size", "Consumers"};


        var table = new JTable(data, columnNames);

        var sp = new JScrollPane(table);

        return sp;
    }

}
