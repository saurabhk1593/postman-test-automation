package com.baraq.merchantsystem.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class CIFGenerator {

    private static final String output_path = "/Users/njoudalgifari/Documents/Projects/CIF/Data/Output/";
    private static final int df_len = 100000000;
    private static final int sample = 1000000;

    private static final Map<Integer, Integer> asci_dict = new HashMap<>();

    static {
        asci_dict.put(0, 48);
        asci_dict.put(1, 49);
        asci_dict.put(2, 50);
        asci_dict.put(3, 51);
        asci_dict.put(4, 52);
        asci_dict.put(5, 53);
        asci_dict.put(6, 54);
        asci_dict.put(7, 55);
        asci_dict.put(8, 56);
        asci_dict.put(9, 57);
    }

    private static String num(int num) {
        return String.format("%,d", num);
    }

    private static List<Integer> digitsOf(long n) {
        return String.valueOf(n).chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
    }

    private static int sumDigitList(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    private static long timestampID() {
        return System.currentTimeMillis() * 1000;
    }

    private static int checkdigitCalculatorOnedigit(String nid_cd) {
        List<Integer> oddDigits = new ArrayList<>();
        List<Integer> evenDigits = new ArrayList<>();

        for (int i = nid_cd.length() - 1; i >= 0; i -= 2) {
            oddDigits.add(Character.getNumericValue(nid_cd.charAt(i)));
        }

        for (int i = nid_cd.length() - 2; i >= 0; i -= 2) {
            evenDigits.add(Character.getNumericValue(nid_cd.charAt(i)));
        }

        int oddDigitsSum = sumDigitList(oddDigits);
        int oddDigitsSumMult = oddDigitsSum * 3;
        int evenDigitsSum = sumDigitList(evenDigits);
        int totalSum = oddDigitsSumMult + evenDigitsSum;
        int checkdigit = 10 - (totalSum % 10);

        if (String.valueOf(checkdigit).length() > 1) {
            checkdigit = sumDigitList(digitsOf(checkdigit));
        }

        return checkdigit;
    }

    private static String nidShuffler(long nid) {
        StringBuilder shuffled_nid = new StringBuilder();

        digitsOf(nid).forEach(num -> shuffled_nid.append((char) asci_dict.get(num).intValue()));

        return shuffled_nid.toString();
    }

    private static String intAsStr(long num) {
        return "\"" + num + "\"";
    }

    private static String CIFCalculatorDraft4(int indx_) {
        int indexValue = df_indexes.get(indx_).intValue();
        String randIndex = String.format("%0" + digits + "d", indexValue);

        int tsCd = checkdigitCalculatorOnedigit(String.valueOf(timestampID()));

        String CIF = randIndex + tsCd;
        CIF = nidShuffler(Long.parseLong(CIF));

        return CIF;
    }

    private static String intAsStr(Integer value){
        return Integer.toString(value);
    }

    private static List<Integer> df_indexes;
    private static int len_;
    private static int digits;

    public static void main(String[] args) {
//        df_indexes = new ArrayList<>();
//        for (int i = 0; i < df_len; i++) {
//            df_indexes.add(i);
//        }
//        Collections.shuffle(df_indexes);
//
//        len_ = df_len;
//        List<Integer> indexList = new ArrayList<>();
//        for (int i = 0; i < len_; i++) {
//            indexList.add(i);
//        }
//        digits = String.valueOf(Collections.max(indexList)).length();
//        System.out.println(digits + " digits, possible generated numbers " + num(Collections.max(indexList)));
//
//        List<String> CIFs = df_indexes.parallelStream().map(CIFGenerator::CIFCalculatorDraft4).collect(Collectors.toList());
//
//        // CIF consistency
//        Set<Integer> listLengths = CIFs.parallelStream().map(String::length).collect(Collectors.toSet());
//        int consistency_ = listLengths.size();
//        if (consistency_ == 1) {
//            System.out.println("All of the CIFs consists of " + listLengths.iterator().next() + " digits");
//        } else {
//            System.out.println("CIFs vary in length");
//        }
//
//        List<String> formattedCIFs = CIFs.parallelStream().map(CIFGenerator::intAsStr).collect(Collectors.toList());
//
//        List<String> rows = new ArrayList<>();
//        for (int i = 0; i < df_len; i++) {
//            rows.add(num(i) + "," + formattedCIFs.get(i));
//        }
//
//        // Saving CIFs to a CSV file
//        String csvContent = String.join("\n", rows);
//        String csvPath = output_path + "cif.csv";
//        try {
//            Files.write(Paths.get(csvPath), csvContent.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Loading sample CIFs from the CSV file
//        List<String> sampleRows = new ArrayList<>();
//        for (int i = 0; i < sample; i++) {
//            sampleRows.add(rows.get(i));
//        }
//
//        // Saving sample CIFs to a new CSV file
//        String sampleCsvContent = String.join("\n", sampleRows);
//        String sampleCsvPath = output_path + "cif_sample.csv";
//        try {
//            Files.write(Paths.get(sampleCsvPath), sampleCsvContent.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}