/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi4j.io.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class GpioFactory {
    public static void main(String[] args) throws InterruptedException {
        // Membuat instance GpioController menggunakan GpioFactory
        GpioController gpio = GpioFactory.getInstance();

        // Inisialisasi pin GPIO sebagai output
        GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17, "MyLED", PinState.LOW);

        try {
            // Hidupkan lampu
            pin.high();
            System.out.println("Lampu dinyalakan");

            // Tunggu selama 5 detik
            Thread.sleep(5000);

            // Matikan lampu
            pin.low();
            System.out.println("Lampu dimatikan");
        } finally {
            // Matikan GPIO dan lepaskan sumber daya
            gpio.shutdown();
        }
    }
}
