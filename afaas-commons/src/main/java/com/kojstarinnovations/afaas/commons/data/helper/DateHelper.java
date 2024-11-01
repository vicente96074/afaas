package com.kojstarinnovations.afaas.commons.data.helper;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateHelper {

    public static LocalDate addMonths(LocalDate date, int months) {
        return date.plusMonths(months);
    }

    public static LocalDateTime addMonths(LocalDateTime date, int months) {
        return date.plusMonths(months);
    }

    /*public static int daysBetweenDates(LocalDate from,  LocalDate until) {
        try{
            long lDays  = ChronoUnit.DAYS.between(from, until);
            return Integer.parseInt(String.valueOf(lDays));
        }catch (Exception e){
            return 0;
        }
    }*/

    public static int daysBetweenDates(LocalDate from, LocalDate until) {
        try {
            return Math.toIntExact(ChronoUnit.DAYS.between(from, until));
        } catch (ArithmeticException | DateTimeException e) {
            return 0;
        }
    }

    public static int daysBetweenDates(LocalDateTime from, LocalDateTime until) {
        try {
            return Math.toIntExact(ChronoUnit.DAYS.between(from, until));
        } catch (ArithmeticException | DateTimeException e) {
            return 0;
        }
    }

    public static int daysOfMonth(LocalDate date) {
        boolean leapYear = false;

        int year = date.getYear();
        int month = date.getMonthValue();
        leapYear = year % 4 == 0;

        if (month == 2 && leapYear) { //Leap year february will have 29 days
            return 29;
        } else if (month == 2) { // February will have 28 days
            return 28;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) { // Months with 31 days
            return 31;
        } else { // Months with 30 days
            return 30;
        }
    }

    public static int daysOfMonth(LocalDateTime date) {
        boolean leapYear = false;

        int year = date.getYear();
        int month = date.getMonthValue();
        leapYear = year % 4 == 0;

        if (month == 2 && leapYear) { //Leap year february will have 29 days
            return 29;
        } else if (month == 2) { // February will have 28 days
            return 28;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) { // Months with 31 days
            return 31;
        } else { // Months with 30 days
            return 30;
        }
    }

    /*public static LocalDate insertDay(LocalDate l, int dia) {
        int year = l.getYear();
        int month = l.getMonthValue();
        String date = year + "-" + String.format("%02d", (month)) + "-" + String.format("%02d", (dia));
        return stringToLocalDate(date);
    }*/

    public static LocalDate insertDay(LocalDate l, int dia) {
        return l.withDayOfMonth(dia);
    }

    public static LocalDateTime insertDay(LocalDateTime l, int dia) {
        return l.withDayOfMonth(dia);
    }

    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    /*public static long monthBetween(LocalDate from, LocalDate until) {
        long months = java.time.temporal.ChronoUnit.MONTHS.between(from, until);
        if (!(until.getMonthValue() == 2) && until.getMonth().length(until.isLeapYear()) == 31) {
            return months;
        }
        if (until.getDayOfMonth() == from.getDayOfMonth()) {
            return months;
        }
        if ((until.getDayOfMonth() >= 28) && (from.getDayOfMonth() >= 28)) {
            months = months + 1;
            return months;
        }
        return months;
    }*/

    public static long monthsBetween(LocalDate from, LocalDate until) {
        long months = ChronoUnit.MONTHS.between(from, until);

        if (until.getDayOfMonth() == until.getMonth().length(until.isLeapYear()) && from.getDayOfMonth() > 28) {
            months++;
        }

        return months;
    }


    public static long monthsBetween(LocalDateTime from, LocalDateTime until) {
        long months = ChronoUnit.MONTHS.between(from, until);

        if (until.getDayOfMonth() == until.getMonth().length(until.toLocalDate().isLeapYear()) && from.getDayOfMonth() > 28) {
            months++;
        }

        return months;
    }

    /*public static boolean lessOrEqualToRange(LocalDate dateFrom, LocalDate dateTo, int range) {
        for (int i = 0; i < range; i++) {
            LocalDate ld = dateFrom.plusDays(i);
            if (ld.equals(dateTo)) {
                return true;
            }
        }
        return false;
    }*/

    public static boolean lessOrEqualToRange(LocalDate dateFrom, LocalDate dateTo, int range) {
        return !dateTo.isAfter(dateFrom.plusDays(range)); //If dateTo is not after dateFrom + range days, then it is less or equal to range
    }

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
