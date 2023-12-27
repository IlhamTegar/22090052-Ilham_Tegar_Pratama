/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control_lamp;

public class LampControl {
    private ConnectArduino connectArduino;

    public LampControl(ConnectArduino connectArduino) {
        this.connectArduino = connectArduino;
    }

    public void turnOn() {
        connectArduino.sendData("1");
    }

    public void turnOff() {
        connectArduino.sendData("0");
    }
}
