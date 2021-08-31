package mvanbrummen.kafka.models;

import javax.swing.table.DefaultTableModel;

public class BrokersTableModel extends DefaultTableModel {
    private static final String[] COLUMN_NAMES = new String[]{
            "ID",
            "Rack",
            "Host"};

    public BrokersTableModel() {
        super(COLUMN_NAMES, 0);
    }
}
