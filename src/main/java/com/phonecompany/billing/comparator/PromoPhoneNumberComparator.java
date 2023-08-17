package com.phonecompany.billing.comparator;

import com.phonecompany.billing.dto.TelephoneRecord;

import java.util.Comparator;
import java.util.List;

public class PromoPhoneNumberComparator implements Comparator<List<TelephoneRecord>> {
    @Override
    public int compare(List<TelephoneRecord> l1, List<TelephoneRecord> l2) {
        if (l1.size() < l2.size()) {
            return -1;
        } else if (l1.size() > l2.size()) {
            return 1;
        } else if (l1.get(0).getPhone() < l2.get(0).getPhone()) {
            return -1;
        } else {
            return 1;
        }
    }
}
