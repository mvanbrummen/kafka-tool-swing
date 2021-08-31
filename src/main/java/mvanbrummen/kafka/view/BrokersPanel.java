package mvanbrummen.kafka.view;

import javax.swing.*;

public class BrokersPanel extends JTabbedPane {

    public BrokersPanel() {
        initUI();
    }

    private void initUI() {
        add(new JLabel("brokers"));
    }
}
