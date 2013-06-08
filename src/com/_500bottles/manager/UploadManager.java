package com._500bottles.manager;

import java.io.File;
import java.util.Random;

import org.apache.commons.fileupload.FileItem;

import com._500bottles.da.internal.UploadDAO;

public class UploadManager
{

	static public long addPhoto(FileItem item, String extension)
	{
		long id = -1;
		String filename = Long.toString(System.currentTimeMillis());

		Random rand = new Random();
		int min = 0;
		int max = 900;
		int randomNum = rand.nextInt(max - min + 1) + min;
		filename += randomNum;
		filename += extension;

		File uploadedFile = new File("uploads", filename);
		try
		{
			item.write(uploadedFile);
			id = UploadDAO.addPhoto("/uploads/" + filename);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return id;
	}

	static public boolean deletePhoto(String filename)
	{
		boolean bool = UploadDAO.deletePhoto(filename);
		return bool;
	}
}
