package com.example.DnDProject.Services.EncounterCalculator;

import java.util.Map;

public class EncounterXPData {

    public static double getEncounterMultiplier(int monstersnum, int playersnum) {
        double baseMultiplier = 1;

        float cof = (float) monstersnum /playersnum;

        if (cof < 0.5 ) {
            baseMultiplier *= 0.5;
        }else if (cof < 0.75) {
            baseMultiplier *= 0.75;
        }else if (cof < 1) {
            baseMultiplier *= 1;
        }else if (cof < 1.5) {
            baseMultiplier *= 1.25;
        } else if (cof < 2.5) {
            baseMultiplier *= 1.5;
        }else if (cof < 3.5) {
            baseMultiplier *= 1.75;
        }else{
            baseMultiplier *= 2;
        }

        return baseMultiplier;
    }

    public static final Map<Integer, int[]> LEVEL_THRESHOLDS = Map.ofEntries(
            Map.entry(1, new int[]{25, 50, 75, 100}),
            Map.entry(2, new int[]{50, 100, 150, 200}),
            Map.entry(3, new int[]{75, 150, 225, 400}),
            Map.entry(4, new int[]{125, 250, 375, 500}),
            Map.entry(5, new int[]{250, 500, 750, 900}),
            Map.entry(6, new int[]{300, 600, 800, 1000}),
            Map.entry(7, new int[]{450, 750, 1100, 1400}),
            Map.entry(8, new int[]{450, 750, 1100, 1400}),
            Map.entry(9, new int[]{600, 1000, 1500, 2000}),
            Map.entry(10, new int[]{600, 1200, 1700, 2200}),
            Map.entry(11, new int[]{800, 1600, 2200, 3000}),
            Map.entry(12, new int[]{800, 1600, 2200, 3000}),
            Map.entry(13, new int[]{1100, 2000, 3000, 4000}),
            Map.entry(14, new int[]{1100, 2000, 3000, 4000}),
            Map.entry(15, new int[]{1600, 2800, 4000, 5500}),
            Map.entry(16, new int[]{1600, 2800, 4000, 5500}),
            Map.entry(17, new int[]{2400, 3900, 5300, 7100}),
            Map.entry(18, new int[]{2400, 3900, 5300, 7100}),
            Map.entry(19, new int[]{2400, 3900, 5300, 7100}),
            Map.entry(20, new int[]{3200, 5500, 8000, 10000})
    );

    public static final Map<String, Integer> CR_XP = Map.ofEntries(
            Map.entry("0", 0),
            Map.entry("1/8", 10),
            Map.entry("1/4", 20),
            Map.entry("1/2", 35),
            Map.entry("1", 75),
            Map.entry("2", 200),
            Map.entry("3", 400),
            Map.entry("4", 650),
            Map.entry("5", 900),
            Map.entry("6", 1300),
            Map.entry("7", 1800),
            Map.entry("8", 2200),
            Map.entry("9", 3200),
            Map.entry("10", 4000),
            Map.entry("11", 4800),
            Map.entry("12", 5200),
            Map.entry("13", 6000),
            Map.entry("14", 6800),
            Map.entry("15", 7800),
            Map.entry("16", 8800),
            Map.entry("17", 9500),
            Map.entry("18", 10900),
            Map.entry("19", 12000),
            Map.entry("20", 14500),
            Map.entry("21", 16800),
            Map.entry("22", 19000),
            Map.entry("23", 23000),
            Map.entry("24", 28000),
            Map.entry("25", 35000),
            Map.entry("26", 45000),
            Map.entry("27", 58000),
            Map.entry("28", 70000),
            Map.entry("29", 86000),
            Map.entry("30", 100000)
    );
}
