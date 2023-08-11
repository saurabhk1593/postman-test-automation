package com.baraq.merchantsystem.utilities;

import java.util.HashMap;
import java.util.Map;

public class CifShuffler {

    private static Map<Integer, Integer> cif_shuffle_dict = new HashMap<>();

    static {
        // Initialize the cif_shuffle_dict with mapping values
        cif_shuffle_dict.put(0, 7);
        cif_shuffle_dict.put(1, 2);
        cif_shuffle_dict.put(2, 9);
        cif_shuffle_dict.put(3, 1);
        cif_shuffle_dict.put(4, 6);
        cif_shuffle_dict.put(5, 0);
        cif_shuffle_dict.put(6, 8);
        cif_shuffle_dict.put(7, 3);
        cif_shuffle_dict.put(8, 4);
        cif_shuffle_dict.put(9, 5);
    }

    public static String cifShuffler(String CIF) {
        StringBuilder shuffled_cif = new StringBuilder();

        for (int i = 0; i < CIF.length(); i++) {
            int num = cif_shuffle_dict.get(Character.getNumericValue(CIF.charAt(i))) % 10;
            shuffled_cif.append(num);
        }
        return shuffled_cif.toString();
    }
}