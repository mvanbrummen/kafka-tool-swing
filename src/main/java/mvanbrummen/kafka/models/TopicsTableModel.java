package mvanbrummen.kafka.models;

import javax.swing.table.DefaultTableModel;

public class TopicsTableModel extends DefaultTableModel {
    private static final String[] COLUMN_NAMES = new String[]{
            "Topic name",
            "Partitions",
            "Count",
            "Size",
            "Consumers"};

    public TopicsTableModel() {
        super(COLUMN_NAMES, 0);
    }
}
