/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control_lamp;

import com.fazecast.jSerialComm.SerialPort;

public class ConnectArduino {
    private SerialPort serialPort;

    public void connect(String portName) {
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(9600);

        if (serialPort.openPort()) {
            System.out.println("Port is open.");
        } else {
            System.out.println("Unable to open the port.");
        }
    }

    public void disconnect() {
        if (serialPort != null) {
            serialPort.closePort();
        }
    }

    public void sendData(String data) {
        if (serialPort != null) {
            serialPort.writeBytes(data.getBytes(), data.length());
        }
    }
}
