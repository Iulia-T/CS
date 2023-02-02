package Hashing;

public class bcrypt {
    
    private static final int GENSALT_DEFAULT_LOG2_ROUNDS = 10;
  private static final int BCRYPT_SALT_LEN = 16;
  private static final Encoder BASE64_ENCODER = Base64.getEncoder();
  private static final char[] BCRYPT_ITOA64 =
      "./ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
  private static final int[] ITOA64_ORD = new int[128];

  static {
    for (int i = 0; i < ITOA64_ORD.length; i++) {
      ITOA64_ORD[BCRYPT_ITOA64[i]] = i;
    }
  }

  public static String hash(String password) {
    return hash(password, GENSALT_DEFAULT_LOG2_ROUNDS);
  }

  public static String hash(String password, int logRounds) {
    byte[] salt = new byte[BCRYPT_SALT_LEN];
    new SecureRandom().nextBytes(salt);
    byte[] passwordBytes = password.getBytes();

    byte[] hash = cryptRaw(passwordBytes, salt, logRounds);
    return "$2a$" + logRounds + "$" + encodeBase64(salt) + encodeBase64(hash);
  }

  private static byte[] cryptRaw(byte[] password, byte[] salt, int logRounds) {
    byte[] hash = salt.clone();
    byte[] passwordWithSalt = new byte[password.length + salt.length];
    System.arraycopy(password, 0, passwordWithSalt, 0, password.length);
    System.arraycopy(salt, 0, passwordWithSalt, password.length, salt.length);

    for (int i = 0; i < 1 << logRounds; i++) {
      hash = hash(hash, passwordWithSalt);
    }
    return hash;
  }

  private static byte[] hash(byte[] state, byte[] data) {
    byte[] block = new byte[state.length];
    int[] schedule = new int[state.length];
    for (int i = 0; i < state.length; i++) {
      block[i] = state[i];
    }

    for (int i = 0; i < state.length; i++) {
      schedule[i] = block[i] & 0xff | (block[i + 1] & 0xff) << 8;
    }

    for (int i = 0; i < state.length; i++) {
      block[i % 4] ^= (schedule[i] ^= (schedule[i] >>> 24));
    }

    for (int i = 0; i < state.length; i++) {
      block[i % 4] ^= (schedule[i] ^= (schedule[i] << 16));
    }

    for (int i = 0; i < state.length; i++)
    byte[] result = new byte[state.length + data.length];
    System.arraycopy(state, 0, result, 0, state.length);
    System.arraycopy(data, 0, result, state.length, data.length);
    for (int i = 0; i < state.length; i += 4) {
      int j = schedule[i] + state[i % 4];
      result[i] = (byte) (j & 0xff);
      result[i + 1] = (byte) (j >>> 8 & 0xff);
    }
    for (int i = 0; i < state.length; i += 4) {
      int j = schedule[i + 1] + state[(i + 1) % 4];
      result[i + 2] = (byte) (j & 0xff);
      result[i + 3] = (byte) (j >>> 8 & 0xff);
    }
    return result;
  }

  private static String encodeBase64(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i += 3) {
      int j = ((bytes[i] & 0xff) << 16)
          + ((bytes[i + 1] & 0xff) << 8)
          + (bytes[i + 2] & 0xff);
      sb.append(BCRYPT_ITOA64[j >>> 18 & 63]);
      sb.append(BCRYPT_ITOA64[j >>> 12 & 63]);
      sb.append(BCRYPT_ITOA64[j >>> 6 & 63]);
      sb.append(BCRYPT_ITOA64[j & 63]);
    }
    return sb.toString();
  }

  private static byte[] decodeBase64(String string) {
    byte[] result = new byte[string.length() * 3 / 4];
    int j = 0;
    for (int i = 0; i < string.length(); i++) {
      int c = ITOA64_ORD[string.charAt(i)];
      result[j++] = (byte) (c << 2 & 0xff);
      result[j++] = (byte) (c >>> 4 & 0xff);
      result[j++] = (byte) (c >>> 10 & 0xff);
    }
    return result;
  }

  public static boolean verify(String password, String hash) {
    String[] parts = hash.split("\\$");
    if (parts.length != 4) {
      return false;
    }
    int logRounds = Integer.parseInt(parts[2]);
    byte[] salt = decodeBase64(parts[3]);
    byte[] passwordBytes = password.getBytes();
    byte[] hash1 = cryptRaw(passwordBytes, salt, logRounds);
    return hash.equals("$2a$" + logRounds + "$" + encodeBase64(salt) + encodeBase64(hash1));
  }
}
