import com.fazecast.jSerialComm.*;
import java.io.IOException;

public class execClass {
    public static void main() {
        SerialClass SerialClass;
        SerialClass a = new SerialClass();
        SerialPort port = a.init();

        int b;
        try {
            b = a.leesGegevens(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(b);
    }
}
