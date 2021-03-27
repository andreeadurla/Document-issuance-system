package view.viewUser;

import view.Style;
import dto.DocumentDto;
import dto.HouseDto;
import dto.RequestDto;
import entity.Status_level;
import utils.ApplicationUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.UUID;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class RequestView extends JPanel{

    private JFrame frame;

    private JTable requestsTable;
    private JButton viewRequestButton = new JButton("view request");
    private JButton addRequestButton = new JButton("+ add request");
    private JButton updateRequestButton = new JButton("update request");
    private JButton deleteRequestButton = new JButton("- delete request");

    private DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    //Add and Update Forms
    private JTextField dateField = new JTextField();
    private JComboBox location = new JComboBox();
    private JComboBox documentType = new JComboBox();
    private JTextArea purposeArea = new JTextArea();
    private JTextField statusField = new JTextField();
    private JTextArea errorArea = new JTextArea();
    private JButton submitAddButton = new JButton("Save");
    private JButton submitUpdateButton = new JButton("Save");

    private JDialog addForm;
    private JDialog updateForm;

    public RequestView(JFrame frame) {
        this.frame = frame;

        //Add Tabel to the main panel
        this.add(createTablePanel());

        //Create a panel with add button and delete button
        JPanel buttonsPanel = new JPanel();
        Style.styleButton(viewRequestButton, 150, 30, new Font("Arial", Font.BOLD, 13));
        buttonsPanel.add(viewRequestButton);

        Style.styleButton(addRequestButton, 150, 30, new Font("Arial", Font.BOLD, 13));
        buttonsPanel.add(addRequestButton);

        Style.styleButton(updateRequestButton, 150, 30, new Font("Arial", Font.BOLD, 13));
        buttonsPanel.add(updateRequestButton);

        Style.styleButton(deleteRequestButton, 150, 30, new Font("Arial", Font.BOLD, 13));
        buttonsPanel.add(deleteRequestButton);

        //Add ButtonsPanel to the main panel
        this.add(buttonsPanel);
    }

    private JScrollPane createTablePanel() {
        //Collumn's name
        tableModel.addColumn("ID");
        tableModel.addColumn("Status");
        tableModel.addColumn("Date");
        tableModel.addColumn("Document Type");
        tableModel.addColumn("Location");
        tableModel.addColumn("Purpose");

        requestsTable = new JTable(tableModel);
        requestsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        requestsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Collumns
        requestsTable.getColumnModel().getColumn(0).setMinWidth(0);
        requestsTable.getColumnModel().getColumn(0).setMaxWidth(0);
        requestsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        requestsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        requestsTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        requestsTable.getColumnModel().getColumn(4).setPreferredWidth(250);
        requestsTable.getColumnModel().getColumn(5).setPreferredWidth(300);

        requestsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        requestsTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(requestsTable);
        scrollPane.setPreferredSize(new Dimension(753, 250));

        return scrollPane;
    }

    public void insertInTable(RequestDto requestDto) {
        String id = requestDto.getId();
        String date = ApplicationUtils.printPrettyDate(requestDto.getDate());
        String documentType = requestDto.getDocument().toString();
        String house = requestDto.getHouse().toString();
        String purpose = requestDto.getPurpose();
        String status = requestDto.getStatus();

        tableModel.addRow(new Object[] {id, status, date, documentType, house, purpose});
    }

    //Display a form where we can view the request
    public void displaySelectedRow() {
        int selectedRow = requestsTable.getSelectedRow();

        if(selectedRow > -1) {
            DisplayRequestFormView form = new DisplayRequestFormView(frame);

            form.setDateField(tableModel.getValueAt(selectedRow, 2). toString());
            form.setStatusField(tableModel.getValueAt(selectedRow, 1). toString());
            form.setDocumentTypeField(tableModel.getValueAt(selectedRow, 3). toString());
            form.setLocationArea(tableModel.getValueAt(selectedRow, 4). toString());
            form.setPurposeArea(tableModel.getValueAt(selectedRow, 5). toString());

            form.createForm();
        }
    }

    public void createAddForm() {
        addForm = new JDialog(frame, "Add Request", true);
        //Style JDialog
        addForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addForm.setPreferredSize(new Dimension(600, 700));
        addForm.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Title
        c.insets.set(5, 10, 60,10);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0; c.gridy = 0;
        JLabel label = new JLabel("Add Request");
        Style.styleTitle(label, new Font("Arial", Font.ITALIC, 25));
        panel.add(label, c);

        //Labels + Field
        c.insets.set(5, 10, 10,10);
        c.anchor = GridBagConstraints.LINE_START;

        //Location
        c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("Location"), c);

        c.gridx = 0; c.gridy = 2;
        Style.styleComboBox(location, 400, 30, new Font("Arial", Font.ITALIC, 15));
        panel.add(location, c);

        //Document Type
        c.gridx = 0; c.gridy = 3;
        panel.add(new JLabel("Document Type"), c);

        c.gridx = 0; c.gridy = 4;
        Style.styleComboBox(documentType, 400, 30, new Font("Arial", Font.ITALIC, 15));
        panel.add(documentType, c);

        //Purpose
        c.gridx = 0; c.gridy = 5;
        panel.add(new JLabel("Purpose"), c);

        c.gridx = 0; c.gridy = 6;
        purposeArea.setLineWrap(true);
        Style.styleTextArea(purposeArea, 400, 100, new Font("Arial", Font.ITALIC, 15));
        JScrollPane scrollPane = new JScrollPane(purposeArea);
        panel.add(scrollPane, c);

        //Error area
        c.gridx = 0; c.gridy = 7;
        Style.styleErrorArea(errorArea, 35);
        panel.add(errorArea, c);

        //Submit Button
        c.insets.set(30, 10, 10, 10);
        c.gridx = 0; c.gridy = 8;
        Style.styleButton(submitAddButton, 400, 30, new Font("Arial", Font.BOLD, 15));
        panel.add(submitAddButton, c);

        addForm.add(panel);
        addForm.pack();
        addForm.setVisible(true);
    }

    public void createUpdateForm() {
        updateForm = new JDialog(frame, "Update Request", true);

        //Style JDialog
        updateForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        updateForm.setPreferredSize(new Dimension(600, 800));
        updateForm.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Title
        c.insets.set(5, 10, 60,10);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0; c.gridy = 0;
        JLabel label = new JLabel("Update Request");
        Style.styleTitle(label, new Font("Arial", Font.ITALIC, 25));
        panel.add(label, c);

        //Labels + Field
        c.insets.set(5, 10, 10,10);
        c.anchor = GridBagConstraints.LINE_START;

        //Date
        c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("Date"), c);

        c.gridx = 0; c.gridy = 2;
        Style.styleTextField(dateField, 400, 30, new Font("Arial", Font.ITALIC, 15));
        dateField.setEditable(false);
        panel.add(dateField, c);

        //Location
        c.gridx = 0; c.gridy = 3;
        panel.add(new JLabel("Location"), c);

        c.gridx = 0; c.gridy = 4;
        Style.styleComboBox(location, 400, 30, new Font("Arial", Font.ITALIC, 15));
        location.setEditable(false);
        panel.add(location, c);

        //Document Type
        c.gridx = 0; c.gridy = 5;
        panel.add(new JLabel("Document Type"), c);

        c.gridx = 0; c.gridy = 6;
        Style.styleComboBox(documentType, 400, 30, new Font("Arial", Font.ITALIC, 15));
        panel.add(documentType, c);

        //Purpose
        c.gridx = 0; c.gridy = 7;
        panel.add(new JLabel("Purpose"), c);

        c.gridx = 0; c.gridy = 8;
        purposeArea.setLineWrap(true);
        Style.styleTextArea(purposeArea, 400, 100, new Font("Arial", Font.ITALIC, 15));
        JScrollPane scrollPane = new JScrollPane(purposeArea);
        panel.add(scrollPane, c);

        //Status
        c.gridx = 0; c.gridy = 9;
        Style.styleTextField(statusField, 400, 30, new Font("Arial", Font.ITALIC, 15));
        statusField.setEditable(false);
        panel.add(statusField, c);

        //Error area
        c.gridx = 0; c.gridy = 10;
        Style.styleErrorArea(errorArea, 35);
        panel.add(errorArea, c);

        //Submit Button
        c.insets.set(30, 10, 10, 10);
        c.gridx = 0; c.gridy = 11;
        Style.styleButton(submitUpdateButton, 400, 30, new Font("Arial", Font.BOLD, 15));
        panel.add(submitUpdateButton, c);

        updateForm.add(panel);
        updateForm.pack();
        updateForm.setVisible(true);
    }

    public void insertHouseInComboBox(HouseDto house) {
        location.addItem(house);
    }

    public void insertDocumentTypeInComboBox(DocumentDto document) {
        documentType.addItem(document);
    }

    public void clearPurposeArea() {
        purposeArea.setText("");
    }

    public void clearComboBoxes() {
        location.removeAllItems();
        documentType.removeAllItems();
    }

    public RequestDto getRequestInfos() {
        RequestDto requestDto = new RequestDto();

        String id = UUID.randomUUID().toString();

        requestDto.setId(id);
        requestDto.setDate(new Date());
        requestDto.setHouse((HouseDto)location.getSelectedItem());
        requestDto.setDocument((DocumentDto)documentType.getSelectedItem());
        requestDto.setPurpose(purposeArea.getText());
        requestDto.setStatus(Status_level.IN_PROCESSING.toString());

        return requestDto;
    }

    public void setUpdateFields(RequestDto requestDto) {
        dateField.setText(ApplicationUtils.printPrettyDate(requestDto.getDate()));
        location.setSelectedItem(requestDto.getHouse());
        documentType.setSelectedItem(requestDto.getDocument());
        purposeArea.setText(requestDto.getPurpose());
        statusField.setText(requestDto.getStatus());
    }

    public RequestDto getUpdatedInfos() {
        RequestDto requestDto = null;
        int selectedRow = requestsTable.getSelectedRow();

        if (selectedRow > -1){
            requestDto = new RequestDto();

            requestDto.setId(tableModel.getValueAt(selectedRow, 0).toString());
            requestDto.setHouse((HouseDto) location.getSelectedItem());
            requestDto.setDocument((DocumentDto) documentType.getSelectedItem());
            requestDto.setPurpose(purposeArea.getText());
            requestDto.setStatus(tableModel.getValueAt(selectedRow, 1).toString());
        }

        return requestDto;
    }

    public void updateSelectedRow(RequestDto request) {
        int row = requestsTable.getSelectedRow();

        if(row > -1 && row < requestsTable.getRowCount()) {
            requestsTable.setValueAt(request.getDocument(), row, 3);
            requestsTable.setValueAt(request.getHouse(), row, 4);
            requestsTable.setValueAt(request.getPurpose(), row, 5);
        }
    }

    public String getIdOfSelectedRequest() {
        int row = requestsTable.getSelectedRow();

        String id = null;
        if(row > -1 && row < requestsTable.getRowCount())
            id = tableModel.getValueAt(row, 0).toString();

        return id;
    }

    public void deleteSelectedRequest() {
        int row = requestsTable.getSelectedRow();

        if(row > -1 && row < requestsTable.getRowCount()) {
            tableModel.removeRow(row);
        }
    }

    public void setVisibleErrorArea(boolean flag) {
        errorArea.setVisible(flag);
    }

    public void setErrorArea(String message) {
        errorArea.setText(message);
    }

    public void closeAddForm() {
        addForm.dispose();
    }

    public void closeUpdateForm() {
        updateForm.dispose();
    }

    public void addViewButtonListener(ActionListener e) {
        viewRequestButton.addActionListener(e);
    }

    public void addCreateAddFormButtonListener(ActionListener e) {
        addRequestButton.addActionListener(e);
    }

    public void addSubmitAddButtonListener(ActionListener e) {
        submitAddButton.addActionListener(e);
    }

    public void addCreateUpdateFormButtonListener(ActionListener e) {
        updateRequestButton.addActionListener(e);
    }

    public void addSubmitUpdateButtonListener(ActionListener e) {
        submitUpdateButton.addActionListener(e);
    }

    public void addDeleteButtonListener(ActionListener e) {
        deleteRequestButton.addActionListener(e);
    }

}
