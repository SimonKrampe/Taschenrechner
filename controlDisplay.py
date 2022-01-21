import time
import RGB1602
import sys
colorR = 64
colorG = 128
colorB = 64

rgb1 = (148,0,110)
line1 = sys.argv[1]
line2 = sys.argv[2]

print("Printring to Display...")

lcd=RGB1602.RGB1602(16,2)

rgb8 = (255,0,0) #Red

lcd.setCursor(0, 0)
lcd.printout(line1)

lcd.setCursor(0, 1)
lcd.printout(line2)
  
lcd.setRGB(rgb8[0],rgb8[1],rgb8[2]);
