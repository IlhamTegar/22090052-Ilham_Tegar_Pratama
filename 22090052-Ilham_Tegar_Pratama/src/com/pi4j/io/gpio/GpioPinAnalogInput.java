/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi4j.io.gpio;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinAnalogValueChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerAnalog;

public class GpioPinAnalogInput {
    public static void main(String[] args) {
        // Inisialisasi GpioController
        GpioController gpio = GpioFactory.getInstance();

        // Inisialisasi pin input analog
        GpioPinAnalogInput analogInput = gpio.provisionAnalogInputPin(RaspiPin.GPIO_01, "MyAnalogInput");

        // Menambahkan listener untuk mendengarkan perubahan nilai analog
        analogInput.addListener(new GpioPinListenerAnalog() {
            @Override
            public void handleGpioPinAnalogValueChangeEvent(GpioPinAnalogValueChangeEvent event) {
                // Membaca nilai analog saat terjadi perubahan
                double nilaiADC = event.getValue();
                System.out.println("Nilai Analog: " + nilaiADC);
            }
        });

        // Menunggu hingga program dihentikan (Ctrl+C atau lainnya)
        try {
            System.out.println("Tekan Ctrl+C untuk menghentikan...");
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Matikan GPIO dan lepaskan sumber daya
            gpio.shutdown();
        }
    }
}
