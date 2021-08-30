package mvanbrummen.kafka.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClusterPanel extends JPanel {

    private JButton addClusterButton;

    public ClusterPanel() {
        super(new BorderLayout());

        var toolbar = new JToolBar();

        addClusterButton = new JButton();
        addClusterButton.setToolTipText("Add a new cluster");
        addClusterButton.setIcon(new FlatSVGIcon("icons/plus.svg"));

        toolbar.add(addClusterButton);
        toolbar.addSeparator();

        add(BorderLayout.NORTH, toolbar);
        add(BorderLayout.CENTER, buildTree());
    }

    private JScrollPane buildTree() {
        var clusters = new DefaultMutableTreeNode("Clusters");

        var cluster1 = new DefaultMutableTreeNode("Confluent pkc-e09o6");

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

    public void addClusterButton(ActionListener actionListener) {
        this.addClusterButton.addActionListener(actionListener);
    }
}
