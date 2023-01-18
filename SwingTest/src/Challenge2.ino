#include <LSM303AGR_ACC_Sensor.h>
#include <Wire.h>
#include <Adafruit_Microbit.h>
#define DEV_I2C Wire1  // Wire1 is voor de interne I2C bus van de Microbit V2

Adafruit_Microbit_Matrix display;
LSM303AGR_ACC_Sensor Acc(&DEV_I2C);


int32_t accelerometer[3] = { 0, 0, 0 };
uint32_t oude_data[3];
uint32_t loopcounter = 0;

uint32_t timer;
uint32_t tijd_van_laatste_reminder;
uint32_t interval = 60000;
bool reminder = false;

void updateAcc();
//bool check();

bool check(int32_t verschillen[3]) {
  if (verschillen[0] > 400) {
    //Serial.println("Positief 0");
    return true;
  }
  if (verschillen[1] > 400) {
    //Serial.println("Positief 1");
    return true;
  }
  if (verschillen[2] > 400) {
    //Serial.println("Positief 2");
    return true;
  }
  if (verschillen[0] < -400) {
    //Serial.println("Negatief 0");
    return true;
  }
  if (verschillen[1] < -400) {
    //Serial.println("Negatief 1");
    return true;
  }
  if (verschillen[2] < -400) {
    //Serial.println("Negatief 2");
    return true;
  }
  return false;
}

void setup() {
  DEV_I2C.begin();
  Acc.begin();
  Acc.Enable();
  display.begin();
  Serial.begin(115200);
  pinMode(27, OUTPUT);

}

void loop() {
  oude_data[0] = accelerometer[0];
  oude_data[1] = accelerometer[1];
  oude_data[2] = accelerometer[2];
  delay(990);
  updateAcc();
  int32_t verschillen[3];
  verschillen[0] = accelerometer[0] - oude_data[0];
  verschillen[1] = accelerometer[1] - oude_data[1];
  verschillen[2] = accelerometer[2] - oude_data[2];

  timer = millis();
  if (tijd_van_laatste_reminder + interval < timer) {
    display.drawPixel(3, 3, 255);
    reminder = true;
    tijd_van_laatste_reminder = timer;
    analogWrite(27, 255);
    delay(100);
    digitalWrite(27, LOW);
    display.clear();
  }

  if (reminder == true) {
    if (digitalRead(PIN_BUTTON_A)) {
      reminder = false;
      tijd_van_laatste_reminder = timer;
      display.clear();
    }
  }

  if (check(verschillen)) {
    loopcounter++;
  }

  Serial.println(loopcounter);
}


void updateAcc() {
  Acc.GetAxes(accelerometer);
}