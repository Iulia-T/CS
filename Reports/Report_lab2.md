# Laboratory work №2. Symmetric Ciphers. Stream Ciphers. Block Ciphers.

### Course: CS
### Author: Iulia Tarus, FAF-202

## Objectives:

1. Get familiar with the symmetric cryptography, stream and block ciphers.
2. Implement an example of a stream cipher.
3. Implement an example of a block cipher.
4. The implementation should, ideally follow the abstraction/contract/interface used in the previous laboratory work.
5. Please use packages/directories to logically split the files that you will have.
6. As in the previous task, please use a client class or test classes to showcase the execution of your programs.


## Implementation description

Symmetric Encryption is the most basic and old method of encryption. It uses only one key for the process of both the encryption and decryption of data. Thus, it is also known as Single-Key Encryption.


#### Stream Cipher
A stream cipher is an encryption technique that works byte by byte to transform plain text into code that's unreadable to anyone without the proper key. Stream ciphers are linear, so the same key both encrypts and decrypts messages. 

RC4 is a stream cipher and variable-length key algorithm. This algorithm encrypts one byte at a time (or larger units at a time).
```
static int [] KSA(int[] S, final String key) {
        //initialization of the initial state S with values ​​from 0 to -1
        for (int i = 0; i < N; ++i) {
            S[i] = i;
        }
        //initialization of the iterator j with the initial value and length of the text kLen
        int j = 0;
        int kLen = key.length();
        //repeat (N-1) times the permutation in state S
        for (int i = 0; i < N; ++i) {
            //defining the new value of the iterator j
            j = (j + S[i] + (int)key.charAt(i % kLen)) % N;
            //permutation in state S with iterators i and j using a variable temp
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }

        return S;
    }
```

A key input is pseudorandom bit generator that produces a stream 8-bit number that is unpredictable without knowledge of input key. 
```
    static String PRGA(int[] S, String keyStream, final int textLen){
        //initialization of iterators with an initial value
        int i = 0, j = 0;
        for (int k = 0; k < textLen; ++k) {
            //determination of new values ​​of iterators i and j
            i = (i + 1) % N;
            j = (j + S[i]) % N;
            //permutation in state S with iterators i and j using temp variable
            temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            //key flow generation
            keyStream += (char) ((S[(S[i] + S[j]) % N]));
        }
```

The output of the generator is called key-stream, is combined one byte at a time with the plaintext stream cipher using X-OR operation.
```
//initialization of ciphertext/decrypted text by XOR (^) operation between input/ciphertext and key stream
        for (int i = 0; i < text.length(); ++i) {
           result += (char)(text.charAt(i)^keyStream.charAt(i));
        }

```

#### Block Cipher
A block cipher takes a block of plaintext bits and generates a block of ciphertext bits, generally of same size. The size of block is fixed in the given scheme. The choice of block size does not directly affect to the strength of encryption scheme. The strength of cipher depends up on the key length.

DES is a block cipher and encrypts data in blocks of size of 64 bits each, which means 64 bits of plain text go as the input to DES, which produces 64 bits of ciphertext.
```
		String encrypt(String plainText, String key)
		{
			int i;
			// get round keys
			String keys[] = getKeys(key);

			// initial permutation
			plainText = permutation(constants.IP, plainText);
			System.out.println("After initial permutation: "
							+ plainText.toUpperCase());
			System.out.println(
				"After splitting: L0="
				+ plainText.substring(0, 8).toUpperCase()
				+ " R0="
				+ plainText.substring(8, 16).toUpperCase()
				+ "\n");

			// 16 rounds
			for (i = 0; i < 16; i++) {
				plainText = round(plainText, keys[i], i);
			}

			// 32-bit swap
			plainText = plainText.substring(8, 16)
						+ plainText.substring(0, 8);
			System.out.println("32bitswap: "+plainText);

			// final permutation
			plainText = permutation(constants.IP1, plainText);
			return plainText;
		}
```

The same algorithm and key are used for encryption and decryption, with minor differences. The key length is 56 bits. 

## Conclusions
After performing this laboratory work, I got familiar with 2 symmetric ciphers, RC4 (stream cipher) and DSA (block cipher), both theoretically and practically, implementing it in Java. There are 2 different algorithms and this laboratory work helped me to discover how there are implemented.