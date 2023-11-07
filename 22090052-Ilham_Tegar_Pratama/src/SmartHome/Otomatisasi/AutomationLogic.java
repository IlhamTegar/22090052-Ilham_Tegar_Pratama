/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartHome.Otomatisasi;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class AutomationLogic {
    public static void main(String[] args) throws InterruptedException {
        // Inisialisasi GpioController
        GpioController gpio = GpioFactory.getInstance();

        // Inisialisasi pin input untuk sensor gerakan
        GpioPinDigitalInput motionSensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
        motionSensor.setShutdownOptions(true);

        // Inisialisasi pin output untuk lampu
        GpioPinDigitalOutput light = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Light", PinState.LOW);
        light.setShutdownOptions(true, PinState.LOW);

        // Menambahkan listener untuk mendeteksi perubahan status sensor gerakan
        motionSensor.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState() == PinState.HIGH) {
                    // Sensor gerakan mendeteksi gerakan
                    System.out.println("Gerakan terdeteksi. Menyalakan lampu.");
                    light.high(); // Hidupkan lampu
                } else {
                    // Tidak ada gerakan, matikan lampu setelah beberapa waktu
                    System.out.println("Tidak ada gerakan. Matikan lampu.");
                    light.low(); // Matikan lampu
                }
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
