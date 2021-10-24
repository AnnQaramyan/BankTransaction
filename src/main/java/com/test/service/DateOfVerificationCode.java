package com.test.service;

import com.test.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateOfVerificationCode {
    private static final long TWO_HOURS = 1000 * 60 * 60 * 2;

    public boolean dateOfVfCode(User user) {

        Date date = new Date();
        long currentDateInMills = date.getTime();
        long verificationCodeDateInMills = user.getVerificationCodeDate().getTime();

        long timeDifferenceInMills = currentDateInMills - verificationCodeDateInMills;

        if (timeDifferenceInMills > TWO_HOURS) {
            return false;
        }
        return true;
    }
}
