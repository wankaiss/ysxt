package  com.wondersgroup.qyws.tjfx.common;

/*
 * RandomGUID
 * @version 1.2.1 11/05/02
 * @author Marc A. Mnich
 *
 * From www.JavaExchange.com, Open Software licensing
 *
 * 11/05/02 -- Performance enhancement from Mike Dubman.
 *             Moved InetAddr.getLocal to static block.  Mike has measured
 *             a 10 fold improvement in run time.
 * 01/29/02 -- Bug fix: Improper seeding of nonsecure Random object
 *             caused duplicate GUIDs to be produced.  Random object
 *             is now only created once per JVM.
 * 01/19/02 -- Modified random seeding and added new constructor
 *             to allow secure random feature.
 * 01/14/02 -- Added random function seeding with JVM run time
 *
 */

import java.net.*;
import java.security.*;
import java.util.*;



/*
 * In the multitude of java GUID generators, I found none that
 * guaranteed randomness.  GUIDs are guaranteed to be globally unique
 * by using ethernet MACs, IP addresses, time elements, and sequential
 * numbers.  GUIDs are not expected to be random and most often are
 * easy/possible to guess given a sample from a given generator.
 * SQL Server, for example generates GUID that are unique but
 * sequencial within a given instance.
 *
 * GUIDs can be used as security devices to hide things such as
 * files within a filesystem where listings are unavailable (e.g. files
 * that are served up from a Web server with indexing turned off).
 * This may be desireable in cases where standard authentication is not
 * appropriate. In this scenario, the RandomGUIDs are used as directories.
 * Another example is the use of GUIDs for primary keys in a database
 * where you want to ensure that the keys are secret.  Random GUIDs can
 * then be used in a URL to prevent hackers (or users) from accessing
 * records by guessing or simply by incrementing sequential numbers.
 *
 * There are many other possiblities of using GUIDs in the realm of
 * security and encryption where the element of randomness is important.
 * This class was written for these purposes but can also be used as a
 * general purpose GUID generator as well.
 *
 * RandomGUID generates truly random GUIDs by using the system's
 * IP address (name/IP), system time in milliseconds (as an integer),
 * and a very large random number joined together in a single String
 * that is passed through an MD5 hash.  The IP address and system time
 * make the MD5 seed globally unique and the random number guarantees
 * that the generated GUIDs will have no discernable pattern and
 * cannot be guessed given any number of previously generated GUIDs.
 * It is generally not possible to access the seed information (IP, time,
 * random number) from the resulting GUIDs as the MD5 hash algorithm
 * provides one way encryption.
 *
 * ----> Security of RandomGUID: <-----
 * RandomGUID can be called one of two ways -- with the basic java Random
 * number generator or a cryptographically strong random generator
 * (SecureRandom).  The choice is offered because the secure random
 * generator takes about 3.5 times longer to generate its random numbers
 * and this performance hit may not be worth the added security
 * especially considering the basic generator is seeded with a
 * cryptographically strong random seed.
 *
 * Seeding the basic generator in this way effectively decouples
 * the random numbers from the time component making it virtually impossible
 * to predict the random number component even if one had absolute knowledge
 * of the System time.  Thanks to Ashutosh Narhari for the suggestion
 * of using the static method to prime the basic random generator.
 *
 * Using the secure random option, this class compies with the statistical
 * random number generator tests specified in FIPS 140-2, Security
 * Requirements for Cryptographic Modules, secition 4.9.1.
 *
 * I converted all the pieces of the seed to a String before handing
 * it over to the MD5 hash so that you could print it out to make
 * sure it contains the data you expect to see and to give a nice
 * warm fuzzy.  If you need better performance, you may want to stick
 * to byte[] arrays.
 *
 * I believe that it is important that the algorithm for
 * generating random GUIDs be open for inspection and modification.
 * This class is free for all uses.
 *
 *
 * - Marc
 */

public class RandomGUID
    extends Object {

  public String valueBeforeMD5 = "";
  public String valueAfterMD5 = "";
  private static Random myRand;
  private static SecureRandom mySecureRand;

  private static byte[] s_ip;
  private static String s_name;

  /*
   * Static block to take care of one time secureRandom seed.
   * It takes a few seconds to initialize SecureRandom.  You might
   * want to consider removing this static block or replacing
   * it with a "time since first loaded" seed to reduce this time.
   * This block will run only once per JVM instance.
   */

  static {
    mySecureRand = new SecureRandom();
    long secureInitializer = mySecureRand.nextLong();
    myRand = new Random(secureInitializer);
    try {
      s_ip = InetAddress.getLocalHost().getAddress();
      s_name = InetAddress.getLocalHost().getHostName();
    }
    catch (UnknownHostException e) {
      e.printStackTrace();
    }

  }

  /*
   * Default constructor.  With no specification of security option,
   * this constructor defaults to lower security, high performance.
   */
  public RandomGUID() {
    getRandomGUID(false);
  }

  /*
   * Constructor with security option.  Setting secure true
   * enables each random number generated to be cryptographically
   * strong.  Secure false defaults to the standard Random function seeded
   * with a single cryptographically strong random number.
   */
  public RandomGUID(boolean secure) {
    getRandomGUID(secure);
  }

  /*
   * Method to generate the random GUID
   */
  private void getRandomGUID(boolean secure) {
    MessageDigest md5 = null;
    StringBuffer sbValueBeforeMD5 = new StringBuffer();

    try {
      md5 = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException e) {
      System.out.println("Error: " + e);
    }

    try {
      md5.update(s_name.getBytes());
      byte[] array = md5.digest();
      valueAfterMD5 = getVal(hexFormat(getInt(s_ip), 8) +
                             hexFormat(getInt(array), 8));
    }
    catch (Exception e) {
      System.out.println("Error:" + e);
    }
  }

  private String getVal(String s) throws Exception {
    long l = System.currentTimeMillis();
    int i = (int) l & 0xffffffff;
    int j = myRand.nextInt();
    return s + hexFormat(i, 8) + hexFormat(j, 8);
  }

  private String hexFormat(int i, int j) {
    String s = Integer.toHexString(i);
    return padHex(s, j) + s;
  }

  private int getInt(byte abyte0[]) {
    int i = 0;
    int j = 24;
    for (int k = 0; j >= 0; k++) {
      int l = abyte0[k] & 0xff;
      i += l << j;
      j -= 8;
    }
    return i;
  }

  private String padHex(String s, int i) {
    StringBuffer stringbuffer = new StringBuffer();
    if (s.length() < i) {
      for (int j = 0; j < i - s.length(); j++)
        stringbuffer.append("0");

    }
    return stringbuffer.toString();
  }

  /*
   * Convert to the standard format for GUID
   * (Useful for SQL Server UniqueIdentifiers, etc.)
   * Example: C2FEEEAC-CFCD-11D1-8B05-00600806D9B6
   */
  public String toString() {
    String raw = valueAfterMD5.toUpperCase();
    StringBuffer sb = new StringBuffer();
    sb.append("{");
    sb.append(raw.substring(0, 8));
    sb.append("-");
    sb.append(raw.substring(8, 12));
    sb.append("-");
    sb.append(raw.substring(12, 16));
    sb.append("-");
    sb.append(raw.substring(16, 20));
    sb.append("-");
    sb.append(raw.substring(20));
    sb.append("}");

    return sb.toString();
  }

  public static void main(String args[]) {
    for (int i = 0; i < 100; i++) {
      RandomGUID myGUID = new RandomGUID();
//      System.out.println("Seeding String=" + myGUID.valueBeforeMD5);
//      System.out.println("rawGUID=" + myGUID.valueAfterMD5);
      System.out.println("RandomGUID=" + myGUID.toString());
    }
  }
}