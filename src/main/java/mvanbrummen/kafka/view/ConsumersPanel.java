package mvanbrummen.kafka.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import mvanbrummen.kafka.models.ConsumersTableModel;
import org.apache.kafka.clients.admin.ConsumerGroupListing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsumersPanel extends JTabbedPane {

    private DefaultTableModel consumersTableModel;
    private JTable consumersTable;
    private JButton refreshButton;

    public ConsumersPanel() {
        initUI();
    }

    private void initUI() {
        var borderLayout = new JPanel(new BorderLayout());

        borderLayout.add(BorderLayout.NORTH, buildToolbar());
        borderLayout.add(BorderLayout.CENTER, buildTable());

        add("Consumers", borderLayout);
    }

    private JToolBar buildToolbar() {
        refreshButton = new JButton();

        refreshButton.setIcon(new FlatSVGIcon("icons/refresh.svg"));
        var toolbar = new JToolBar();

        var topicSearchTextField = new JTextField();
        topicSearchTextField.setLayout(new BorderLayout());
        var searchLabel = new JLabel(new FlatSVGIcon("icons/search.svg"));
        topicSearchTextField.add(BorderLayout.EAST, searchLabel);

        toolbar.add(topicSearchTextField);
        toolbar.addSeparator();
        toolbar.add(refreshButton);
        toolbar.addSeparator();

        return toolbar;
    }

    private JScrollPane buildTable() {
        consumersTableModel = new ConsumersTableModel();

        consumersTable = new JTable(consumersTableModel);

        var sp = new JScrollPane(consumersTable);

        return sp;
    }

    public void setConsumers(java.util.List<ConsumerGroupListing> consumers) {
        final var tableModel = (ConsumersTableModel) consumersTable.getModel();

        consumers.forEach(i -> tableModel.addRow(new Object[]{i.groupId(), "-", "0", "0"}));
    }
}
