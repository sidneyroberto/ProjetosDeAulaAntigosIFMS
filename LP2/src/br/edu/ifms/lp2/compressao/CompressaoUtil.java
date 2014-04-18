package br.edu.ifms.lp2.compressao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressaoUtil {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		int diaHoje = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(diaHoje);
	}

	private CompressaoUtil() {

	}

	public static String descomprimeComGZIP(byte[] bytes, String encoding) {
		String decompressedString = "";
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			GZIPInputStream gzip = new GZIPInputStream(bais);
			Reader reader = new InputStreamReader(gzip, encoding);
			StringBuffer sbuf = new StringBuffer();
			char[] buffer = new char[32 * 1024];
			int nread;
			while ((nread = reader.read(buffer)) >= 0) {
				sbuf.append(buffer, 0, nread);
			}
			decompressedString = sbuf.toString();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decompressedString;
	}

	public static String geraHashMD5(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes("UTF8"));
			byte bytes[] = digest.digest();
			String resultado = "";
			for (int i = 0; i < bytes.length; i++) {
				// Processo para converter os bytes em uma string de caracteres
				// hexadecimais
				resultado += Integer.toHexString(
						(0x000000ff & bytes[i]) | 0xffffff00).substring(6);
			}
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] comprimeComGZIP(String str, String encoding) {
		try {
			if (str == null || str.length() == 0) {
				return null;
			}
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			Writer writer = new OutputStreamWriter(gzip, encoding);
			writer.write(str);
			writer.close();
			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
