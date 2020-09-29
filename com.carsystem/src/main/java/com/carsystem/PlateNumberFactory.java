package com.carsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlateNumberFactory {
    public static List<String> generatePlateNumber(final int countOfPlateNumber) {
        final List<String> returnList = new ArrayList<>();

        final Map<String, String> small = new HashMap<String, String>();
        final Map<String, String> big = new HashMap<String, String>();

        // 迴圈
        int i = 0;
        while (i < countOfPlateNumber) {
            
            final int type = (int) (Math.random() * 2) + 0;
            // 產車牌
            Map<String, String> returnMap = createPlateNumber(type, type == 0 ? big : small);
            if(returnMap == null){
                continue;
            }
            if (type == 0) {
                big.putAll(returnMap);
            } else {
                small.putAll(returnMap);
            }
            i++;
        }

        // 放入對應list
        System.out.println("大車牌：" + big.size());
        System.out.println("小車牌：" + small.size());
        returnList.addAll(big.values());
        returnList.addAll(small.values());

        return returnList;
    }

    private static Map<String, String> createPlateNumber(int type, Map<String, String> savePN) {
        Map<String, String> returnMap ;
        final String eng = createStr(type);
        final String num = createNum(type);

        // 大車
        if (type == 0) {
            // 檢查數字不重複
            if (savePN.containsKey(num)) {
                // 捨棄
                return null;
            }

            // 檢查是否存在
            if ((eng + "-" + num).equals(savePN.get(num))) {
                // 檢查反轉
                if (savePN.containsKey(eng) && (num + "-" + eng).equals(savePN.get(eng))) {
                    returnMap = createPlateNumber(type, savePN);
                }
                // 放入反轉
                else {
                    returnMap = new HashMap<String,String>();
                    returnMap.put(eng, num + "-" + eng);
                }
            }
            // 不存在放入
            else {
                returnMap = new HashMap<String,String>();
                returnMap.put(num, eng + "-" + num);
            }
        }
        // 小車
        else {
            // 檢查英文字不重複
            if (savePN.containsKey(eng)) {
                return null;
            }

            // 檢查是否存在
            if ((eng + "-" + num).equals(savePN.get(eng))) {
                // 檢查反轉
                if (savePN.containsKey(num) && (num + "-" + eng).equals(savePN.get(num))) {
                    return null;
                }
                // 放入反轉
                else {
                    returnMap = new HashMap<String,String>();
                    returnMap.put(num, num + "-" + eng);
                }
            }
            // 不存在放入
            else {
                returnMap = new HashMap<String,String>();
                returnMap.put(eng, eng + "-" + num);
            }
        }

        return returnMap;
    }

    private static String createStr(final int type) {
        final String s1 = String.valueOf((char) ((int) (Math.random() * 26) + 65));
        final String s2 = String.valueOf((char) ((int) (Math.random() * 26) + 65));

        // 小車
        if (type == 0) {
            return s1 + s2;
        } else {
            final String s3 = String.valueOf((char) ((int) (Math.random() * 26) + 65));
            return s1 + s2 + s3;
        }
    }

    private static String createNum(final int type) {
        String returnStr = "";
        // 大車
        if (type == 0) {
            final int intN = (int) (Math.random() * 100);
            returnStr = String.format("%03d", intN);
        }
        // 小車
        else {
            final int intN = (int) (Math.random() * 10000);
            returnStr = String.format("%04d", intN);
            // 小車不含有4
            if (returnStr.contains("4")) {
                return createNum(type);
            }
        }

        // 檢查是否含有66
        if (returnStr.contains("66")) {
            return createNum(type);
        }
        return returnStr;
    }

    public static void main(final String[] args) {

        final List<String> pns = generatePlateNumber(5);
        for (int i = 0; i < pns.size(); i++) {
            System.out.print(pns + ":");
            System.out.println(pns.get(i));
        }
    }

}
