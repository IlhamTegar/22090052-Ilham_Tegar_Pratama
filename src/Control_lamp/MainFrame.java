/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control_lamp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private LampControl lampControl;
    private JLabel statusLabel;

    public MainFrame(LampControl lampControl) {
        this.lampControl = lampControl;

        JButton onButton = new JButton("Turn On");
        onButton.setBackground(Color.GREEN);
        onButton.setForeground(Color.WHITE);
        onButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lampControl.turnOn();
                setStatusLabel("Lampu Menyala");
            }
        });

        JButton offButton = new JButton("Turn Off");
        offButton.setBackground(Color.RED);
        offButton.setForeground(Color.WHITE);
        offButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lampControl.turnOff();
                setStatusLabel("Lampu Mati");
            }
        });

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(onButton);
        buttonPanel.add(offButton);

        // Label untuk menampilkan status
        statusLabel = new JLabel("Status: ");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        // Panel utama menggunakan GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        mainPanel.add(statusLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(buttonPanel, gbc);

        // Mengatur ukuran panel
        mainPanel.setPreferredSize(new Dimension(350, 250));

        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setStatusLabel(String status) {
        statusLabel.setText("Status: " + status);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConnectArduino connectArduino = new ConnectArduino();
            LampControl lampControl = new LampControl(connectArduino);

            connectArduino.connect("COM3"); // Ganti dengan nama port yang digunakan

            new MainFrame(lampControl);
        });
    }
}
