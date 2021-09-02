package mvanbrummen.kafka.view.topics;

import mvanbrummen.kafka.models.TopicsTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class TopicsPanel extends JTabbedPane {

    private JTable topicsTable;
    private TopicsToolbar topicsToolbar;

    private JSplitPane detailsSplitPane;

    private TableRowSorter<DefaultTableModel> topicsTableSorter;
    private TopicsDetails topicsDetails;

    public TopicsPanel() {
        initUI();
    }

    private void initUI() {
        var b2 = new JPanel(new BorderLayout());
        topicsToolbar = new TopicsToolbar();
        topicsDetails = new TopicsDetails();

        detailsSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                buildTable(),
                topicsDetails
        );

        // hide details by default
        detailsSplitPane.setDividerLocation(0);
        detailsSplitPane.getTopComponent().setVisible(true);
        detailsSplitPane.getBottomComponent().setVisible(false);

        b2.add(BorderLayout.NORTH, topicsToolbar);
        b2.add(BorderLayout.CENTER, detailsSplitPane);

        add("Topics (0)", b2);
    }

    private JScrollPane buildTable() {
        DefaultTableModel topicsTableModel = new TopicsTableModel();

        topicsTable = new JTable(topicsTableModel);

        topicsTableSorter = new TableRowSorter<>((DefaultTableModel) topicsTable.getModel());
        topicsTableSorter.setRowFilter(RowFilter.notFilter(RowFilter.regexFilter("^_", 0)));
        topicsTable.setRowSorter(topicsTableSorter);

        var sp = new JScrollPane(topicsTable);

        return sp;
    }

    public void setTopicCount(int count) {
        this.setTitleAt(0, "Topics (" + count + ")");
    }

    public void setTopics(java.util.List<String> topics) {
        final var tableModel = (TopicsTableModel) topicsTable.getModel();

        topics.forEach(i -> tableModel.addRow(new Object[]{i, "1", "0", "0", "0"}));
    }

    public JSplitPane getDetailsSplitPane() {
        return detailsSplitPane;
    }

    public void showDetailsSplitPane() {
        detailsSplitPane.getBottomComponent().setVisible(true);
        detailsSplitPane.setDividerLocation(0.5);
    }

    public void topicsTable(MouseAdapter mouseAdapter) {
        this.topicsTable.addMouseListener(mouseAdapter);
    }

    public JTable getTopicsTable() {
        return topicsTable;
    }


    public TopicsToolbar getTopicsToolbar() {
        return topicsToolbar;
    }

    public TableRowSorter<DefaultTableModel> getTopicsTableSorter() {
        return topicsTableSorter;
    }

    public TopicsDetails getTopicsDetails() {
        return topicsDetails;
    }
}
