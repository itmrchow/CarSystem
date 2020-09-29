package com.carsystem;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.CoreMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Assert;
import org.junit.Test;

public class PlateNumberFactoryTest {
    @Test
    public void testGeneratePlateNumber() {
        // 3A原則
        // 1. Arrange 安排
        final int countOfPlateNumber = 5;

        // 2. Act 行動
        final List<String> plateNumberList = PlateNumberFactory.generatePlateNumber(countOfPlateNumber);

        // 3. Assert 斷言
        final LinkedHashSet<String> hashSet = new LinkedHashSet<>(plateNumberList);
        Assert.assertEquals("車牌不重複", hashSet.size(), plateNumberList.size());

        Map<String, String> big = new HashMap<String, String>();
        Map<String, String> small = new HashMap<String, String>();

        for (final String plateNumber : plateNumberList) {

            assertThat("長度不為6or7", plateNumber.length(), CoreMatchers.anyOf(CoreMatchers.is(6), CoreMatchers.is(8)));
            assertThat("不含有66", plateNumber, CoreMatchers.not(CoreMatchers.containsString("66")));

            String[] pnInfo = plateNumber.split("-");
            String f = pnInfo[0];
            String b = pnInfo[1];
            // 大車
            if (plateNumber.length() == 6) {
                // 後面是數字
                if (isNumeric(b)) {
                    assertThat("大車數字重複(後)", big.keySet(), CoreMatchers.not(CoreMatchers.hasItem(b)));
                }
                // 前面是數字
                else {
                    assertThat("大車數字重複(前)", big.values(), CoreMatchers.not(CoreMatchers.hasItem(f)));
                }
                big.put(b, f);

            }
            // 小車
            if (plateNumber.length() == 7) {
                // 英文部分不能重複 f為key
                // 前面是英文
                if (!isNumeric(f)) {
                    assertThat("小車英文重複(前)", small.keySet(), CoreMatchers.not(CoreMatchers.hasItem(f)));
                }
                // 後面是英文
                else {
                    assertThat("小車英文重複(後)", small.values(), CoreMatchers.not(CoreMatchers.hasItem(b)));
                }
                big.put(f, b);
            }
        }
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
