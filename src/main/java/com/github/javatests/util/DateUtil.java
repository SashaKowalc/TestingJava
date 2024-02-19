package com.github.javatests.util;

public class DateUtil {

  public static boolean isLeapYear(int year) {
    /* CODIGO INICIAL
    if(year % 4 == 0 && year % 100 != 0) {
      return true;
    }

    if (year % 400 == 0) {
      return true;
    } else {
      return false;
    }
    */
    //REFACTOR:
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
  }

}
