/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Spotify.clone;

/**
 *
 * @author deeks
 */
import java.awt.*;
import javax.swing.*;

public class SpotifyUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel;

    public SpotifyUI() {
        setTitle("Spotify Login & Signup");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with card layout
        mainPanel = new JPanel(cardLayout);

        // Adding Login and Signup Panels
        JPanel loginPanel = createLoginPanel();
        JPanel signupPanel = createSignupPanel();

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(signupPanel, "Signup");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(25, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Load Spotify Icon
        ImageIcon originalIcon = new ImageIcon("src\\main\\Resources\\logo.png"); // Adjust path as needed
        Image img = originalIcon.getImage(); // Get the original image
        Image resizedImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize image
        ImageIcon icon = new ImageIcon(resizedImg); // Create a new ImageIcon with the resized image

        JLabel lblLogo = new JLabel("Spotify", icon, JLabel.CENTER);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 36));
        lblLogo.setForeground(new Color(30, 215, 96));
        lblLogo.setHorizontalTextPosition(JLabel.RIGHT); // Position text to the right of icon
        lblLogo.setIconTextGap(10); // Space between icon and text

        // Username Label and Field
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.WHITE);
        JTextField txtUsername = new JTextField(20);
        styleTextField(txtUsername);

        // Password Label and Field
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        JPasswordField txtPassword = new JPasswordField(20);
        styleTextField(txtPassword);

        // Checkbox for "Stay signed in"
        JCheckBox chkStaySignedIn = new JCheckBox("Stay signed in");
        chkStaySignedIn.setForeground(Color.WHITE);
        chkStaySignedIn.setOpaque(false);

        // Login Button
        JButton btnLogin = new JButton("Sign In");
        styleButton(btnLogin);

        // Signup Switch Button
        JButton btnSignupSwitch = new JButton("Sign Up");
        styleButton(btnSignupSwitch);

        // Login Action
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            if (SpotifyAuth.loginUser(username, password)) {
                setVisible(false);
                new Home().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials.");
            }
        });

        // Switch to Signup Panel
        btnSignupSwitch.addActionListener(e -> cardLayout.show(mainPanel, "Signup"));

        // Adding components to the panel with constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblLogo, gbc);

        gbc.gridy++;
        panel.add(lblUsername, gbc);

        gbc.gridy++;
        panel.add(txtUsername, gbc);

        gbc.gridy++;
        panel.add(lblPassword, gbc);

        gbc.gridy++;
        panel.add(txtPassword, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(chkStaySignedIn, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(btnLogin, gbc);

        gbc.gridx = 1;
        panel.add(btnSignupSwitch, gbc);

        return panel;
    }

    private JPanel createSignupPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(25, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Load Spotify Icon
        ImageIcon originalIcon = new ImageIcon("src\\main\\Resources\\logo.png"); // Adjust path as needed
        Image img = originalIcon.getImage(); // Get the original image
        Image resizedImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize image
        ImageIcon icon = new ImageIcon(resizedImg); // Create a new ImageIcon with the resized image
        
        JLabel lblLogo = new JLabel("Spotify", icon, JLabel.CENTER);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 36));
        lblLogo.setForeground(new Color(30, 215, 96));
        lblLogo.setHorizontalTextPosition(JLabel.RIGHT); // Position text to the right of icon
        lblLogo.setIconTextGap(10); // Space between icon and text

        // Username Label and Field
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.WHITE);
        JTextField txtUsername = new JTextField(20);
        styleTextField(txtUsername);

        // Email Label and Field
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.WHITE);
        JTextField txtEmail = new JTextField(20);
        styleTextField(txtEmail);

        // Password Label and Field
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        JPasswordField txtPassword = new JPasswordField(20);
        styleTextField(txtPassword);

        // Signup Button
        JButton btnSignup = new JButton("Sign Up");
        styleButton(btnSignup);

        // Login Switch Button
        JButton btnLoginSwitch = new JButton("Back to Login");
        styleButton(btnLoginSwitch);

        // Signup Action
        btnSignup.addActionListener(e -> {
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            if (SpotifyAuth.registerUser(username, email, password)) {
                JOptionPane.showMessageDialog(this, "Sign Up Successful! Please Log in.");
                cardLayout.show(mainPanel, "Login");
            } else {
                JOptionPane.showMessageDialog(this, "Sign Up Failed. Try Again.");
            }
        });

        // Switch to Login Panel
        btnLoginSwitch.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        // Adding components to the panel with constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblLogo, gbc);

        gbc.gridy++;
        panel.add(lblUsername, gbc);

        gbc.gridy++;
        panel.add(txtUsername, gbc);

        gbc.gridy++;
        panel.add(lblEmail, gbc);

        gbc.gridy++;
        panel.add(txtEmail, gbc);

        gbc.gridy++;
        panel.add(lblPassword, gbc);

        gbc.gridy++;
        panel.add(txtPassword, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(btnSignup, gbc);

        gbc.gridx = 1;
        panel.add(btnLoginSwitch, gbc);

        return panel;
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(new Color(40, 40, 40));
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(30, 215, 96));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }
public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SpotifyUI().setVisible(true);
            }
        });
}
}
