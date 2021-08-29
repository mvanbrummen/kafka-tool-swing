package mvanbrummen.kafka.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TopicsPanel extends JPanel {

    private DefaultTableModel topicsTableModel;
    private TableRowSorter<DefaultTableModel> topicsTableSorter;

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

        var b2 = new JPanel(new BorderLayout());

        var refreshButton = new JButton();

        refreshButton.setIcon(new FlatSVGIcon("icons/refresh.svg"));
        var toolbar2 = new JToolBar();

        var topicSearchTextField = new JTextField();
        topicSearchTextField.setLayout(new BorderLayout());
        var searchLabel = new JLabel(new FlatSVGIcon("icons/search.svg"));
        topicSearchTextField.add(BorderLayout.EAST, searchLabel);

        var newTopicButton = new JButton("Add Topic");
        newTopicButton.setIcon(new FlatSVGIcon("icons/plus.svg"));

        var showInternalTopicsButton = new JToggleButton();
        showInternalTopicsButton.setIcon(new FlatSVGIcon("icons/show.svg"));
        showInternalTopicsButton.setToolTipText("Toggle showing internal kafka topics");

        topicSearchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Event in search field");
                var text = topicSearchTextField.getText();
                if (text.trim().length() == 0) {
                    topicsTableSorter.setRowFilter(null);
                } else {
                    topicsTableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        toolbar2.add(topicSearchTextField);
        toolbar2.add(showInternalTopicsButton);
        toolbar2.addSeparator();
        toolbar2.add(refreshButton);
        toolbar2.addSeparator();
        toolbar2.add(newTopicButton);


        b2.add(BorderLayout.NORTH, toolbar2);
        b2.add(BorderLayout.CENTER, buildTable());

        var tabbedPane = new JTabbedPane();
        tabbedPane.add("Topics", b2);

        splitPane.setRightComponent(tabbedPane);


        add(BorderLayout.CENTER, splitPane);

        var bottomToolbar = new JToolBar();
        bottomToolbar.add(new JLabel("v0.0.1"));
        add(BorderLayout.SOUTH, bottomToolbar);

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
        topicsTableModel = new DefaultTableModel(new String[]{"Topic name", "Partitions", "Count", "Size", "Consumers"}, 0);

        var table = new JTable(topicsTableModel);

        topicsTableSorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
        table.setRowSorter(topicsTableSorter);


        topicsTableModel.addRow(new Object[]{"property.foo.foo.foo-topic", "1", "0", "0", "0"});
        topicsTableModel.addRow(new Object[]{"property.foo.foo.foo-topic2", "1", "0", "0", "0"});
        topicsTableModel.addRow(new Object[]{"property.foo.foo.foo-topic3", "1", "0", "0", "0"});
        topicsTableModel.addRow(new Object[]{"property.foo.foo.foo-topic4", "69", "0", "0", "0"});
        topicsTableModel.addRow(new Object[]{"property.foo.foo.foo-topic5", "1", "0", "0", "0"});
        topicsTableModel.addRow(new Object[]{"property.foo.foo.foo-topic6", "1", "0", "0", "0"});
        topicsTableModel.addRow(new Object[]{"property.foo.foo.foo-topic7", "1", "0", "0", "0"});

        var sp = new JScrollPane(table);

        return sp;
    }

}
