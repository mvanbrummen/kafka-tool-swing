package mvanbrummen.kafka.view;

import mvanbrummen.kafka.models.BrokersTableModel;
import org.apache.kafka.common.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BrokersPanel extends JTabbedPane {

    private DefaultTableModel brokersTableModel;
    private JTable brokersTable;

    public BrokersPanel() {
        initUI();
    }

    private void initUI() {
        var borderLayout = new JPanel(new BorderLayout());

        borderLayout.add(BorderLayout.CENTER, buildTable());

        add("Brokers", borderLayout);
    }

    private JScrollPane buildTable() {
        brokersTableModel = new BrokersTableModel();

        brokersTable = new JTable(brokersTableModel);

        var sp = new JScrollPane(brokersTable);

        return sp;
    }

    public void setBrokers(java.util.List<Node> brokers) {
        final var tableModel = (BrokersTableModel) brokersTable.getModel();

        brokers.forEach(i -> tableModel.addRow(new Object[]{i.idString(), i.rack(), i.host()}));
    }
}
