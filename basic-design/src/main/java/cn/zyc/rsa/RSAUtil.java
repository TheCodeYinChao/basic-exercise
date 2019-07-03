package cn.zyc.rsa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;


/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 
 * @author zhuliang
 * @date 2018-3-08
 * @version 1.0
 */
public class RSAUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);

	public static final String ENCODING = "UTF-8";

	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/**
	 * 获取公钥的key对应的Encoded值
	 */
	public static final String PUBLIC_KEY_VALUE = "RSAPublicKeyEncoded";

	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * 获取私钥的key对应的Encoded值
	 */
	public static final String PRIVATE_KEY_VALUE = "RSAPrivateKeyEncoded";

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	public static final Map<String, String> KEY_MAP = new HashMap<String, String>();
	static {
		InputStream inputStream = null;
		try {
			Properties properties = new Properties();
			String url = RSAUtil.class.getClassLoader().getResource("rsa/rsa.properties").getPath();
			inputStream = new FileInputStream(new File(url));
			properties.load(inputStream);
			for (Entry<Object, Object> entry : properties.entrySet()) {
				KEY_MAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
				KEY_MAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			}
		} catch (Exception e) {
			RSAUtil.LOGGER.error("[RSA-Util] Read Rsa Error ","read rsa code error");
			throw new RuntimeException(e);
		}finally {
			try {
				if(inputStream != null){
                    inputStream.close();
                }
			} catch (IOException e) {
				e.printStackTrace();
				RSAUtil.LOGGER.error("[RSA-Util] IO Close Error ","IO Close Error");
			}
		}
	}

	/**
	 * <p>
	 * 生成密钥对(公钥和私钥)
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);

		keyMap.put(PUBLIC_KEY_VALUE, Base64.encode(publicKey.getEncoded()));
		keyMap.put(PRIVATE_KEY_VALUE, Base64.encode(privateKey.getEncoded()));
		return keyMap;
	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	private static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 使用配置文件中的公钥解密
	 * 
	 * @param encryptedData(base64编码)
	 * @return 原始数据
	 */
	public static String decryptByPrivateKey(String encryptedData) {
		try {
			byte[] decryptByPrivateKey = decryptByPrivateKey(Base64.decode(encryptedData), getPrivateKey());
			return new String(decryptByPrivateKey, ENCODING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用传入的私钥解密
	 * 
	 * @param encryptedData(base64编码)
	 *  @param privateKey
	 * @return 原始数据
	 */
	public static String decryptByPrivateKey(String encryptedData, String privateKey) {
		try {
			byte[] decryptByPrivateKey = decryptByPrivateKey(Base64.decode(encryptedData), privateKey);
			return new String(decryptByPrivateKey, ENCODING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	private static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 使用配置文件中的公钥解密
	 * 
	 * @param encryptedData(base64编码)
	 * @return 原始数据
	 */
	public static String decryptByPublicKey(String encryptedData) {
		try {
			byte[] decryptByPrivateKey = decryptByPublicKey(Base64.decode(encryptedData), getPublicKey());
			return new String(decryptByPrivateKey, ENCODING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用传入的公钥解密
	 * 
	 * @param encryptedData(base64编码)
	 * @param publicKey
	 * @return 原始数据
	 */
	public static String decryptByPublicKey(String encryptedData, String publicKey) {
		try {
			byte[] decryptByPrivateKey = decryptByPublicKey(Base64.decode(encryptedData), publicKey);
			return new String(decryptByPrivateKey, ENCODING);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	private static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 使用配置文件中的公钥加密
	 * 
	 * @param data
	 * @return RSA加密后 经Base64编码过后的结果
	 */
	public static String encryptByPublicKey(String data) {
		try {
			byte[] encryptByPublicKey = encryptByPublicKey(data.getBytes(ENCODING), getPublicKey());
			return Base64.encode(encryptByPublicKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使用传入的公钥加密
	 * 
	 * @param data
	 * @return RSA加密后 经Base64编码过后的结果
	 */
	public static String encryptByPublicKey(String data, String publicKey) {
		try {
			byte[] encryptByPublicKey = encryptByPublicKey(data.getBytes(ENCODING), publicKey);
			return Base64.encode(encryptByPublicKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>
	 * 私钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	private static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 使用配置文件中的私钥加密
	 * 
	 * @param data
	 * @return RSA加密后 经Base64编码过后的结果
	 */
	public static String encryptByPrivateKey(String data) {
		try {
			return Base64.encode(encryptByPrivateKey(data.getBytes(ENCODING), getPrivateKey()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 使传入的私钥加密
	 * 
	 * @param data
	 * @param privateKey
	 * @return RSA加密后 经Base64编码过后的结果
	 */
	public static String encryptByPrivateKey(String data, String privateKey) {
		try {
			return Base64.encode(encryptByPrivateKey(data.getBytes(ENCODING), privateKey));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64.encode(key.getEncoded());
	}

	/**
	 * 获取私钥（配置文件中的）
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey() {
		return KEY_MAP.get(PRIVATE_KEY_VALUE);
	}

	/**
	 * <p>
	 * 获取公钥
	 * </p>
	 * 
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64.encode(key.getEncoded());
	}

	/**
	 * 获取公钥（配置文件中的）
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey() {
		return KEY_MAP.get(PUBLIC_KEY_VALUE);
	}

	public static void main(String[] args) throws Exception {
		// 生成公私
		/*Map<String, Object> map = genKeyPair();
		map.forEach((key,value) -> {
			System.out.println( key + value);
		});*/


		// 私钥加密
		String epk = encryptByPrivateKey("18610431874", getPrivateKey());
		String epk2 = encryptByPrivateKey("412828199310105115", getPrivateKey());
//		System.out.println(epk);
		System.out.println(epk.length()); //密文长度172
		System.out.println(epk2.length()); //密文长度172

		// 公钥解密
		String dpk = decryptByPublicKey(epk, getPublicKey());
		String dpk2 = decryptByPublicKey(epk2, getPublicKey());
		System.out.println(dpk);
		System.out.println(dpk2);
	}
}
