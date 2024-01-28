package com.app.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.app.constants.UserConstant;

@Component
public class ImageUtil {
	public byte[] compressImage(byte[] data) {

		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outputStream.write(tmp, 0, size);
		}
		try {
			outputStream.close();
		} catch (Exception e) {
		}
		return outputStream.toByteArray();
	}

	public static byte[] decompressImage(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(tmp);
				outputStream.write(tmp, 0, count);
			}
			outputStream.close();
		} catch (Exception exception) {
		}
		return outputStream.toByteArray();
	}

	public boolean isValidImage(MultipartFile file) {
		String[] str = file.getOriginalFilename().split("\\.");
		if (str[1].equals(UserConstant.IMAGE_TYPE_JPEG) || str[1].equals(UserConstant.IMAGE_TYPE_PNG)) {
			return true;
		} else {
			return false;
		}

	}
}
