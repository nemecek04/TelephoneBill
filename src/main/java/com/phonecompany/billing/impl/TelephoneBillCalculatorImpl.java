package com.phonecompany.billing.impl;

import com.phonecompany.billing.TelephoneBillCalculator;
import com.phonecompany.billing.comparator.PromoPhoneNumberComparator;
import com.phonecompany.billing.dto.TelephoneRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TelephoneBillCalculatorImpl implements TelephoneBillCalculator {
    @Override
    public BigDecimal calculate(String phoneLog) {
        List<String> rows = Arrays.asList(phoneLog.split("\n"));
        List<TelephoneRecord> records = rows.stream().map(TelephoneRecord::new).collect(Collectors.toList());

        List<TelephoneRecord> recordsWithoutPromo = removePromoPhoneNumber(records);

        Long price = 0L;
        for (TelephoneRecord r : recordsWithoutPromo) {
            price += r.getPrice();
        }

        return BigDecimal.valueOf(price / 100.0);
    }

    private List<TelephoneRecord> removePromoPhoneNumber(List<TelephoneRecord> records) {
        Map<Long, List<TelephoneRecord>> groupedRecords = records.stream().collect(Collectors.groupingBy(TelephoneRecord::getPhone));

        List<List<TelephoneRecord>> sortedRecords = new ArrayList<>(groupedRecords.values());
        sortedRecords.sort(new PromoPhoneNumberComparator().reversed());
        sortedRecords.remove(0);

        return sortedRecords.stream().flatMap(List::stream).collect(Collectors.toList());
    }

}
