package Control_lamp;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        ConnectArduino connectArduino = new ConnectArduino();
        LampControl lampControl = new LampControl(connectArduino);

        connectArduino.connect("COM3"); // Ganti dengan nama port yang digunakan

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(lampControl);
            }
        });
    }
}
