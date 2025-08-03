package com.example.DnDProject.Services.EncounterCalculator;

import java.util.Map;

public class EncounterXPData {

    public static double getEncounterMultiplier(int monstersnum, int playersnum) {
        double baseMultiplier;
        if (monstersnum == 1) baseMultiplier = 1.0;
        else if (monstersnum == 2) baseMultiplier = 1.5;
        else if (monstersnum <= 6) baseMultiplier = 2.0;
        else if (monstersnum <= 10) baseMultiplier = 2.5;
        else if (monstersnum <= 14) baseMultiplier = 3.0;
        else baseMultiplier = 4.0;

        if (playersnum<3) baseMultiplier *= 1.5;
        else if (playersnum>5) baseMultiplier *= 0.5;

        return baseMultiplier;
    }

    public static final Map<Integer, int[]> LEVEL_THRESHOLDS = Map.ofEntries(
            Map.entry(1, new int[]{25, 50, 75, 100}),
            Map.entry(2, new int[]{50, 100, 150, 200}),
            Map.entry(3, new int[]{75, 150, 225, 400}),
            Map.entry(4, new int[]{125, 250, 375, 500}),
            Map.entry(5, new int[]{250, 500, 750, 1100}),
            Map.entry(6, new int[]{300, 600, 900, 1400}),
            Map.entry(7, new int[]{350, 750, 1100, 1700}),
            Map.entry(8, new int[]{450, 900, 1400, 2100}),
            Map.entry(9, new int[]{550, 1100, 1600, 2400}),
            Map.entry(10, new int[]{600, 1200, 1900, 2800}),
            Map.entry(11, new int[]{800, 1600, 2400, 3600}),
            Map.entry(12, new int[]{1000, 2000, 3000, 4500}),
            Map.entry(13, new int[]{1100, 2200, 3400, 5100}),
            Map.entry(14, new int[]{1250, 2500, 3800, 5700}),
            Map.entry(15, new int[]{1400, 2800, 4300, 6400}),
            Map.entry(16, new int[]{1600, 3200, 4800, 7200}),
            Map.entry(17, new int[]{2000, 3900, 5900, 8800}),
            Map.entry(18, new int[]{2100, 4200, 6300, 9500}),
            Map.entry(19, new int[]{2400, 4900, 7300, 10900}),
            Map.entry(20, new int[]{2800, 5700, 8500, 12700})
    );

    public static final Map<String, Integer> CR_XP = Map.ofEntries(
            Map.entry("0", 10),
            Map.entry("1/8", 25),
            Map.entry("1/4", 50),
            Map.entry("1/2", 100),
            Map.entry("1", 200),
            Map.entry("2", 450),
            Map.entry("3", 700),
            Map.entry("4", 1100),
            Map.entry("5", 1800),
            Map.entry("6", 2300),
            Map.entry("7", 2900),
            Map.entry("8", 3900),
            Map.entry("9", 5000),
            Map.entry("10", 5900),
            Map.entry("11", 7200),
            Map.entry("12", 8400),
            Map.entry("13", 10000),
            Map.entry("14", 11500),
            Map.entry("15", 13000),
            Map.entry("16", 15000),
            Map.entry("17", 18000),
            Map.entry("18", 20000),
            Map.entry("19", 22000),
            Map.entry("20", 25000),
            Map.entry("21", 33000),
            Map.entry("22", 41000),
            Map.entry("23", 50000),
            Map.entry("24", 62000),
            Map.entry("25", 75000),
            Map.entry("26", 90000),
            Map.entry("27", 105000),
            Map.entry("28", 120000),
            Map.entry("29", 135000),
            Map.entry("30", 155000)
    );
}
