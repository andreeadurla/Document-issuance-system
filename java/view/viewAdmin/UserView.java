package view.viewAdmin;

import dto.UserDisplayDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserView extends JPanel{

    private JTable usersTable;

    private DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public UserView() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0; c.gridy = 0;
        this.add(createTablePanel(), c);
    }

    private JScrollPane createTablePanel() {
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");
        tableModel.addColumn("CNP");

        usersTable = new JTable(tableModel);
        usersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Collumns
        usersTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        usersTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        usersTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        usersTable.getColumnModel().getColumn(3).setPreferredWidth(150);

        usersTable.setFont(new Font("Arial", Font.PLAIN, 14));
        usersTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        scrollPane.setPreferredSize(new Dimension(602, 250));

        return scrollPane;
    }

    public void insertInTable(UserDisplayDto user) {
        tableModel.addRow(new Object[] {user.getName(), user.getEmail(), user.getPhone(), user.getCnp()});
    }
}
