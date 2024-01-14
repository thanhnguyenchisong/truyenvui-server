package com.sky.tv.comics.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateServiceUtils {
  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
  public static Date fromStringToDate(String dateStr, TimeZone timeZone) throws ParseException {
     final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
     dateFormat.setTimeZone(timeZone);
     return dateFormat.parse(dateStr);
  }

  public static Date fromStringToDate(String dateStr) throws ParseException {
    return fromStringToDate(dateStr, TimeZone.getDefault());
  }

  public static String fromDateToString(Date date, TimeZone timeZone) {
    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    dateFormat.setTimeZone(timeZone);
    return dateFormat.format(date);
  }

  public static String fromDateToString(Date date) {
    return fromDateToString(date, TimeZone.getDefault());
  }

  public static String addDay(String currentDate, int day, TimeZone timeZone)
      throws ParseException {
    Date date = fromStringToDate(currentDate, timeZone);
    Calendar calendar = Calendar.getInstance(timeZone);
    calendar.setTime(date);
    calendar.add(Calendar.DATE, day);
    return fromDateToString(calendar.getTime(), timeZone);
  }

  public static String addDay(Date date, int day, TimeZone timeZone)
      throws ParseException {
    Calendar calendar = Calendar.getInstance(timeZone);
    calendar.setTime(date);
    calendar.add(Calendar.DATE, day);
    return fromDateToString(calendar.getTime(), timeZone);
  }

  public static String addDay(String currentDate, int day) throws ParseException {
    return addDay(currentDate, day, TimeZone.getDefault());
  }
}
