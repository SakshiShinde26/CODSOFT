package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

class AddressBook extends JFrame {
    private ArrayList<Contact> contacts;
    private JTextArea displayArea;

    public AddressBook() {
        setTitle("Address Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        contacts = new ArrayList<>();

        JLabel infoLabel = new JLabel("Address Book System");
        infoLabel.setBounds(120, 10, 200, 20);
        add(infoLabel);

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(20, 40, 360, 150);
        add(scrollPane);

        JButton addContactButton = new JButton("Add Contact");
        addContactButton.setBounds(20, 200, 120, 30);
        add(addContactButton);

        JButton displayContactsButton = new JButton("Display Contacts");
        displayContactsButton.setBounds(150, 200, 150, 30);
        add(displayContactsButton);

        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter contact's name:");
                String phoneNumber = JOptionPane.showInputDialog("Enter contact's phone number:");
                String emailAddress = JOptionPane.showInputDialog("Enter contact's email address:");

                if (name != null && phoneNumber != null && emailAddress != null &&
                        !name.isEmpty() && !phoneNumber.isEmpty() && !emailAddress.isEmpty()) {
                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    contacts.add(newContact);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                }
            }
        });

        displayContactsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayContacts();
            }
        });
    }

    private void displayContacts() {
        if (contacts.isEmpty()) {
            displayArea.setText("No contacts added yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Contact contact : contacts) {
                sb.append("Name: ").append(contact.getName())
                        .append(", Phone: ").append(contact.getPhoneNumber())
                        .append(", Email: ").append(contact.getEmailAddress())
                        .append("\n");
            }
            displayArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AddressBook addressBook = new AddressBook();
                addressBook.setVisible(true);
            }
        });
    }
}
