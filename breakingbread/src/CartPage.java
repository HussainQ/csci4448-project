import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CartPage extends Page {
    private JPanel changePanel = new JPanel(new GridBagLayout());
    private JButton changeButton = new JButton();
    private JTextField changeField = new JTextField(4);
    private void change(Customer user, Product prod){
        Product product = prod.clone();
        changePanel.setVisible(true);
        changeButton.setText(String.format("Change %s Quantity", product.getName()));
        ActionListener changeQuantity = e -> {
            try {
                int newQuantity = Integer.parseInt(changeField.getText());

                if (newQuantity > 0){
                    if (newQuantity > Inventory.getInstance().getProduct(product.getId()).getQuantity()){
                        newQuantity = Inventory.getInstance().getProduct(product.getId()).getQuantity();
                    }
                    user.removeItem(product.getId());
                    product.setQuantity(newQuantity);
                    user.addItem(product);
                    results(user);
                }
                else if(newQuantity == 0){
                    user.removeItem(product.getId());
                    results(user);
                }
            } catch (Exception ex) {
                changeField.setText(String.format("%d",product.getQuantity()));
            }
        };
        changeField.addActionListener(changeQuantity);
        changeButton.addActionListener(changeQuantity);
        pack();

    }

    private JPanel resultsPanel = new JPanel(new GridLayout(0,3));
    private JLabel totalLabel, totalLabel1;
    private void results(Customer user) {
        totalLabel.setText(String.format("%d",user.order.getSize()));
        totalLabel1.setText(String.format("$%.2f",user.order.getCost()));
        resultsPanel.removeAll();
        user.getCart().getItems().forEach(product -> {
            JButton name = new JButton(product.getName());
            name.addActionListener(e->change(user, product));
            resultsPanel.add(name);
            resultsPanel.add(new JLabel(String.format("%d",product.getQuantity()),SwingConstants.CENTER));
            resultsPanel.add(new JLabel(String.format("$%.2f",product.getQuantity()*product.getDiscountPrice()),SwingConstants.RIGHT));
        });
        pack();
    }

    public CartPage(Customer user) {
        super();
        header(user);
        home(user);

        changePanel.setVisible(false);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        changePanel.add(changeButton,constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        changePanel.add(changeField,constraints);

        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0,10,0,10);
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        pane.add(changePanel, constraints);

        JPanel cartPanel = new JPanel(new GridLayout(0,3));
        cartPanel.add(new JLabel("Total"));
        totalLabel = new JLabel(String.format("%d",user.order.getSize()),SwingConstants.CENTER);
        cartPanel.add(totalLabel);
        totalLabel1 = new JLabel(String.format("$%.2f",user.order.getCost()),SwingConstants.RIGHT);
        cartPanel.add(totalLabel1);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        pane.add(cartPanel, constraints);

        JButton checkout = new JButton("Checkout");
        checkout.addActionListener(e -> {
            user.checkout();
            new MainPage(user);
            close();
        });
        constraints.gridy = 4;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        pane.add(checkout, constraints);

        results(user);
        JScrollPane scroll = new JScrollPane(resultsPanel);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        pane.add(scroll, constraints);

        display();
    }
}
