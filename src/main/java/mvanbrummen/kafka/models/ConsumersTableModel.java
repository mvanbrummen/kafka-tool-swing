package mvanbrummen.kafka.models;

import javax.swing.table.DefaultTableModel;

public class ConsumersTableModel extends DefaultTableModel {
    private static final String[] COLUMN_NAMES = new String[]{
            "Consumer Group",
            "Lag",
            "Partitions",
            "Topics"};

    public ConsumersTableModel() {
        super(COLUMN_NAMES, 0);
    }
}
