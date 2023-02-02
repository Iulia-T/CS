# Laboratory work №3. Asymmetric Ciphers

### Course: CS
### Author: Iulia Tarus, FAF-202

## Objectives:

1. Get familiar with the asymmetric cryptography mechanisms.
2. Implement an example of an asymmetric cipher.
3. As in the previous task, please use a client class or test classes to showcase the execution of your programs.


## Implementation description

For this Laboratory Work, I chose to study RSA algorithm, as one of the most expicit implementation( in my point of view) of encrypting and descrypting a message. The implementation uses Java Programming Language.


#### RSA Cipher
The RSA algorithm is a widely used public-key cryptography algorithm that is based on the mathematical properties of large prime numbers. The algorithm consists of two algorithms: Key Generation and Data Encryption and Decryption.

Key Generation: In the key generation process, two large prime numbers are selected and used to generate public and private keys. The public key is used for encrypting data and the private key is used for decrypting data.
```
KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
kpg.initialize(2048);
 KeyPair kp = kpg.generateKeyPair();
PublicKey publicKey = kp.getPublic();
PrivateKey privateKey = kp.getPrivate();
```

Data Encryption and Decryption: In the encryption process, data is encrypted with the public key. In the decryption process, the encrypted data is decrypted with the private key. Since it is computationally infeasible to factor the product of two large prime numbers, the encrypted data cannot be decrypted by anyone who does not have the private key.
```
cipher.init(Cipher.ENCRYPT_MODE, publicKey);
byte[] encryptedBytes = cipher.doFinal(message.getBytes());
BigInteger encryptedMessage = new BigInteger(encryptedBytes);
System.out.println("Encrypted message: " + encryptedMessage);
```
```
cipher.init(Cipher.DECRYPT_MODE, privateKey);
byte[] decryptedBytes = cipher.doFinal(encryptedMessage.toByteArray());
String decryptedMessage = new String(decryptedBytes);
System.out.println("Decrypted message: " + decryptedMessage);
```

## Conclusions
After performing this laboratory work, I got familiar with one of many assymetric ecryption and decryption algorithms, RSA, both theoretically and practically, implementing it in Java. The most confortable with implementing this algorithm was the existance of multiple libraries which could be used, but this laboratory showed me how to use those libraries.