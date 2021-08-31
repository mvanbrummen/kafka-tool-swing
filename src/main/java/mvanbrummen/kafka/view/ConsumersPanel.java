package mvanbrummen.kafka.view;

import mvanbrummen.kafka.models.ConsumersTableModel;
import org.apache.kafka.clients.admin.ConsumerGroupListing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsumersPanel extends JTabbedPane {

    private DefaultTableModel consumersTableModel;
    private JTable consumersTable;

    public ConsumersPanel() {
        initUI();
    }

    private void initUI() {
        var borderLayout = new JPanel(new BorderLayout());

        borderLayout.add(BorderLayout.CENTER, buildTable());

        add("Consumers", borderLayout);
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
