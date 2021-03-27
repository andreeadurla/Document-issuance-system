package view.viewUser;

import view.Style;

import javax.swing.*;
import java.awt.*;

public class DisplayRequestFormView extends JDialog {

    private JTextField dateField = new JTextField();
    private JTextField documentTypeField = new JTextField();
    private JTextArea locationArea = new JTextArea();
    private JTextArea purposeArea = new JTextArea();
    private JTextField statusField = new JTextField();

    public DisplayRequestFormView(JFrame frame) {
        super(frame, "View Request", true);
    }

    public void createForm() {

        //Style JDialog
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 700));
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Title
        c.insets.set(5, 10, 60,10);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0; c.gridy = 0;
        JLabel label = new JLabel("View Request");
        Style.styleTitle(label, new Font("Arial", Font.ITALIC, 25));
        panel.add(label, c);

        //Labels + Fields
        c.insets.set(5, 10, 10,10);
        c.anchor = GridBagConstraints.LINE_START;

        //Date
        c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("Date"), c);

        c.gridx = 0; c.gridy = 2;
        Style.styleTextField(dateField, 400, 30, new Font("Arial", Font.ITALIC, 15));
        dateField.setEditable(false);
        panel.add(dateField, c);

        //Document Type
        c.gridx = 0; c.gridy = 3;
        panel.add(new JLabel("Document Type"), c);

        c.gridx = 0; c.gridy = 4;
        Style.styleTextField(documentTypeField, 400, 30, new Font("Arial", Font.ITALIC, 15));
        documentTypeField.setEditable(false);
        panel.add(documentTypeField, c);

        //Location
        c.gridx = 0; c.gridy = 5;
        panel.add(new JLabel("Location"), c);

        c.gridx = 0; c.gridy = 6;
        Style.styleTextArea(locationArea, 400, 30, new Font("Arial", Font.ITALIC, 15));
        locationArea.setEditable(false);
        panel.add(locationArea, c);

        //Purpose
        c.gridx = 0; c.gridy = 7;
        panel.add(new JLabel("Purpose"), c);

        c.gridx = 0; c.gridy = 8;
        purposeArea.setLineWrap(true);
        Style.styleTextArea(purposeArea, 400, 100, new Font("Arial", Font.ITALIC, 15));
        purposeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(purposeArea);
        panel.add(scrollPane, c);

        //Status
        c.gridx = 0; c.gridy = 9;
        panel.add(new JLabel("Status"), c);

        c.gridx = 0; c.gridy = 10;
        Style.styleTextField(statusField, 400, 30, new Font("Arial", Font.ITALIC, 15));
        statusField.setEditable(false);
        panel.add(statusField, c);

        add(panel);
        pack();
        setVisible(true);
    }

    public void setDateField(String date) {
        this.dateField.setText(date);
    }

    public void setLocationArea(String location) {
        this.locationArea.setText(location);
    }

    public void setDocumentTypeField(String documentType) {
        this.documentTypeField.setText(documentType);
    }

    public void setPurposeArea(String purpose) {
        this.purposeArea.setText(purpose);
    }

    public void setStatusField(String status) {
        this.statusField.setText(status);
    }
}
