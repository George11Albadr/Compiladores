// DO NOT EDIT
// Generated by JFlex 1.9.1 http://jflex.de/
// source: compiler/scanner/Scanner.flex

package compiler.scanner;

import java_cup.runtime.*;
import compiler.parser.sym;


@SuppressWarnings("fallthrough")
public class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\37\u0100\1\u0200\267\u0100\10\u0300\u1020\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\3\1\4\1\5\22\0\1\1"+
    "\1\6\1\7\2\0\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\12\23\1\0"+
    "\1\24\1\25\1\26\1\27\2\0\1\30\7\31\1\32"+
    "\4\31\1\33\1\31\1\34\1\31\1\35\10\31\1\36"+
    "\1\37\1\40\1\0\1\31\1\0\1\41\1\42\1\43"+
    "\1\44\1\45\1\46\1\47\1\50\1\51\1\31\1\52"+
    "\1\53\1\54\1\55\1\56\1\57\1\31\1\60\1\61"+
    "\1\62\1\63\1\64\1\65\1\66\1\67\1\31\1\70"+
    "\1\71\1\72\7\0\1\3\u01a2\0\2\3\326\0\u0100\3";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1024];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\1\1\5\2\1"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\4\23\1\24\1\25"+
    "\13\23\1\26\1\1\1\27\1\30\1\0\1\31\1\0"+
    "\1\32\2\0\1\33\1\34\1\0\1\35\1\36\1\37"+
    "\1\40\14\23\1\41\12\23\1\42\1\43\1\0\13\23"+
    "\1\44\1\23\1\45\1\46\7\23\1\47\6\23\1\50"+
    "\1\23\1\51\3\23\1\52\2\23\1\53\1\54\1\55"+
    "\2\23\1\56\2\23\1\57\1\23\1\60\1\23\1\61"+
    "\3\23\1\62\6\23\1\63\1\64\2\23\1\65\1\66"+
    "\1\67\1\23\1\70\5\23\1\71\1\72";

  private static int [] zzUnpackAction() {
    int [] result = new int[159];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\73\0\166\0\261\0\354\0\u0127\0\73\0\u0162"+
    "\0\u019d\0\73\0\73\0\73\0\u01d8\0\73\0\u0213\0\73"+
    "\0\u024e\0\u0289\0\73\0\u02c4\0\u02ff\0\u033a\0\u0375\0\u03b0"+
    "\0\u03eb\0\u0426\0\73\0\73\0\u0461\0\u049c\0\u04d7\0\u0512"+
    "\0\u054d\0\u0588\0\u05c3\0\u05fe\0\u0639\0\u0674\0\u06af\0\73"+
    "\0\u06ea\0\73\0\73\0\u0127\0\73\0\u0725\0\73\0\u0760"+
    "\0\u079b\0\73\0\73\0\u07d6\0\u0811\0\73\0\73\0\73"+
    "\0\u084c\0\u0887\0\u08c2\0\u08fd\0\u0938\0\u0973\0\u09ae\0\u09e9"+
    "\0\u0a24\0\u0a5f\0\u0a9a\0\u0ad5\0\u0375\0\u0b10\0\u0b4b\0\u0b86"+
    "\0\u0bc1\0\u0bfc\0\u0c37\0\u0c72\0\u0cad\0\u0ce8\0\u0d23\0\73"+
    "\0\73\0\u0d5e\0\u0d99\0\u0dd4\0\u0e0f\0\u0e4a\0\u0e85\0\u0ec0"+
    "\0\u0efb\0\u0f36\0\u0f71\0\u0fac\0\u0fe7\0\u0375\0\u1022\0\u0375"+
    "\0\u0375\0\u105d\0\u1098\0\u10d3\0\u110e\0\u1149\0\u1184\0\u11bf"+
    "\0\73\0\u11fa\0\u1235\0\u1270\0\u12ab\0\u12e6\0\u1321\0\u0375"+
    "\0\u135c\0\u0375\0\u1397\0\u13d2\0\u140d\0\u0375\0\u1448\0\u1483"+
    "\0\u0375\0\u0375\0\u0375\0\u14be\0\u14f9\0\u0375\0\u1534\0\u156f"+
    "\0\u0375\0\u15aa\0\u0375\0\u15e5\0\u0375\0\u1620\0\u165b\0\u1696"+
    "\0\u0375\0\u16d1\0\u170c\0\u1747\0\u1782\0\u17bd\0\u17f8\0\u0375"+
    "\0\u0375\0\u1833\0\u186e\0\u0375\0\u0375\0\u0375\0\u18a9\0\u0375"+
    "\0\u18e4\0\u191f\0\u195a\0\u1995\0\u19d0\0\u0375\0\u0375";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[159];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\0\1\3\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\3\27\1\30\1\31\1\32\1\33\1\2\1\34\1\27"+
    "\1\35\1\36\1\27\1\37\1\40\2\27\1\41\3\27"+
    "\1\42\2\27\1\43\1\44\1\45\1\27\1\46\1\47"+
    "\2\27\1\50\1\51\1\52\74\0\1\3\2\0\1\3"+
    "\70\0\1\4\2\0\1\4\113\0\1\53\44\0\2\54"+
    "\1\0\4\54\1\55\27\54\1\56\33\54\11\0\1\57"+
    "\61\0\2\60\1\0\7\60\1\0\24\60\1\61\33\60"+
    "\26\0\1\62\72\0\1\63\61\0\1\64\4\0\1\65"+
    "\73\0\1\22\75\0\1\66\72\0\1\67\72\0\1\70"+
    "\67\0\1\27\4\0\6\27\3\0\27\27\26\0\1\27"+
    "\4\0\6\27\3\0\4\27\1\71\22\27\26\0\1\27"+
    "\4\0\6\27\3\0\17\27\1\72\7\27\26\0\1\27"+
    "\4\0\6\27\3\0\4\27\1\73\22\27\26\0\1\27"+
    "\4\0\6\27\3\0\15\27\1\74\1\27\1\75\7\27"+
    "\26\0\1\27\4\0\6\27\3\0\1\76\6\27\1\77"+
    "\2\27\1\100\14\27\26\0\1\27\4\0\6\27\3\0"+
    "\12\27\1\101\12\27\1\102\1\27\26\0\1\27\4\0"+
    "\6\27\3\0\1\103\14\27\1\104\11\27\26\0\1\27"+
    "\4\0\6\27\3\0\5\27\1\105\5\27\1\106\1\107"+
    "\12\27\26\0\1\27\4\0\6\27\3\0\4\27\1\110"+
    "\15\27\1\111\4\27\26\0\1\27\4\0\6\27\3\0"+
    "\4\27\1\112\22\27\26\0\1\27\4\0\6\27\3\0"+
    "\21\27\1\113\5\27\26\0\1\27\4\0\6\27\3\0"+
    "\7\27\1\114\7\27\1\115\7\27\26\0\1\27\4\0"+
    "\6\27\3\0\15\27\1\116\11\27\26\0\1\27\4\0"+
    "\6\27\3\0\7\27\1\117\17\27\74\0\1\120\1\0"+
    "\2\54\4\0\65\54\12\0\1\121\67\0\1\60\2\0"+
    "\1\60\24\0\1\60\2\0\1\60\3\0\1\60\6\0"+
    "\1\60\2\0\1\60\1\0\1\60\10\0\15\64\1\122"+
    "\55\64\2\65\4\0\65\65\23\0\1\27\4\0\6\27"+
    "\3\0\24\27\1\123\2\27\26\0\1\27\4\0\6\27"+
    "\3\0\10\27\1\124\16\27\26\0\1\27\4\0\6\27"+
    "\3\0\1\125\26\27\26\0\1\27\4\0\6\27\3\0"+
    "\15\27\1\126\11\27\26\0\1\27\4\0\6\27\3\0"+
    "\4\27\1\127\22\27\26\0\1\27\4\0\6\27\3\0"+
    "\12\27\1\130\14\27\26\0\1\27\4\0\6\27\3\0"+
    "\1\131\26\27\26\0\1\27\4\0\6\27\3\0\1\132"+
    "\26\27\26\0\1\27\4\0\6\27\3\0\20\27\1\133"+
    "\6\27\26\0\1\27\4\0\6\27\3\0\21\27\1\134"+
    "\5\27\26\0\1\27\4\0\6\27\3\0\12\27\1\135"+
    "\14\27\26\0\1\27\4\0\6\27\3\0\17\27\1\136"+
    "\7\27\26\0\1\27\4\0\6\27\3\0\16\27\1\137"+
    "\10\27\26\0\1\27\4\0\6\27\3\0\21\27\1\140"+
    "\5\27\26\0\1\27\4\0\6\27\3\0\24\27\1\141"+
    "\2\27\26\0\1\27\4\0\6\27\3\0\12\27\1\142"+
    "\14\27\26\0\1\27\4\0\6\27\3\0\21\27\1\143"+
    "\5\27\26\0\1\27\4\0\6\27\3\0\17\27\1\144"+
    "\7\27\26\0\1\27\4\0\6\27\3\0\10\27\1\145"+
    "\16\27\26\0\1\27\4\0\6\27\3\0\22\27\1\146"+
    "\4\27\26\0\1\27\4\0\6\27\3\0\10\27\1\147"+
    "\16\27\26\0\1\27\4\0\6\27\3\0\10\27\1\150"+
    "\16\27\3\0\15\64\1\122\4\64\1\151\50\64\23\0"+
    "\1\27\4\0\1\152\5\27\3\0\27\27\26\0\1\27"+
    "\4\0\6\27\3\0\14\27\1\153\12\27\26\0\1\27"+
    "\4\0\6\27\3\0\3\27\1\154\23\27\26\0\1\27"+
    "\4\0\6\27\3\0\12\27\1\155\14\27\26\0\1\27"+
    "\4\0\6\27\3\0\1\156\26\27\26\0\1\27\4\0"+
    "\6\27\3\0\12\27\1\157\14\27\26\0\1\27\4\0"+
    "\6\27\3\0\17\27\1\160\7\27\26\0\1\27\4\0"+
    "\6\27\3\0\20\27\1\161\6\27\26\0\1\27\4\0"+
    "\6\27\3\0\4\27\1\162\22\27\26\0\1\27\4\0"+
    "\6\27\3\0\4\27\1\163\22\27\26\0\1\27\4\0"+
    "\6\27\3\0\20\27\1\164\6\27\26\0\1\27\4\0"+
    "\6\27\3\0\12\27\1\165\14\27\26\0\1\27\4\0"+
    "\6\27\3\0\12\27\1\166\14\27\26\0\1\27\4\0"+
    "\6\27\3\0\22\27\1\167\4\27\26\0\1\27\4\0"+
    "\6\27\3\0\10\27\1\170\16\27\26\0\1\27\4\0"+
    "\6\27\3\0\20\27\1\171\6\27\26\0\1\27\4\0"+
    "\6\27\3\0\4\27\1\172\22\27\26\0\1\27\4\0"+
    "\6\27\3\0\3\27\1\173\23\27\26\0\1\27\4\0"+
    "\6\27\3\0\12\27\1\174\14\27\26\0\1\27\4\0"+
    "\6\27\3\0\17\27\1\175\7\27\26\0\1\27\4\0"+
    "\6\27\3\0\21\27\1\176\5\27\26\0\1\27\4\0"+
    "\2\27\1\177\3\27\3\0\27\27\26\0\1\27\4\0"+
    "\6\27\3\0\4\27\1\200\22\27\26\0\1\27\4\0"+
    "\6\27\3\0\11\27\1\201\15\27\26\0\1\27\4\0"+
    "\6\27\3\0\15\27\1\202\11\27\26\0\1\27\4\0"+
    "\6\27\3\0\20\27\1\203\6\27\26\0\1\27\4\0"+
    "\6\27\3\0\14\27\1\204\12\27\26\0\1\27\4\0"+
    "\6\27\3\0\4\27\1\205\22\27\26\0\1\27\4\0"+
    "\6\27\3\0\4\27\1\206\22\27\26\0\1\27\4\0"+
    "\6\27\3\0\17\27\1\207\7\27\26\0\1\27\4\0"+
    "\6\27\3\0\14\27\1\210\12\27\26\0\1\27\4\0"+
    "\6\27\3\0\4\27\1\211\22\27\26\0\1\27\4\0"+
    "\6\27\3\0\17\27\1\212\7\27\26\0\1\27\4\0"+
    "\6\27\3\0\14\27\1\213\12\27\26\0\1\27\4\0"+
    "\6\27\3\0\1\214\26\27\26\0\1\27\4\0\6\27"+
    "\3\0\22\27\1\215\4\27\26\0\1\27\4\0\6\27"+
    "\3\0\3\27\1\216\23\27\26\0\1\27\4\0\6\27"+
    "\3\0\13\27\1\217\13\27\26\0\1\27\4\0\6\27"+
    "\3\0\14\27\1\220\12\27\26\0\1\27\4\0\6\27"+
    "\3\0\6\27\1\221\20\27\26\0\1\27\4\0\6\27"+
    "\3\0\1\222\26\27\26\0\1\27\4\0\6\27\3\0"+
    "\21\27\1\223\5\27\26\0\1\27\4\0\6\27\3\0"+
    "\14\27\1\224\12\27\26\0\1\27\4\0\6\27\3\0"+
    "\21\27\1\225\5\27\26\0\1\27\4\0\6\27\3\0"+
    "\20\27\1\226\6\27\26\0\1\27\4\0\6\27\3\0"+
    "\4\27\1\227\22\27\26\0\1\27\4\0\6\27\3\0"+
    "\26\27\1\230\26\0\1\27\4\0\6\27\3\0\4\27"+
    "\1\231\22\27\26\0\1\27\4\0\6\27\3\0\14\27"+
    "\1\232\12\27\26\0\1\27\4\0\6\27\3\0\6\27"+
    "\1\233\20\27\26\0\1\27\4\0\6\27\3\0\21\27"+
    "\1\234\5\27\26\0\1\27\4\0\6\27\3\0\4\27"+
    "\1\235\22\27\26\0\1\27\4\0\6\27\3\0\20\27"+
    "\1\236\6\27\26\0\1\27\4\0\6\27\3\0\17\27"+
    "\1\237\7\27\3\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[6667];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\4\1\1\11\2\1\3\11\1\1\1\11"+
    "\1\1\1\11\2\1\1\11\7\1\2\11\13\1\1\11"+
    "\1\1\2\11\1\0\1\11\1\0\1\11\2\0\2\11"+
    "\1\0\1\1\3\11\27\1\2\11\1\0\26\1\1\11"+
    "\66\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[159];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen())];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline + 1, yycolumn + 1);
  }
  
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate && zzCanGrow()) {
      /* if not, and it can grow: blow it up */
      char newBuffer[] = new char[Math.min(zzBuffer.length * 2, zzMaxBufferLen())];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      if (requested == 0) {
        throw new java.io.EOFException("Scan buffer limit reached ["+zzBuffer.length+"]");
      }
      else {
        throw new java.io.IOException(
            "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
      }
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    int initBufferSize = Math.min(ZZ_BUFFERSIZE, zzMaxBufferLen());
    if (zzBuffer.length > initBufferSize) {
      zzBuffer = new char[initBufferSize];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
              {
                return symbol(sym.EOF);
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { System.err.println("Error: Carácter no reconocido '" + yytext() + "' en la línea " 
                       + (yyline + 1) + ", columna " + (yycolumn + 1));
    yycolumn += yylength();
    return symbol(sym.error, yytext());
            }
          // fall through
          case 59: break;
          case 2:
            { yycolumn += yylength();
            }
          // fall through
          case 60: break;
          case 3:
            { yyline++; yycolumn = 0;
            }
          // fall through
          case 61: break;
          case 4:
            { return symbol(sym.NOT, yytext());
            }
          // fall through
          case 62: break;
          case 5:
            { return symbol(sym.MOD, yytext());
            }
          // fall through
          case 63: break;
          case 6:
            { return symbol(sym.LPAREN, yytext());
            }
          // fall through
          case 64: break;
          case 7:
            { return symbol(sym.RPAREN, yytext());
            }
          // fall through
          case 65: break;
          case 8:
            { return symbol(sym.MULTIPLY, yytext());
            }
          // fall through
          case 66: break;
          case 9:
            { return symbol(sym.PLUS, yytext());
            }
          // fall through
          case 67: break;
          case 10:
            { return symbol(sym.COMMA, yytext());
            }
          // fall through
          case 68: break;
          case 11:
            { return symbol(sym.MINUS, yytext());
            }
          // fall through
          case 69: break;
          case 12:
            { return symbol(sym.DOT, yytext());
            }
          // fall through
          case 70: break;
          case 13:
            { return symbol(sym.DIVIDE, yytext());
            }
          // fall through
          case 71: break;
          case 14:
            { yycolumn += yylength();
    return symbol(sym.INT_LITERAL, Integer.parseInt(yytext()));
            }
          // fall through
          case 72: break;
          case 15:
            { return symbol(sym.SEMI, yytext());
            }
          // fall through
          case 73: break;
          case 16:
            { return symbol(sym.LT, yytext());
            }
          // fall through
          case 74: break;
          case 17:
            { return symbol(sym.ASSIGN, yytext());
            }
          // fall through
          case 75: break;
          case 18:
            { return symbol(sym.GT, yytext());
            }
          // fall through
          case 76: break;
          case 19:
            { yycolumn += yylength();
    return symbol(sym.ID, yytext());
            }
          // fall through
          case 77: break;
          case 20:
            { return symbol(sym.LBRACKET, yytext());
            }
          // fall through
          case 78: break;
          case 21:
            { return symbol(sym.RBRACKET, yytext());
            }
          // fall through
          case 79: break;
          case 22:
            { return symbol(sym.LBRACE, yytext());
            }
          // fall through
          case 80: break;
          case 23:
            { return symbol(sym.RBRACE, yytext());
            }
          // fall through
          case 81: break;
          case 24:
            { return symbol(sym.NEQ, yytext());
            }
          // fall through
          case 82: break;
          case 25:
            { String stringValue = yytext().substring(1, yylength() - 1); // Remover las comillas
    yycolumn += yylength();
    return symbol(sym.STRING_LITERAL, stringValue);
            }
          // fall through
          case 83: break;
          case 26:
            { return symbol(sym.AND, yytext());
            }
          // fall through
          case 84: break;
          case 27:
            { return symbol(sym.PLUS_ASSIGN, yytext());
            }
          // fall through
          case 85: break;
          case 28:
            { return symbol(sym.MINUS_ASSIGN, yytext());
            }
          // fall through
          case 86: break;
          case 29:
            { /* Ignorar comentarios de una línea */
            }
          // fall through
          case 87: break;
          case 30:
            { return symbol(sym.LE, yytext());
            }
          // fall through
          case 88: break;
          case 31:
            { return symbol(sym.EQ, yytext());
            }
          // fall through
          case 89: break;
          case 32:
            { return symbol(sym.GE, yytext());
            }
          // fall through
          case 90: break;
          case 33:
            { return symbol(sym.IF, yytext());
            }
          // fall through
          case 91: break;
          case 34:
            { return symbol(sym.OR, yytext());
            }
          // fall through
          case 92: break;
          case 35:
            { String text = yytext();
    char charValue;
    if (text.length() == 3) {
        // Carácter simple, como 'a'
        charValue = text.charAt(1);
    } else if (text.length() == 4 && text.charAt(1) == '\\') {
        // Secuencia de escape, como '\n'
        switch (text.charAt(2)) {
            case 'b': charValue = '\b'; break;
            case 't': charValue = '\t'; break;
            case 'n': charValue = '\n'; break;
            case 'f': charValue = '\f'; break;
            case 'r': charValue = '\r'; break;
            case '\'': charValue = '\''; break;
            case '\"': charValue = '\"'; break;
            case '\\': charValue = '\\'; break;
            default:
                System.err.println("Error: Secuencia de escape inválida en literal de carácter: " + text + " en la línea " + (yyline + 1) + ", columna " + (yycolumn + 1));
                yycolumn += yylength();
                return symbol(sym.error, yytext());
        }
    } else {
        System.err.println("Error: Literal de carácter inválido: " + text + " en la línea " + (yyline + 1) + ", columna " + (yycolumn + 1));
        yycolumn += yylength();
        return symbol(sym.error, yytext());
    }
    yycolumn += yylength();
    return symbol(sym.CHAR_LITERAL, charValue);
            }
          // fall through
          case 93: break;
          case 36:
            { return symbol(sym.FOR, yytext());
            }
          // fall through
          case 94: break;
          case 37:
            { return symbol(sym.INT, yytext());
            }
          // fall through
          case 95: break;
          case 38:
            { return symbol(sym.NEW, yytext());
            }
          // fall through
          case 96: break;
          case 39:
            { /* Ignorar comentarios de múltiples líneas */
            }
          // fall through
          case 97: break;
          case 40:
            { return symbol(sym.CHAR, yytext());
            }
          // fall through
          case 98: break;
          case 41:
            { return symbol(sym.ELSE, yytext());
            }
          // fall through
          case 99: break;
          case 42:
            { return symbol(sym.NULL, yytext());
            }
          // fall through
          case 100: break;
          case 43:
            { return symbol(sym.THIS, yytext());
            }
          // fall through
          case 101: break;
          case 44:
            { return symbol(sym.TRUE, yytext());
            }
          // fall through
          case 102: break;
          case 45:
            { return symbol(sym.VOID, yytext());
            }
          // fall through
          case 103: break;
          case 46:
            { return symbol(sym.PRINT, yytext());
            }
          // fall through
          case 104: break;
          case 47:
            { return symbol(sym.BREAK, yytext());
            }
          // fall through
          case 105: break;
          case 48:
            { return symbol(sym.CLASS, yytext());
            }
          // fall through
          case 106: break;
          case 49:
            { return symbol(sym.FALSE, yytext());
            }
          // fall through
          case 107: break;
          case 50:
            { return symbol(sym.WHILE, yytext());
            }
          // fall through
          case 108: break;
          case 51:
            { return symbol(sym.RETURN, yytext());
            }
          // fall through
          case 109: break;
          case 52:
            { return symbol(sym.STRING, yytext());
            }
          // fall through
          case 110: break;
          case 53:
            { return symbol(sym.BOOLEAN, yytext());
            }
          // fall through
          case 111: break;
          case 54:
            { return symbol(sym.CALLOUT, yytext());
            }
          // fall through
          case 112: break;
          case 55:
            { return symbol(sym.EXTENDS, yytext());
            }
          // fall through
          case 113: break;
          case 56:
            { return symbol(sym.NEWARRAY, yytext());
            }
          // fall through
          case 114: break;
          case 57:
            { return symbol(sym.IMPLEMENTS, yytext());
            }
          // fall through
          case 115: break;
          case 58:
            { return symbol(sym.READINTEGER, yytext());
            }
          // fall through
          case 116: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}