package com.carsystem;

import java.util.List;

public class main {
    public static void main(String[] args) {
        List<String> plateNumbers = PlateNumberFactory.generatePlateNumber(400);

        Thread t1 = new CreateCarThread(0, plateNumbers);
        Thread t2 = new CreateCarThread(1, plateNumbers);
        t1.start();
        t2.start();
    }

}

class CreateCarThread extends Thread {
    private int type;
    private List<String> plateNumbers;

    public CreateCarThread(int type, List<String> plateNumbers) {
        this.type = type;
        this.plateNumbers = plateNumbers;
    }

    @Override
    public void run() {
        int createCount = 0;

        try {
            for (int i = 0; i < plateNumbers.size(); i++) {
                String plateNumber = plateNumbers.get(i);
                int pnLen = plateNumber.length();
                // 大車
                if (type == 0 && pnLen == 6) {

                    Car bigCar = new Car(plateNumber, (int) (Math.random() * 16) + 115,
                            (int) (Math.random() * 11) + 100);
                    createCount++;
                    System.out.println("大車" + createCount + ":" + plateNumber);
                }
                // 小車
                else if (type == 1 && pnLen == 8) {
                    Car smallCar = new Car(plateNumber, (int) (Math.random() * 11) + 100,
                            (int) (Math.random() * 21) + 70);
                    createCount++;
                    System.out.println("小車" + createCount + ":" + plateNumber);
                }
                if (createCount == 100) {
                    break;
                }
            }
            if (createCount < 100) {
                System.out.println((type == 0 ? "大車" : "小車") + "車牌數量不足:" + createCount);
                //throw new CarException((type == 0 ? "大車" : "小車") + "車牌數量不足:" + createCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
