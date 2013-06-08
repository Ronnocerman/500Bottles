package com._500bottles.action;

import org.apache.commons.fileupload.FileItem;

import com._500bottles.manager.UploadManager;

public class UploadAction
{

	static public long addPhoto(FileItem item, String extension)
	{
		long id = UploadManager.addPhoto(item, extension);

		return id;
	}

	static public boolean deletePhoto(String url)
	{
		boolean bool = UploadManager.deletePhoto(url);

		return bool;
	}

}
