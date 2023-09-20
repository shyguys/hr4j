package com.shyguys.hr4j;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Argument;
import net.sourceforge.argparse4j.inf.ArgumentGroup;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.ArgumentType;
import net.sourceforge.argparse4j.inf.Namespace;

public class hr4j {
  public static final String PROG = "hr4j";

  private static Namespace parseArgs(String[] args) {
    ArgumentParser parser = ArgumentParsers.newFor(PROG)
        .singleMetavar(true)
        .build();
    parser.addArgument("title")
        .dest("title")
        .help("title to insert in center")
        .nargs("?");
    parser.addArgument("-p --as-paragraph")
        .dest("asParagraph")
        .help("print two horizontal rules, BEGIN and END")
        .action(Arguments.storeTrue());
    ArgumentGroup styleProperties = parser.addArgumentGroup("style properties");
    styleProperties.addArgument("-i --inner")
        .dest("inner")
        .metavar("STR")
        .help("inner character (default: -)")
        .setDefault("-")
        .type(new ArgumentType<String>() {
          @Override
          public String convert(ArgumentParser parser, Argument arg, String value) throws ArgumentParserException {
            return value.strip().substring(0, 1);
          }
        });
    styleProperties.addArgument("-l --length")
        .dest("length")
        .metavar("INT")
        .help("total character length (default: 80)")
        .setDefault(80)
        .type(Integer.class);
    styleProperties.addArgument("-o --outer")
        .dest("outer")
        .metavar("STR")
        .help("outer character(s) (default: //)")
        .setDefault("//")
        .type(new ArgumentType<String>() {
          @Override
          public String convert(ArgumentParser parser, Argument arg, String value) throws ArgumentParserException {
            return value.strip();
          }
        });
    try {
      return parser.parseArgs(args);
    } catch (ArgumentParserException e) {
      parser.handleError(e);
      if (e.getMessage().equals("Help Screen")) {
        System.exit(0);
      } else {
        System.exit(1);
      }
    }
    return null;
  }

  public static void main(String[] args) {
    Namespace ns = parseArgs(args);
    try {
      if (ns.getBoolean("asParagraph")) {
        lib.print_paragraph(ns.getInt("length"), ns.getString("outer"), ns.getString("inner"), ns.getString("title"));
      } else if (ns.getString("title") != null) {
        lib.print_titled(ns.getInt("length"), ns.getString("outer"), ns.getString("inner"), ns.getString("title"));
      } else {
        lib.print_untitled(ns.getInt("length"), ns.getString("outer"), ns.getString("inner"));
      }
    } catch (lib.LengthException e) {
      System.err.println("[ERROR] " + e.getMessage());
      System.exit(1);
    }
  }
}
