package com.phonecompany.billing.impl;

import com.phonecompany.billing.TelephoneBillCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TelephoneBillCalculatorTest {

    private final TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();

    @Test
    public void test1() {
        String log =
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" + //1.5
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00"; //is skipped as promo number

        BigDecimal price = calculator.calculate(log);

        Assertions.assertEquals(price, new BigDecimal("1.5"));
    }

    @Test
    public void test2() {
        String log =
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" + //skipped
                "420774577453,19-01-2020 15:58:19,19-01-2020 16:06:57\n" + //skipped
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00"; //6.2

        BigDecimal price = calculator.calculate(log);

        Assertions.assertEquals(price, new BigDecimal("6.2"));
    }

    @Test
    public void test3() {
        String log =
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" + //1.5
                "420774577453,19-01-2020 15:58:19,19-01-2020 16:06:57\n" + //4.3
                "420776562353,19-01-2020 04:50:20,19-01-2020 04:50:40\n" + //skipped
                "420776123128,23-01-2020 04:51:21,23-01-2020 04:51:39\n" + //0.5
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00"; //skipped

        BigDecimal price = calculator.calculate(log);

        Assertions.assertEquals(price, new BigDecimal("6.3"));
    }
}
