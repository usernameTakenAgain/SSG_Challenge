package SwingTest.src;

import com.fazecast.jSerialComm.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class SerialClass {

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(SerialPort.getCommPorts()));
        SerialPort comPort = null;
        try {
            comPort = SerialPort.getCommPort("/dev/ttyACM0");
        } catch (SerialPortInvalidPortException e){
            System.out.println(e);
            try {
                comPort = SerialPort.getCommPort("/dev/ACM1");
            } catch (SerialPortInvalidPortException e2) {
                comPort = SerialPort.getCommPorts()[1];
            }
        }

        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        InputStream in = comPort.getInputStream();
        OutputStream out = comPort.getOutputStream();
        

        while(true) {
            /*int input = in.read();
            String inputString = Character.toString(input);
            System.out.println(inputString);*/
            boolean b = false;
            StringBuilder inputString = new StringBuilder();
            while (!b){
                int input = (char) in.read();
                String cinput = Character.toString(input);
                if (cinput.equals("#")){
                    break;
                } else {
                    inputString.append(cinput);
                }

            }
            System.out.println(inputString);
        }
        //comPort.closePort();
    } 
    public SerialPort init() {
        // Selecteer de juiste comport voor de microbit
        // Het object van de klasse SerialPort dat je terugkrijgt moet je meegeven aan andere functies
        SerialPort comPort = null;
        if (getOperatingSystem().equals("Linux")) {
            System.out.println(Arrays.toString(SerialPort.getCommPorts()));
            comPort = null;
            try {
                comPort = SerialPort.getCommPort("/dev/ttyACM0");
            } catch (SerialPortInvalidPortException e) {
                System.out.println(e);
                try {
                    comPort = SerialPort.getCommPort("/dev/ACM1");
                } catch (SerialPortInvalidPortException e2) {
                    comPort = SerialPort.getCommPorts()[1];
                }
            }
            return comPort;
        } else {
            // Oke hier zitten we dus in een Windows systeem
            try {
                comPort = SerialPort.getCommPorts()[0];
            } catch (SerialPortInvalidPortException e) {
                System.out.println(e);
            }
            return comPort;
        }
    }


    public int leesGegevens(SerialPort comport) throws IOException {
        // Lees eerst eerste gegevens als int
        // Daarna pas tweede, enzovoort
        comport.openPort();
        InputStream in = comport.getInputStream();
        int gelopencounter = 0;
        int buffersize = in.available();
        in.skipNBytes( buffersize -1);
        if (in.available() != 0){
            gelopencounter = in.read();
        }
        comport.closePort();
        return gelopencounter + 1;

    }
    public int leesGegevensPogingTwee() throws IOException {
        SerialPort comPort = init();
        comPort.setBaudRate(115200);
        comPort.openPort();
        InputStream in = comPort.getInputStream();
        OutputStream out  = comPort.getOutputStream();
        return in.read();
    }
    private String getOperatingSystem() {
        return System.getProperty("os.name");
    }
}
