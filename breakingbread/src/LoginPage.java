import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame{

    public LoginPage() {
        JLabel nameLabel = new JLabel("Enter username: ");
        JLabel passwordLabel = new JLabel("Enter password: ");
        JTextField nameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign up");
        JLabel errorLabel = new JLabel("Username or password is incorrect");
        JPanel panel = new JPanel(new GridBagLayout());

        loginButton.addActionListener(e -> {
            if(!Application.login(nameField.getText(), new String(passwordField.getPassword()))){
                errorLabel.setVisible(true);
                pack();
            }
            else{
                setVisible(false);
                dispose();
            }
        });

        signUpButton.addActionListener(e ->{
            new SignUpPage();
            setVisible(false);
            dispose();
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(nameLabel, constraints);

        constraints.gridx = 1;
        panel.add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);



        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(errorLabel, constraints);

        constraints.gridy = 3;
        panel.add(loginButton, constraints);

        constraints.gridy = 4;
        panel.add(signUpButton, constraints);

        // add the panel to this frame
        add(panel);

        setTitle("Login Page");
        pack();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}