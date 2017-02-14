#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_BNO055.h>
#include <utility/imumaths.h>

#define BNO055_SAMPLERATE_DELAY_MS (100)
#define ROBORIO_ADDRESS (0)

Adafruit_BNO055 bno = Adafruit_BNO055(55);
sensors_event_t event;

void setup(void)
{
  Serial.begin(9600);
  Serial.println("Program Started");

  Wire.begin(3786);

  Wire.onRequest(request_event);
  Wire.onReceive(receive_event);

  if(!bno.begin())
  {
    /* There was a problem detecting the BNO055 ... check your connections */
    Serial.print("Ooops, no BNO055 detected ... Check your wiring or I2C ADDR!");
    while(1);
  }

  Serial.println("Gyro Setup");
  delay(1000);

  bno.setExtCrystalUse(true);

}

void loop(void)
{ 
  bno.getEvent(&event);
  delay(BNO055_SAMPLERATE_DELAY_MS);
}

void request_event() 
{
  Serial.println("Request Event Triggered");
}

void receive_event(int buffer_size) 
{
  Serial.print("Recieve Event Triggered "); Serial.println(buffer_size);
  Serial.print("They want"); Serial.print(buffer_size); Serial.println("bytes");
  while(0 < Wire.available())
  {
    char c = Wire.read();
    //Serial.println(c);
    switch(c)
    {
      case 'x':
        Serial.println("They want X");
        transmit_x();
        break;
      case 'y':
        Serial.println("They want Y");
        transmit_y();
        break;
      case 'z':
        Serial.println("They want Z");
        transmit_z();
        break;
    }
  }
}

void transmit_x()
{
  Serial.println("Trying to transmit");
  Wire.write((char*)&event.orientation.x);
}

void transmit_y()
{
  Wire.write((char*)&event.orientation.y);
}

void transmit_z()
{
  Wire.write((char*)&event.orientation.z);
}