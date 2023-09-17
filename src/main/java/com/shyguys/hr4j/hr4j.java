package com.shyguys.hr4j;

public class hr4j {
  public static final String PROG = "hr4j";

  private static class ArgsNamespace {
    public int length;
    public String outer;
    public String inner;
    public boolean asParagraph;
    public String title;

    public ArgsNamespace(int length, String outer, String inner, boolean asParagraph, String title) {
      this.length = length;
      this.outer = outer;
      this.inner = inner;
      this.asParagraph = asParagraph;
      this.title = title;
    }
  }

  private static void usage() {
    System.out.println(
        "usage: " + PROG + " [-h] [-l INT] [-o STR] [-i CHAR] [-p] [title]\n" + //
            "\n" + //
            "positional arguments:\n" + //
            "  title               title to insert in center\n" + //
            "\n" + //
            "options:\n" + //
            "  -h, --help          show this help message and exit\n" + //
            "  -l, --length INT    total character length (default: 80)\n" + //
            "  -o, --outer STR     outer character(s) (default: //)\n" + //
            "  -i, --inner CHAR    inner character (default: -)\n" + //
            "  -p, --as-paragraph  as paragraph, BEGIN and END");
  }

  private static ArgsNamespace parseArgs(String[] args) {
    ArgsNamespace ans = new ArgsNamespace(80, "//", "-", false, null);
    for (int i = 0; i < args.length; i++) {
      String opt = args[i];
      if (opt.equals("-h") || opt.equals("--help")) {
        usage();
        System.exit(0);
      } else if (opt.equals("-l") || opt.equals("--length")) {
        try {
          ans.length = Integer.parseInt(args[++i]);
        } catch (IndexOutOfBoundsException e) {
          System.err.println(PROG + ": -l/--length expected one argument");
          System.exit(1);
        } catch (NumberFormatException e) {
          System.err.println(PROG + ": -l/--length must be an integer");
          System.exit(1);
        }
      } else if (opt.equals("-o") || opt.equals("--outer")) {
        try {
          ans.outer = args[++i];
        } catch (IndexOutOfBoundsException e) {
          System.err.println(PROG + ": -o/--outer expected one argument");
          System.exit(1);
        }
      } else if (opt.equals("-i") || opt.equals("--inner")) {
        try {
          ans.inner = args[++i].substring(0, 1);
        } catch (IndexOutOfBoundsException e) {
          System.err.println(PROG + ": -i/--inner expected one argument");
          System.exit(1);
        }
      } else if (opt.equals("-p") || opt.equals("--as-paragraph")) {
        ans.asParagraph = true;
      } else if (opt.startsWith("-")) {
        System.err.println(PROG + ": option '" + opt + "' not recognized");
        System.exit(1);
      } else {
        if (ans.title == null) {
          ans.title = opt;
        } else {
          System.err.println(PROG + ": positional argument '" + opt + "' not recognized");
          System.exit(1);
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    ArgsNamespace ans = parseArgs(args);
    if (ans.asParagraph) {
      lib.print_paragraph(ans.length, ans.outer, ans.inner, ans.title);
    } else if (ans.title != null) {
      lib.print_titled(ans.length, ans.outer, ans.inner, ans.title);
    } else {
      lib.print_untitled(ans.length, ans.outer, ans.inner);
    }
  }
}
