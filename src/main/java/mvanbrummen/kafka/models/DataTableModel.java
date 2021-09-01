package mvanbrummen.kafka.models;

import javax.swing.table.DefaultTableModel;

public class DataTableModel extends DefaultTableModel {
    private static final String[] COLUMN_NAMES = new String[]{
            "Key",
            "Value",
            "Partition",
            "Offset",
            "Timestamp",
    };

    public DataTableModel() {
        super(COLUMN_NAMES, 0);
    }
}
