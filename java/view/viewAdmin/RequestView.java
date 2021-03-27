package view.viewAdmin;

import view.Style;
import dto.RequestDto;
import entity.Status_level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class RequestView extends JPanel{

    private JFrame frame;

    private JButton allButton = new JButton("all");
    private JButton inProcessingButton = new JButton("in processing");
    private JButton acceptedButton = new JButton("accepted");
    private JButton rejectedButton = new JButton("rejected");

    private RequestTable allRequestsTable;
    private RequestTable acceptedRequestsTable;
    private RequestTable rejectedRequestsTable;
    private RequestTable inProcessingRequestsTable;

    private JDialog updateForm;

    private JTextField dateField = new JTextField();
    private JTextField nameField = new JTextField();
    private JTextField documentTypeField = new JTextField();
    private JTextArea locationArea = new JTextArea();
    private JTextArea purposeArea = new JTextArea();
    private JComboBox statusComboBox = new JComboBox();
    private JTextArea errorArea = new JTextArea();

    private JButton submitButton = new JButton("Save");
    private JButton viewButton = new JButton("View");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");

    public RequestView(JFrame frame) {
        this.frame = frame;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets.set(10, 10, 20,10);
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 0; c.gridy = 0;
        this.add(buttonsPanelUp(), c);

        c.gridx = 0; c.gridy = 1;
        allRequestsTable = new RequestTable();
        this.add(allRequestsTable, c);

        acceptedRequestsTable = new RequestTable();
        acceptedRequestsTable.setVisible(false);
        this.add(acceptedRequestsTable, c);

        rejectedRequestsTable = new RequestTable();
        rejectedRequestsTable.setVisible(false);
        this.add(rejectedRequestsTable, c);

        inProcessingRequestsTable = new RequestTable();
        inProcessingRequestsTable.setVisible(false);
        this.add(inProcessingRequestsTable, c);

        c.gridx = 0; c.gridy = 2;
        this.add(buttonsPanelDown(), c);
    }

    private JPanel buttonsPanelUp() {
        JPanel panel = new JPanel();

        panel.add(allButton);
        panel.add(inProcessingButton);
        panel.add(acceptedButton);
        panel.add(rejectedButton);

        return panel;
    }

    private JPanel buttonsPanelDown() {
        JPanel panel = new JPanel();

        panel.add(viewButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        return panel;
    }

    public void insertInAllRequestsTable(RequestDto requestDto) {
        allRequestsTable.insertInTable(requestDto);
    }

    public void insertInProcessingRequestsTable(RequestDto requestDto) {
        inProcessingRequestsTable.insertInTable(requestDto);
    }

    public void insertInAcceptedRequestsTable(RequestDto requestDto) {
        acceptedRequestsTable.insertInTable(requestDto);
    }

    public void insertInRejectedRequestsTable(RequestDto requestDto) {
        rejectedRequestsTable.insertInTable(requestDto);
    }

    public void updateStatusOfSelectedRequest(String status) {
        allRequestsTable.updateStatusOfSelectedRequest(status);
        inProcessingRequestsTable.deleteSelectedRow();
    }

    public String getIdOfSelectedRequest() {

        String id1 = allRequestsTable.getIdOfSelectedRequest();
        if(id1 != null)
            return id1;

        String id2 = inProcessingRequestsTable.getIdOfSelectedRequest();
        if(id2 != null)
            return id2;

        String id3 = acceptedRequestsTable.getIdOfSelectedRequest();
        if(id3 != null)
            return id3;

        String id4 = rejectedRequestsTable.getIdOfSelectedRequest();
        if(id4 != null)
            return id4;

        return null;
    }

    public void deselectTableRows() {
        allRequestsTable.deselectTableRows();
        inProcessingRequestsTable.deselectTableRows();
        acceptedRequestsTable.deselectTableRows();
        rejectedRequestsTable.deselectTableRows();
    }

    public void deleteSelectedRow() {
        allRequestsTable.deleteSelectedRow();
        inProcessingRequestsTable.deselectTableRows();
        acceptedRequestsTable.deleteSelectedRow();
        rejectedRequestsTable.deleteSelectedRow();
    }

    public void clearAllRequestsTable() {
        allRequestsTable.clearTable();
    }

    public void clearInProcessingRequestTable() {
        inProcessingRequestsTable.clearTable();
    }

    public void clearAcceptedRequestTable() {
        acceptedRequestsTable.clearTable();
    }

    public void clearRejectedRequestTable() {
        rejectedRequestsTable.clearTable();
    }

    public void displaySelectedRow() {
        allRequestsTable.displaySelectedRow();
        inProcessingRequestsTable.displaySelectedRow();
        acceptedRequestsTable.displaySelectedRow();
        rejectedRequestsTable.displaySelectedRow();
    }

    public void createUpdateForm() {
        updateForm = new JDialog(frame, "Appprove Request", true);

        //Style JDialog
        updateForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        updateForm.setPreferredSize(new Dimension(600, 830));
        updateForm.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Title
        c.insets.set(5, 10, 60,10);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0; c.gridy = 0;
        JLabel label = new JLabel("Approve Request");
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

        //Name
        c.gridx = 0; c.gridy = 3;
        panel.add(new JLabel("Name"), c);

        c.gridx = 0; c.gridy = 4;
        Style.styleTextField(nameField, 400, 30, new Font("Arial", Font.ITALIC, 15));
        nameField.setEditable(false);
        panel.add(nameField, c);

        //Document Type
        c.gridx = 0; c.gridy = 5;
        panel.add(new JLabel("Document Type"), c);

        c.gridx = 0; c.gridy = 6;
        Style.styleTextField(documentTypeField, 400, 30, new Font("Arial", Font.ITALIC, 15));
        documentTypeField.setEditable(false);
        panel.add(documentTypeField, c);

        //Location
        c.gridx = 0; c.gridy = 7;
        panel.add(new JLabel("Location"), c);

        c.gridx = 0; c.gridy = 8;
        Style.styleTextArea(locationArea, 400, 30, new Font("Arial", Font.ITALIC, 15));
        locationArea.setEditable(false);
        panel.add(locationArea, c);

        //Purpose
        c.gridx = 0; c.gridy = 9;
        panel.add(new JLabel("Purpose"), c);

        c.gridx = 0; c.gridy = 10;
        purposeArea.setLineWrap(true);
        Style.styleTextArea(purposeArea, 400, 100, new Font("Arial", Font.ITALIC, 15));
        purposeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(purposeArea);
        panel.add(scrollPane, c);

        //Status
        c.gridx = 0; c.gridy = 11;
        panel.add(new JLabel("Status"), c);

        c.gridx = 0; c.gridy = 12;
        Style.styleComboBox(statusComboBox, 400, 30, new Font("Arial", Font.ITALIC, 15));
        panel.add(statusComboBox, c);

        //Error area
        c.gridx = 0; c.gridy = 13;
        Style.styleErrorArea(errorArea, 32);
        panel.add(errorArea, c);

        //Submit Button
        c.insets.set(30, 10, 10, 10);
        c.gridx = 0; c.gridy = 14;
        Style.styleButton(submitButton, 400, 30, new Font("Arial", Font.BOLD, 15));
        panel.add(submitButton, c);

        updateForm.add(panel);
        updateForm.pack();
        updateForm.setVisible(true);
    }

    public void insertStatusInComboBox() {
        for (Status_level status : Status_level.values()) {
           statusComboBox.addItem(status.toString());
        }
    }

    public void setUpdateFields(RequestDto requestDto) {
        dateField.setText(requestDto.getDate().toString());
        nameField.setText(requestDto.getUser().toString());
        documentTypeField.setText(requestDto.getHouse().toString());
        locationArea.setText(requestDto.getDocument().toString());
        purposeArea.setText(requestDto.getPurpose());
        statusComboBox.setSelectedItem(requestDto.getStatus());
    }

    public String getSelectedStatus() {
        return (String)statusComboBox.getSelectedItem();
    }

    public void setVisibleErrorArea(boolean flag) {
        errorArea.setVisible(flag);
    }

    public void setErrorArea(String message) {
        errorArea.setText(message);
    }

    public void viewAllRequestsTable() {
        allRequestsTable.setVisible(true);
        inProcessingRequestsTable.setVisible(false);
        acceptedRequestsTable.setVisible(false);
        rejectedRequestsTable.setVisible(false);
    }

    public void viewInProcessingRequestsTable() {
        inProcessingRequestsTable.setVisible(true);
        acceptedRequestsTable.setVisible(false);
        allRequestsTable.setVisible(false);
        rejectedRequestsTable.setVisible(false);
    }

    public void viewAcceptedRequestsTable() {
        acceptedRequestsTable.setVisible(true);
        inProcessingRequestsTable.setVisible(false);
        allRequestsTable.setVisible(false);
        rejectedRequestsTable.setVisible(false);
    }

    public void viewRejectedRequestsTable() {
        rejectedRequestsTable.setVisible(true);
        inProcessingRequestsTable.setVisible(false);
        allRequestsTable.setVisible(false);
        acceptedRequestsTable.setVisible(false);
    }

    public void addAllButtonListener(ActionListener e) {
        allButton.addActionListener(e);
    }

    public void addInProcessingButtonListener(ActionListener e) {
        inProcessingButton.addActionListener(e);
    }

    public void addAcceptedButtonListener(ActionListener e) {
        acceptedButton.addActionListener(e);
    }

    public void addRejectedButtonListener(ActionListener e) {
        rejectedButton.addActionListener(e);
    }

    public void addViewButtonListener(ActionListener e) {
        viewButton.addActionListener(e);
    }

    public void addUpdateButtonListener(ActionListener e) {
        updateButton.addActionListener(e);
    }

    public void addDeleteButtonListener(ActionListener e) {
        deleteButton.addActionListener(e);
    }


    public void addSubmitButtonListener(ActionListener e) {
        submitButton.addActionListener(e);
    }

    public void closeUpdateForm() {
        updateForm.dispose();
    }
}
