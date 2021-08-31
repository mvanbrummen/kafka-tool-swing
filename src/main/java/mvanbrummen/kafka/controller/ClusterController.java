package mvanbrummen.kafka.controller;

import mvanbrummen.kafka.view.ClusterPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClusterController {
    private ClusterPanel clusterPanel;
    private JPanel cardLayout;

    public ClusterController(ClusterPanel clusterPanel, JPanel cardLayout) {
        this.clusterPanel = clusterPanel;
        this.cardLayout = cardLayout;

        var cards = (CardLayout) cardLayout.getLayout();

        // TODO load cluster
        this.clusterPanel.addClusterButton(e -> {
            JOptionPane.showMessageDialog(this.clusterPanel.getRootPane(), "Adding new cluster", "New",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        this.clusterPanel.addClustersTreeListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var selPath = clusterPanel.getClustersTree().getSelectionPath();

                if (selPath != null) {
                    String selectedNode =
                            (String) ((DefaultMutableTreeNode) selPath.getLastPathComponent()).
                                    getUserObject();

                    cards.show(cardLayout, selectedNode);
                }
            }
        });
    }
}
