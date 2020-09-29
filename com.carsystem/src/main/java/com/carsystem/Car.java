package com.carsystem;

public class Car {
    // 車號
    private String plateNumber;

    private int length;
    private int width;

    private VehicleType vehicleType;

    public Car(String plateNumber, int length, int width) throws CarException {

        this.plateNumber = plateNumber;
        this.length = length;
        this.width = width;

        if ((100 <= length && length <= 110) && (70 <= width && width <= 90)) {
            vehicleType = VehicleType.SMALL_CAR;
            if (this.plateNumber.length() != 8) {
                throw new CarException("輸入不為小車車號");
            }
        } else if ((115 <= length && length <= 130) && (100 <= width && width <= 110)) {
            vehicleType = VehicleType.BIG_CAR;
            if (this.plateNumber.length() != 6) {
                throw new CarException("輸入不為大車車號");
            }
        } else {
            throw new CarException("尺寸錯誤");
        }
    }

    public void printInfomation() {
        System.out.println("車號：" + this.plateNumber);
        System.out.println("長：" + this.length);
        System.out.println("寬：" + this.width);
        System.out.println("vehicleType：" + this.vehicleType);
    }
}
