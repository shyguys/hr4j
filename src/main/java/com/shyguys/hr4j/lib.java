package com.shyguys.hr4j;

public class lib {
  public static void print_paragraph(int length, String outer, String inner, String title) {
    String begin = "BEGIN";
    String end = "END";
    if (title != null) {
      begin = begin + " " + title;
      end = end + " " + title;
    }
    print_titled(length, outer, inner, begin);
    print_titled(length, outer, inner, end);
  }

  public static void print_titled(int length, String outer, String inner, String title) {
    int spareLen = length - outer.length() * 2 - title.length() - 4;
    if (spareLen < 2) {
      System.err.println("Length insufficient, " + String.valueOf(2 - spareLen) + " more required.");
      System.exit(1);
    }
    int rspareLen = Math.floorDiv(spareLen, 2);
    int lspareLen = spareLen - rspareLen;
    System.out.printf(
        "%s %s %s %s %s%n",
        outer,
        inner.repeat(lspareLen),
        title,
        inner.repeat(rspareLen),
        outer);
  }

  public static void print_untitled(int length, String outer, String inner) {
    int spareLen = length - outer.length() * 2 - 2;
    if (spareLen < 1) {
      System.err.println("Length insufficient, " + String.valueOf(1 - spareLen) + " more required.");
      System.exit(1);
    }
    System.out.printf("%s %s %s%n", outer, inner.repeat(spareLen), outer);
  }
}
