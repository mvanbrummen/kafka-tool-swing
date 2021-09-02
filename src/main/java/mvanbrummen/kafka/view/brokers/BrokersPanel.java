package mvanbrummen.kafka.view.brokers;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import mvanbrummen.kafka.models.BrokersTableModel;
import org.apache.kafka.common.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BrokersPanel extends JTabbedPane {

    private DefaultTableModel brokersTableModel;
    private JTable brokersTable;
    private JButton refreshButton;

    public BrokersPanel() {
        initUI();
    }

    private void initUI() {
        var borderLayout = new JPanel(new BorderLayout());

        borderLayout.add(BorderLayout.NORTH, buildToolbar());
        borderLayout.add(BorderLayout.CENTER, buildTable());

        add("Brokers", borderLayout);
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
