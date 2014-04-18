package br.edu.ifms.extensao.testemongodb.util;

import java.security.MessageDigest;

public class CompressionUtil {

	public static String generateMd5Hash(String str) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(str.getBytes("UTF8"));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00)
						.substring(6);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
