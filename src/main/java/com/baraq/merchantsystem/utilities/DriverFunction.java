package com.baraq.merchantsystem.utilities;

public class DriverFunction {
    public static void main(String[] args) {
        String paddedRandomNumber = CIFUtils.generateAndPadRandomNumber(100000000);

        //get current time stamp and convert it to str of length 16
        String timestamp = CIFUtils.timeStamp();

        //calculate checkdigit for timestamp
        String checkDigit = CIFUtils.checkdigitCalculator_onedigit(timestamp);

        //generate cif
        String CIF = paddedRandomNumber + checkDigit;

        //shuffle cif with pre defined numbers
        String shuffled_cif = CifShuffler.cifShuffler(CIF);
        System.out.println(shuffled_cif);
    }
}
