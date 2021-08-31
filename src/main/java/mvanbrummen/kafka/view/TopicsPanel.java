package mvanbrummen.kafka.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import mvanbrummen.kafka.models.TopicsTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class TopicsPanel extends JTabbedPane {

    private JTable topicsTable;
    private JButton refreshButton;
    private JButton addNewTopicButton;

    private TableRowSorter<DefaultTableModel> topicsTableSorter;

    public TopicsPanel() {
        initUI();
    }

    private void initUI() {
        var b2 = new JPanel(new BorderLayout());

        b2.add(BorderLayout.NORTH, buildToolbar());
        b2.add(BorderLayout.CENTER, buildTable());

        add("Topics (0)", b2);
    }

    private JToolBar buildToolbar() {
        refreshButton = new JButton();

        refreshButton.setIcon(new FlatSVGIcon("icons/refresh.svg"));
        var toolbar = new JToolBar();

        var topicSearchTextField = new JTextField();
        topicSearchTextField.setLayout(new BorderLayout());
        var searchLabel = new JLabel(new FlatSVGIcon("icons/search.svg"));
        topicSearchTextField.add(BorderLayout.EAST, searchLabel);

        addNewTopicButton = new JButton("Add Topic");
        addNewTopicButton.setIcon(new FlatSVGIcon("icons/plus.svg"));

        var showInternalTopicsButton = new JToggleButton();
        showInternalTopicsButton.setIcon(new FlatSVGIcon("icons/show.svg"));
        showInternalTopicsButton.setToolTipText("Toggle showing internal kafka topics");

        showInternalTopicsButton.addActionListener(e -> {
            if (showInternalTopicsButton.isSelected()) {
                topicsTableSorter.setRowFilter(null);
            } else {
                topicsTableSorter.setRowFilter(RowFilter.notFilter(RowFilter.regexFilter("^_", 0)));
            }
        });


        topicSearchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Event in search field");
                var text = topicSearchTextField.getText();
                if (text.trim().length() == 0) {
                    if (showInternalTopicsButton.isSelected()) {
                        topicsTableSorter.setRowFilter(null);
                    } else {
                        topicsTableSorter.setRowFilter(RowFilter.notFilter(RowFilter.regexFilter("^_", 0)));
                    }
                } else {
                    if (!showInternalTopicsButton.isSelected()) {
                        topicsTableSorter.setRowFilter(RowFilter.andFilter(
                                        Set.of(
                                                RowFilter.notFilter(RowFilter.regexFilter("^_", 0)),
                                                RowFilter.regexFilter("(?i)" + text)
                                        )
                                )
                        );

                    } else {
                        topicsTableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }
            }
        });

        toolbar.add(topicSearchTextField);
        toolbar.add(showInternalTopicsButton);
        toolbar.addSeparator();
        toolbar.add(refreshButton);
        toolbar.addSeparator();
        toolbar.add(addNewTopicButton);

        return toolbar;
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

    public void refreshButton(ActionListener actionListener) {
        refreshButton.addActionListener(actionListener);
    }

    public void addNewTopicButton(ActionListener actionListener) {
        addNewTopicButton.addActionListener(actionListener);
    }

}
