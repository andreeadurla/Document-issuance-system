package view.viewAdmin;

import dto.RequestDto;
import utils.ApplicationUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RequestTable extends JPanel{

    private JTable requestsTable;
    private DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public RequestTable() {
        createTable();
    }

    private void createTable() {
        tableModel.addColumn("ID");
        tableModel.addColumn("Status");
        tableModel.addColumn("Date");
        tableModel.addColumn("Name");
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
        requestsTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        requestsTable.getColumnModel().getColumn(5).setPreferredWidth(250);
        requestsTable.getColumnModel().getColumn(6).setPreferredWidth(300);

        requestsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        requestsTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(requestsTable);
        scrollPane.setPreferredSize(new Dimension(753, 250));

        this.add(scrollPane);
    }

    public void insertInTable(RequestDto requestDto) {
        tableModel.addRow(new Object[] {requestDto.getId(), requestDto.getStatus(), ApplicationUtils.printPrettyDate(requestDto.getDate()),
                requestDto.getUser(), requestDto.getDocument(), requestDto.getHouse(), requestDto.getPurpose()});
    }

    public int getSelectedRow() {
        int selectedRow = requestsTable.getSelectedRow();
        return selectedRow;
    }

    public String getIdOfSelectedRequest() {
        String id = null;
        int row = getSelectedRow();

        if (row > -1)
            id = tableModel.getValueAt(row, 0).toString();

        return id;
    }

    public void deselectTableRows() {
        requestsTable.getSelectionModel().clearSelection();
    }

    public void deleteSelectedRow() {
        int row = getSelectedRow();

        if(row > -1) {
            tableModel.removeRow(row);
        }
    }

    public void updateStatusOfSelectedRequest(String status) {

        int row = getSelectedRow();

        if(row > -1) {
            requestsTable.setValueAt(status, row, 1);
        }
    }

    public void clearTable() {
        int noRows = tableModel.getRowCount();
        for( int i = noRows - 1; i >= 0; i-- ) {
            tableModel.removeRow(i);
        }
    }

    //Display a form where we can view the request
    public void displaySelectedRow() {
        int row = requestsTable.getSelectedRow();

        if(row > -1) {
            DisplayRequestFormView form = new DisplayRequestFormView(null);

            form.setDateField(tableModel.getValueAt(row, 2). toString());
            form.setStatusField(tableModel.getValueAt(row, 1). toString());
            form.setDocumentTypeField(tableModel.getValueAt(row, 3). toString());
            form.setLocationArea(tableModel.getValueAt(row, 4). toString());
            form.setPurposeArea(tableModel.getValueAt(row, 5). toString());

            form.createForm();
        }
    }
}
