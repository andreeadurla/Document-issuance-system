package view.viewUser;

import view.Style;
import dto.HouseDto;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.UUID;


import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class LocationView extends JPanel{

    private JFrame frame;
    private JTable locationsTable;
    private JButton addLocationButton = new JButton("+ add address");
    private JButton deleteLocationButton = new JButton("- delete address");

    //Add Form
    private JTextField countyField = new JTextField();
    private JTextField cityField = new JTextField();
    private JTextField addressField = new JTextField();
    private JTextArea errorArea = new JTextArea();
    private JButton submitButton = new JButton("Save");
    private JDialog addForm;

    private DefaultTableModel tableModel = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public LocationView(JFrame frame) {
        this.frame = frame;

        //Add Tabel to the main panel
        this.add(createTablePanel());

        //Create a panel with add button and delete button
        JPanel buttonsPanel = new JPanel();
        Style.styleButton(addLocationButton, 150, 30, new Font("Arial", Font.BOLD, 13));
        buttonsPanel.add(addLocationButton);

        Style.styleButton(deleteLocationButton, 150, 30, new Font("Arial", Font.BOLD, 13));
        buttonsPanel.add(deleteLocationButton);

        //Add ButtonsPanel to the main panel
        this.add(buttonsPanel);
    }

    private JScrollPane createTablePanel() {
        tableModel.addColumn("ID");
        tableModel.addColumn("County");
        tableModel.addColumn("City");
        tableModel.addColumn("Adresa");

        locationsTable = new JTable(tableModel);
        locationsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        locationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Collumns
        locationsTable.getColumnModel().getColumn(0).setMinWidth(0);
        locationsTable.getColumnModel().getColumn(0).setMaxWidth(0);
        locationsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        locationsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        locationsTable.getColumnModel().getColumn(3).setPreferredWidth(450);

        locationsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        locationsTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(locationsTable);
        scrollPane.setPreferredSize(new Dimension(753, 250));

        return scrollPane;
    }

    public void insertInTable(HouseDto houseDto) {
        tableModel.addRow(new Object[] {houseDto.getId(), houseDto.getCounty(), houseDto.getCity(), houseDto.getAddress()});
    }

    public void createAddForm() {
        addForm = new JDialog(frame, "Add Location", true);
        //Style JDialog
        addForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addForm.setPreferredSize(new Dimension(500, 550));
        addForm.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Title
        c.insets.set(5, 10, 60,10);
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2; c.gridx = 0; c.gridy = 0;
        JLabel label = new JLabel("Add Location");
        Style.styleTitle(label, new Font("Arial", Font.ITALIC, 25));
        panel.add(label, c);

        //Labels + Fields
        c.insets.set(5, 10, 10,10);
        c.anchor = GridBagConstraints.LINE_START;

        //County
        c.gridwidth = 1; c.gridx = 0; c.gridy = 1;
        panel.add(new JLabel("County"), c);

        c.gridx = 0; c.gridy = 2;
        Style.styleTextField(countyField, 150, 30, new Font("Arial", Font.BOLD, 15));
        panel.add(countyField, c);

        //Localiatate
        c.gridx = 1; c.gridy = 1;
        panel.add(new JLabel("City"), c);

        c.gridx = 1; c.gridy = 2;
        Style.styleTextField(cityField, 150, 30, new Font("Arial", Font.BOLD, 15));
        panel.add(cityField, c);

        //Address
        c.gridwidth = 2;
        c.gridx = 0; c.gridy = 3;
        panel.add(new JLabel("Address"), c);

        c.gridx = 0; c.gridy = 4;
        Style.styleTextField(addressField, 320, 30, new Font("Arial", Font.BOLD, 15));
        panel.add(addressField, c);

        //Error area
        c.gridx = 0; c.gridy = 5;
        Style.styleErrorArea(errorArea, 28);
        panel.add(errorArea, c);

        //SubmitButton
        c.insets.set(30, 10, 10, 10);
        c.gridx = 0; c.gridy = 6;
        Style.styleButton(submitButton, 320, 30, new Font("Arial", Font.BOLD, 15));
        panel.add(submitButton, c);

        addForm.add(panel);
        addForm.pack();
        addForm.setVisible(true);
    }

    public HouseDto getHouseInfos() {
        HouseDto houseDto = new HouseDto();

        String id = UUID.randomUUID().toString();
        houseDto.setId(id);
        houseDto.setCounty(countyField.getText());
        houseDto.setCity(cityField.getText());
        houseDto.setAddress(addressField.getText());

        return houseDto;
    }

    public void setEmptyTextFields() {
        countyField.setText("");
        cityField.setText("");
        addressField.setText("");
    }

    public void closeAddForm() {
        addForm.dispose();
    }

    public String getIdOfSelectedHouse() {
        int row = locationsTable.getSelectedRow();

        String id = null;

        if(row > -1 && row < locationsTable.getRowCount())
            id = tableModel.getValueAt(row, 0).toString();

        return id;
    }

    public void deleteSelectedHouse() {
        int row = locationsTable.getSelectedRow();

        if(row > -1) {
            tableModel.removeRow(row);
        }
    }

    public void addCreateAddFormButtonListener(ActionListener e) {
        addLocationButton.addActionListener(e);
    }

    public void addDeleteButtonListener(ActionListener e) {
        deleteLocationButton.addActionListener(e);
    }

    public void addSubmitButtonListener(ActionListener e) {
        submitButton.addActionListener(e);
    }

    public void setVisibleErrorArea(boolean flag) {
        errorArea.setVisible(flag);
    }

    public void setErrorArea(String message) {
        errorArea.setText(message);
    }
}
