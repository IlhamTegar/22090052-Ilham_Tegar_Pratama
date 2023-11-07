/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartHome.Kontrol;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.Pin;

public class LightingControl {
    public static void main(String[] args) throws InterruptedException {
        // Inisialisasi GPIO controller
        final GpioController gpio = GpioFactory.getInstance();

        // Pilih nomor pin GPIO yang sesuai
        Pin pinLampu = RaspiPin.GPIO_17; // Ganti dengan nomor pin yang sesuai

        // Inisialisasi pin GPIO sebagai output
        final GpioPinDigitalOutput lampu = gpio.provisionDigitalOutputPin(pinLampu, "Lampu", PinState.LOW);

        try {
            while (true) {
                // Perintah Anda untuk mengontrol lampu
                System.out.print("Masukkan perintah (hidup/matikan/keluar): ");
                String perintah = System.console().readLine();

                if ("hidup".equalsIgnoreCase(perintah)) {
                    lampu.high();
                    System.out.println("Lampu dinyalakan");
                } else if ("matikan".equalsIgnoreCase(perintah)) {
                    lampu.low();
                    System.out.println("Lampu dimatikan");
                } else if ("keluar".equalsIgnoreCase(perintah)) {
                    break;
                } else {
                    System.out.println("Perintah tidak valid. Gunakan 'hidup' atau 'matikan'.");
                }
            }
        } finally {
            // Matikan lampu dan lepaskan sumber daya GPIO saat selesai
            lampu.low();
            gpio.shutdown();
        }
    }
}