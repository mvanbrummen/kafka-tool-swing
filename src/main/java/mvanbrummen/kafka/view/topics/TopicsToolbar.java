package mvanbrummen.kafka.view.topics;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TopicsToolbar extends JToolBar {

    private JButton refreshButton;
    private JButton addNewTopicButton;
    private JToggleButton showInternalTopicsButton;
    private JTextField topicSearchTextField;

    public TopicsToolbar() {
        initUI();
    }

    private void initUI() {
        refreshButton = new JButton();
        topicSearchTextField = new JTextField();

        refreshButton.setIcon(new FlatSVGIcon("icons/refresh.svg"));

        topicSearchTextField.setLayout(new BorderLayout());
        var searchLabel = new JLabel(new FlatSVGIcon("icons/search.svg"));
        topicSearchTextField.add(BorderLayout.EAST, searchLabel);

        addNewTopicButton = new JButton("Add Topic");
        addNewTopicButton.setIcon(new FlatSVGIcon("icons/plus.svg"));

        showInternalTopicsButton = new JToggleButton();
        showInternalTopicsButton.setIcon(new FlatSVGIcon("icons/show.svg"));
        showInternalTopicsButton.setToolTipText("Toggle showing internal kafka topics");

        add(topicSearchTextField);
        add(showInternalTopicsButton);
        addSeparator();
        add(refreshButton);
        addSeparator();
        add(addNewTopicButton);
    }

    public void refreshButton(ActionListener a) {
        this.refreshButton.addActionListener(a);
    }

    public void addNewTopicButton(ActionListener a) {
        this.addNewTopicButton.addActionListener(a);
    }

    public void showInternalTopicsButton(ActionListener a) {
        this.showInternalTopicsButton.addActionListener(a);
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getAddNewTopicButton() {
        return addNewTopicButton;
    }

    public JToggleButton getShowInternalTopicsButton() {
        return showInternalTopicsButton;
    }

    public JTextField getTopicSearchTextField() {
        return topicSearchTextField;
    }
}
