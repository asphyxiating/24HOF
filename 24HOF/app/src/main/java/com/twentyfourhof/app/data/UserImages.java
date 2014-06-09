package com.twentyfourhof.app.data;

import com.parse.ParseUser;

/**
 * Holding constants for field names on the cloud
 * 
 * @author Dirk Eisold <mail@dirkeisold.de>
 * 
 */
public class UserImages {

	public final static String PARSE_CLASS_NAME = "UserImages";
	public final static String KEY_THUMBNAIL_FILE_NAME_PREFIX = "thumbnail";
	public final static String FILE_NAME_SUFFIX_FIX = ".jpeg";

	public final static int KEY_PROFILE_PIC_COMPRESS_FACTOR = 90;

	public static String getProfileImageName(final ParseUser user) {
		return user.getObjectId() + System.currentTimeMillis() + FILE_NAME_SUFFIX_FIX;
	}

	public static String getProfileThumbnailImageName(final ParseUser user) {
		return user.getObjectId() + KEY_THUMBNAIL_FILE_NAME_PREFIX + System.currentTimeMillis() + FILE_NAME_SUFFIX_FIX;
	}

}
