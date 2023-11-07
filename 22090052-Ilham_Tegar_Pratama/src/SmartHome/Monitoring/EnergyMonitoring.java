/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartHome.Monitoring;

import com.pi4j.gpio.extension.base.AdcGpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008Pin;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class EnergyMonitoring {
    public static void main(String[] args) {
        // Inisialisasi MCP3008 sebagai pemasok GPIO ADC
        MCP3008GpioProvider gpioProvider = new MCP3008GpioProvider(RaspiPin.GPIO_00);

        // Inisialisasi pin input analog (gunakan pin sesuai kebutuhan)
        GpioPinAnalogInput currentInput = GpioFactory.getInstance().provisionAnalogInputPin(gpioProvider, MCP3008Pin.CH0, "CurrentInput");
        GpioPinAnalogInput voltageInput = GpioFactory.getInstance().provisionAnalogInputPin(gpioProvider, MCP3008Pin.CH1, "VoltageInput");

        // Konfigurasi faktor koreksi dan parameter pengukuran
        double currentCorrectionFactor = 30.0; // Faktor koreksi arus
        double voltageCorrectionFactor = 230.0; // Faktor koreksi tegangan
        int measurementTime = 10000; // Waktu pengukuran dalam milidetik (contoh: 10 detik)

        // Mengukur daya listrik selama waktu tertentu
        long startTime = System.currentTimeMillis();
        double totalPower = 0.0;
        int sampleCount = 0;

        while (System.currentTimeMillis() - startTime < measurementTime) {
            double currentADCValue = currentInput.getValue();
            double voltageADCValue = voltageInput.getValue();
            
            double current = (currentADCValue * 3.3) / currentCorrectionFactor;
            double voltage = (voltageADCValue * 3.3) / voltageCorrectionFactor;

            double power = current * voltage;
            totalPower += power;
            sampleCount++;
        }

        double averagePower = totalPower / sampleCount;

        System.out.println("Konsumsi energi rata-rata: " + averagePower + " Watt");

        // Membersihkan sumber daya
        currentInput.removeAllListeners();
        voltageInput.removeAllListeners();
        gpioProvider.shutdown();
        GpioFactory.getInstance().shutdown();
    }
}
