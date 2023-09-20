package com.shyguys.hr4j;

public class lib {
  public static class LengthException extends Exception {
    public int moreRequired;

    public LengthException(int moreRequired) {
      this.moreRequired = moreRequired;
    }

    @Override
    public String getMessage() {
      return this.toString();
    }

    @Override
    public String toString() {
      return "length insufficient, " + String.valueOf(this.moreRequired) + " more required";
    }
  }

  public static void print_paragraph(int length, String outer, String inner, String title) throws LengthException {
    String begin = "BEGIN";
    String end = "END";
    if (title != null) {
      begin = begin + " " + title;
      end = end + " " + title;
    }
    print_titled(length, outer, inner, begin);
    print_titled(length, outer, inner, end);
  }

  public static void print_titled(int length, String outer, String inner, String title) throws LengthException {
    int spareLength = length - outer.length() * 2 - title.length() - 4;
    if (spareLength < 2) {
      throw new LengthException(2 - spareLength);
    }
    int rightSpareLength = Math.floorDiv(spareLength, 2);
    int leftSpareLength = spareLength - rightSpareLength;
    System.out.printf(
        "%s %s %s %s %s%n",
        outer,
        inner.repeat(leftSpareLength),
        title,
        inner.repeat(rightSpareLength),
        outer);
  }

  public static void print_untitled(int length, String outer, String inner) throws LengthException {
    int spareLength = length - outer.length() * 2 - 2;
    if (spareLength < 1) {
      throw new LengthException(1 - spareLength);
    }
    System.out.printf("%s %s %s%n", outer, inner.repeat(spareLength), outer);
  }
}
