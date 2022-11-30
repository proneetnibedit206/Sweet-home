package com.upgrad.bookingservice.constants;

import java.util.ArrayList;
import java.util.HashMap;

public class PaymentModes {

    static  ArrayList<String> paymentModeList = new ArrayList<>();
    static {
        paymentModeList.add("UPI");
        paymentModeList.add("CARD");
    }
    public static ArrayList<String> getPaymentModeList(){
        return paymentModeList;
    }
}
