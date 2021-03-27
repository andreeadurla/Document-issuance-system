package view.viewAdmin;

import view.Style;
import dto.DocumentDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.UUID;

public class DocumentTypeView extends JPanel{

    private JTable documentTypesTable;
    private JTextField documentTypeField = new JTextField();
    private JTextArea errorArea = new JTextArea();
    private JButton submitButton = new JButton("Save");
    private JButton deleteButton = new JButton("- delete document type");

    private DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public DocumentTypeView() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets.set(10, 10, 10,10);
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 0; c.gridy = 0;
        JPanel tablePanel = createTablePanel();
        this.add(tablePanel, c);

        c.gridx = 0; c.gridy = 1;
        Style.styleButton(deleteButton, 200, 30, new Font("Arial", Font.BOLD, 13));
        this.add(deleteButton, c);

        c.gridx = 0; c.gridy = 2;
        JPanel addForm = createAddForm();
        this.add(addForm, c);
    }


    private JPanel createTablePanel() {
        JPanel panel = new JPanel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Document Type");

        documentTypesTable = new JTable(tableModel);
        //documentTypesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        documentTypesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Collumns
        documentTypesTable.getColumnModel().getColumn(0).setMinWidth(0);
        documentTypesTable.getColumnModel().getColumn(0).setMaxWidth(0);
        documentTypesTable.getColumnModel().getColumn(1).setPreferredWidth(500);

        documentTypesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        documentTypesTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(documentTypesTable);
        scrollPane.setPreferredSize(new Dimension(501, 250));

        panel.add(scrollPane);
        return panel;
    }

    private JPanel createAddForm() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Title
        c.insets.set(5, 10, 40,10);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0; c.gridy = 0;
        JLabel label = new JLabel("Add Document Type");
        Style.styleTitle(label, new Font("Arial", Font.ITALIC, 16));
        panel.add(label, c);

        //Labels + Fields
        c.insets.set(5, 10, 10,10);
        c.anchor = GridBagConstraints.LINE_START;

        //Document Type
        c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("Document Type"), c);

        c.gridx = 0; c.gridy = 2;
        Style.styleTextField(documentTypeField, 300, 30, new Font("Arial", Font.BOLD, 15));
        panel.add(documentTypeField, c);

        //Error area
        c.gridx = 0; c.gridy = 3;
        Style.styleErrorArea(errorArea, 28);
        panel.add(errorArea, c);

        //Submit Button
        c.insets.set(30, 10, 10, 10);
        c.gridx = 0; c.gridy = 4;
        Style.styleButton(submitButton, 300, 30, new Font("Arial", Font.BOLD, 15));
        panel.add(submitButton, c);

        return panel;
    }

    public void insertInTable(DocumentDto document) {
        tableModel.addRow(new Object[] {document.getId(), document.getName()});
    }

    public DocumentDto getDocumentInfos() {

        DocumentDto documentDto = new DocumentDto();

        String id = UUID.randomUUID().toString();
        documentDto.setId(id);
        documentDto.setName(documentTypeField.getText());

        return documentDto;
    }

    public void setEmptyTextField() {
        documentTypeField.setText("");
    }


    public void deleteFromTable() {
        int row = documentTypesTable.getSelectedRow();

        String id = null;
        if(row > -1 && row < documentTypesTable.getRowCount())
            tableModel.removeRow(row);
    }

    public String getIdOfSelectedDocument() {
        int row = documentTypesTable.getSelectedRow();

        String id = null;
        if(row > -1 && row < documentTypesTable.getRowCount())
            id = tableModel.getValueAt(row, 0).toString();

        return id;
    }


    public void setVisibleErrorArea(boolean flag) {
        errorArea.setVisible(flag);
    }

    public void setErrorArea(String message) {
        errorArea.setText(message);
    }

    public void addSubmitButtonListener(ActionListener e) {
        submitButton.addActionListener(e);
    }

    public void addDeleteButtonListener(ActionListener e) {
        deleteButton.addActionListener(e);
    }
}
