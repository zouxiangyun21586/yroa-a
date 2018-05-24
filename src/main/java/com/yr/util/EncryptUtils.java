package com.yr.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.crypto.Mac;
import sun.misc.BASE64Decoder;

/**
 * 各种加密工具类
 * @author 周业好
 * 2018年5月24日 上午10:49:28
 */
public class EncryptUtils {
	public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
    private static final Integer NEGATIVEONE = -1;
    private static final Integer TWO = 2;
    private static final Integer FOUR = 4;
    private static final Integer FIVE = 5;

	/**
     * MAC算法可选以下多种算法
     *
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";

    /**
     * BASE64解密
     *
     * @param key
     *            需要解密的密码字符串
     * @return byte[]
     * @throws Exception
     *             Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     *            = 需要加密的字符数组
     * @return String
     * @throws Exception
     *             Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * MD5加密
     *
     * @param data
     *            = 需要加密的字符数组
     * @return byte[]
     * @throws Exception
     *             Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();

    }

    /**
     * SHA加密
     *
     * @param data
     *            = 需要加密的字符数组
     * @return byte[]
     * @throws Exception
     *             Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }

    /**
     * 初始化HMAC密钥
     *
     * @return String
     * @throws Exception
     *             Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     *            = 密匙加密过的字符数组
     * @param key
     *            = 需要加密的字符串
     * @return byte[]
     * @throws Exception
     *             Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }

    /**
     * InputStream流转bae64
     * 
     * @param input
     *            InputStream
     * @return String
     * @throws IOException
     *             IOException
     */
    public static String imageToBase64(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] b = new byte[(TWO * FIVE * TWO * FIVE * TWO * FIVE) + (TWO * FIVE * TWO + FOUR)];
        while ((len = input.read(b, 0, b.length)) != NEGATIVEONE) {
            baos.write(b, 0, len);
        }
        byte[] buffer = baos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(buffer);
    }
	//-------------------------------
    /**
     * 进行MD5加密
     * 
     * @param info 要加密的信息
     * @return String 加密后的字符串
     */
    public String encryptToMD5(String info) {
        byte[] digesta = null;
        try {
            // 得到一个md5的消息摘要
            MessageDigest alga = MessageDigest.getInstance("MD5");
            // 添加要进行计算摘要的信息
            alga.update(info.getBytes());
            // 得到该摘要
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将摘要转为字符串
        String rs = byte2hex(digesta);
        return rs;
    }

    /**
     * 进行SHA加密
     * 
     * @param info 要加密的信息
     * @return String 加密后的字符串
     */
    public String encryptToSHA(String info) {
        byte[] digesta = null;
        try {
            // 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            // 添加要进行计算摘要的信息
            alga.update(info.getBytes());
            // 得到该摘要
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将摘要转为字符串
        String rs = byte2hex(digesta);
        return rs;
    }
    

    /**
     * 根据一定的算法得到相应的key
     * @param src
     * @return
     */
    public String getKey(String algorithm,String src){
        if(algorithm.equals("AES")){
            return src.substring(0, 16);
        }else if(algorithm.equals("DES")){
            return src.substring(0, 8);
        }else{
            return null;
        }
    }
    /**
     * 得到AES加密的key
     * @param src
     * @return
     */
    public String getAESKey(String src){
        return this.getKey("AES", src);
    }
    /**
     * 得到DES加密的key
     * @param src
     * @return
     */
    public String getDESKey(String src){
        return this.getKey("DES", src);
    }
    /**
     * 创建密匙
     * 
     * @param algorithm 加密算法,可用 AES,DES,DESede,Blowfish
     * @return SecretKey 秘密（对称）密钥
     */
    public SecretKey createSecretKey(String algorithm) {
        // 声明KeyGenerator对象
        KeyGenerator keygen;
        // 声明 密钥对象
        SecretKey deskey = null;
        try {
            // 返回生成指定算法的秘密密钥的 KeyGenerator 对象
            keygen = KeyGenerator.getInstance(algorithm);
            // 生成一个密钥
            deskey = keygen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 返回密匙
        return deskey;
    }

    /**
     * 创建一个AES的密钥
     * @return
     */
    public SecretKey createSecretAESKey() {
        return createSecretKey("AES");
    }

    /**
     * 创建一个DES的密钥
     * @return
     */
    public SecretKey createSecretDESKey() {
        return createSecretKey("DES");
    }

    /**
     * 根据相应的加密算法、密钥、源文件进行加密，返回加密后的文件
     * @param Algorithm 加密算法:DES,AES
     * @param key
     * @param info
     * @return
     */
    public String encrypt(String Algorithm, SecretKey key, String info) {
        // 定义要生成的密文
        byte[] cipherByte = null;
        try {
            // 得到加密/解密器
            Cipher c1 = Cipher.getInstance(Algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            // 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
            c1.init(Cipher.ENCRYPT_MODE, key);
            // 对要加密的内容进行编码处理,
            cipherByte = c1.doFinal(info.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回密文的十六进制形式
        return byte2hex(cipherByte);
    }

    /**
     * 根据相应的解密算法、密钥和需要解密的文本进行解密，返回解密后的文本内容
     * @param algorithm 1
     * @param key 1
     * @param sInfo 1
     * @return 1
     */
    public String decrypt(String algorithm, SecretKey key, String sInfo) {
        byte[] cipherByte = null;
        try {
            // 得到加密/解密器
            Cipher c1 = Cipher.getInstance(algorithm);
            // 用指定的密钥和模式初始化Cipher对象
            c1.init(Cipher.DECRYPT_MODE, key);
            // 对要解密的内容进行编码处理
            cipherByte = c1.doFinal(hex2byte(sInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(cipherByte);
    }

    /**
     * 根据相应的解密算法、指定的密钥和需要解密的文本进行解密，返回解密后的文本内容
     * @param algorithm 加密算法:DES,AES
     * @param sSrc 1
     * @param sKey 这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
     * @return 1
     * @throws Exception 1 
     */
    public static String decrypt(String algorithm, String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                throw new Exception("Key为空null");
            }
            // 判断采用AES加解密方式的Key是否为16位
            final int shiliu = 16;
			if (algorithm.equals("AES") && sKey.length() != shiliu) {
                throw new Exception("Key长度不是16位");
            }
            // 判断采用DES加解密方式的Key是否为8位
            final int ba = 8;
			if (algorithm.equals("DES") && sKey.length() != ba) {
                throw new Exception("Key长度不是8位");
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = hex2byte(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 根据相应的加密算法、指定的密钥、源文件进行加密，返回加密后的文件
     * @param algorithm 加密算法:DES,AES
     * @param sSrc 1
     * @param sKey 这个key可以由用户自己指定 注意AES的长度为16位,DES的长度为8位
     * @return 1
     * @throws Exception 1 
     */
    public static String encrypt(String algorithm, String sSrc, String sKey) throws Exception {
        // 判断Key是否正确
        if (sKey == null) {
            throw new Exception("Key为空null");
        }
        // 判断采用AES加解密方式的Key是否为16位
        final int shiliu = 16;
		if (algorithm.equals("AES") && sKey.length() != shiliu) {
            throw new Exception("Key长度不是16位");
        }
        // 判断采用DES加解密方式的Key是否为8位
        final int ba = 8;
		if (algorithm.equals("DES") && sKey.length() != ba) {
            throw new Exception("Key长度不是8位");
        }
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        return byte2hex(encrypted);
    }

    /**
     * 采用DES随机生成的密钥进行加密
     * @param key 1
     * @param info 1
     * @return 1
     */
    public String encryptToDES(SecretKey key, String info) {
        return encrypt("DES", key, info);
    }

    /**
     * 采用DES指定密钥的方式进行加密
     * @param key 1
     * @param info 1
     * @return 1
     * @throws Exception 1 1
     */
    public String encryptToDES(String key, String info) throws Exception {
        return encrypt("DES", info, key);
    }

    /**
     * 采用DES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样
     * @param key 1
     * @param sInfo 1
     * @return 1
     */
    public String decryptByDES(SecretKey key, String sInfo) {
        return decrypt("DES", key, sInfo);
    }

    /**
     * 采用DES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
     * @param key 1
     * @param sInfo 1
     * @return 1
     */
    public String decryptByDES(String key, String sInfo) throws Exception {
        return decrypt("DES", sInfo, key);
    }

    /**
     * 采用AES随机生成的密钥进行加密
     * @param key 1
     * @param info 1
     * @return 1
     */
    public String encryptToAES(SecretKey key, String info) {
        return encrypt("AES", key, info);
    }
    /**
     * 采用AES指定密钥的方式进行加密
     * @param key 1
     * @param info 1
     * @return 1
     * @throws Exception 1
     */
    public String encryptToAES(String key, String info) throws Exception {
        return encrypt("AES", info, key);
    }

    /**
     * 采用AES随机生成密钥的方式进行解密，密钥需要与加密的生成的密钥一样
     * @param key 1
     * @param sInfo 1
     * @return 1
     */
    public String decryptByAES(SecretKey key, String sInfo) {
        return decrypt("AES", key, sInfo);
    }
   /**
    * 
    * @author 周业好
    * 采用AES用户指定密钥的方式进行解密，密钥需要与加密时指定的密钥一样
    * @param key 1
    * @param sInfo 1
    * @return 1
    * @throws Exception 1
    */
    public String decryptByAES(String key, String sInfo) throws Exception {
        return decrypt("AES", sInfo, key);
    }

    /**
     * 十六进制字符串转化为2进制
     * 
     * @param strhex 1
     * @return 1
     */
    public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        final int two = 2;
		final int one = 1;
		final int shiliu = 16;
		if (l % two == one) {
            return null;
        }
        byte[] b = new byte[l / two];
        for (int i = 0; i != l / two; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * two, i * two + two), shiliu);
        }
        return b;
    }

    /**
     * 将二进制转化为16进制字符串
     * 
     * @param b 二进制字节数组
     * @return String
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            final int i = 0XFF;
			stmp = (java.lang.Integer.toHexString(b[n] & i));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 测试
     * 
     * @param args 1
     */
//    public static void main(String[] args) {
//        EncryptUtils encryptUtils = new EncryptUtils();
//        String source = "www.putiman.com";
//        //encryptUtils.encryptToMD5(source) //经过MD5加密
//        //encryptUtils.encryptToSHA(source) //经过SHA加密
//        
//        // 生成一个DES算法的密匙
//        SecretKey key = encryptUtils.createSecretDESKey();
//        String str1 = encryptUtils.encryptToDES(key, source); //DES加密后为
//        // 使用这个密匙解密
//        String str2 = encryptUtils.decryptByDES(key, str1); //DES解密后为
//
//        // 生成一个AES算法的密匙
//        SecretKey key1 = encryptUtils.createSecretAESKey();
//        String stra = encryptUtils.encryptToAES(key1, source); //AES加密后为
//        // 使用这个密匙解密
//        String strb = encryptUtils.decryptByAES(key1, stra); //AES解密后为
//        
//        //指定key进行加解密
//        try {
//            String aesKey = encryptUtils.getAESKey(encryptUtils.encryptToSHA(source));
//            String desKey = encryptUtils.getDESKey(encryptUtils.encryptToSHA(source));
//            String str11 = encryptUtils.encryptToDES(desKey, source); // DES加密后为
//            // 使用这个密匙解密
//            String str12 = encryptUtils.decryptByDES(desKey, str11); //DES解密后为
//
//            // 生成一个AES算法的密匙
//            String strc = encryptUtils.encryptToAES(aesKey, source); //AES加密后为
//            // 使用这个密匙解密
//            String strd = encryptUtils.decryptByAES(aesKey, strc); //AES解密后为
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
