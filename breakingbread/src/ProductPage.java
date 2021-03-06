import javax.swing.*;
import java.awt.*;

public class ProductPage extends Page{
    public ProductPage(Customer user, Product prod) {
        super();
        header(user);
        home(user);

        Product product = prod.clone();
        JLabel nameLabel = new JLabel(product.getName());
        JLabel descriptionLabel = new JLabel(product.getDescription());
        JLabel quantityLabel = new JLabel("Quantity: ");
        JLabel stockLabel = new JLabel(String.format("Stock: %d", product.getQuantity()));
        JLabel priceLabel = new JLabel(String.format("Price: $%.2f", product.getDiscountPrice()));
        JTextField quantityField = new JTextField("1",5);

        ImageIcon thumbnail = new ImageIcon(getClass().getResource(product.getPath()));
        JLabel imageLabel = new JLabel(thumbnail);
        imageLabel.setMaximumSize(new Dimension(100,100));

        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(e -> {
            try {
                int newQuantity = Integer.parseInt(quantityField.getText());

                if (newQuantity >  0 && newQuantity <= Inventory.getInstance().getProduct(product.getId()).getQuantity()){
                    user.removeItem(product.getId());
                    product.setQuantity(newQuantity);
                    user.addItem(product);
                    new CartPage(user);
                    close();
                }
            } catch (NumberFormatException ex) {
                quantityField.setText(String.format("%d",1));
            }

        });

        JPanel addPanel = new JPanel(new GridBagLayout());

        constraints.insets = new Insets(10,10,10,10);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        pane.add(descriptionLabel, constraints);

        constraints.gridy = 1;
        constraints.insets = new Insets(10,0,10,0);
        pane.add(addPanel,constraints);

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(0,10,0,10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        addPanel.add(nameLabel, constraints);

        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.weightx = 0;
        addPanel.add(stockLabel, constraints);

        constraints.gridx = 2;
        addPanel.add(priceLabel, constraints);

        constraints.gridx = 3;
        addPanel.add(quantityLabel, constraints);

        constraints.gridx = 4;
        addPanel.add(quantityField, constraints);

        constraints.gridx = 5;
        addPanel.add(addButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        pane.add(imageLabel, constraints);

        display();
    }
}
