import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPage extends JFrame{
    public MainPage(User user) {
        //Overall layout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Pastels
        /*Color orange = new Color(255,179,71);
        Color red = new Color(255,87,71);
        getContentPane().setBackground(orange);*/

        //search Bar
        JButton searchButton = new JButton("Search");
        JTextField searchField = new JTextField(20);
        JPanel searchPanel = new JPanel(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0,10,0,10);
        constraints.anchor = GridBagConstraints.WEST;
        searchPanel.add(searchField,constraints);

        constraints.gridx = 1;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.EAST;
        searchPanel.add(searchButton,constraints);


        //search Functionality
        ActionListener searchFunction = e -> {
            Application.results(searchField.getText());
            setVisible(false);
            dispose();
        };

        searchButton.addActionListener(searchFunction);
        searchField.addActionListener(searchFunction);

        //Header bar
        JPanel headerPanel = new JPanel(new GridBagLayout());

        JLabel userLabel = new JLabel("Welcome " + user.getEmail());
        JButton signoutButton = new JButton("Sign Out");

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 10, 0, 10);

        headerPanel.add(userLabel, constraints);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.EAST;
        headerPanel.add(signoutButton, constraints);

        signoutButton.addActionListener(e -> {
            Application.signout();
            setVisible(false);
            dispose();
        });

        // add components to the content pane
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);
        //headerPanel.setBackground(red);
        add(headerPanel, constraints);


        constraints.gridy++;
        constraints.weighty = 1.0;
        //searchPanel.setBackground(orange);
        add(searchPanel, constraints);

        setSize(5000,5000);
        pack();
        setTitle("Breaking Bread");
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }
}
