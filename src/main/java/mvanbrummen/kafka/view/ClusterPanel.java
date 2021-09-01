package mvanbrummen.kafka.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class ClusterPanel extends JPanel {

    private JButton addClusterButton;

    private JTree clustersTree;

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

        // TODO for each cluster in config
        clusters.add(buildSubtree("Confluent pcrei3"));

        clustersTree = new JTree(clusters);

        return new JScrollPane(clustersTree);
    }

    private DefaultMutableTreeNode buildSubtree(String clusterName) {
        var subtree = new DefaultMutableTreeNode(clusterName);

        var brokers = new DefaultMutableTreeNode("Brokers");
        var topics = new DefaultMutableTreeNode("Topics");
        var consumers = new DefaultMutableTreeNode("Consumers");
        var schemaRegistry = new DefaultMutableTreeNode("Schema Registry");

        subtree.add(brokers);
        subtree.add(topics);
        subtree.add(consumers);
        subtree.add(schemaRegistry);

        return subtree;
    }

    public JTree getClustersTree() {
        return clustersTree;
    }

    public void addClusterButton(ActionListener actionListener) {
        this.addClusterButton.addActionListener(actionListener);
    }

    public void addClustersTreeListener(MouseAdapter mouseAdapter) {
        clustersTree.addMouseListener(mouseAdapter);
    }
}
