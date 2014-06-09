package com.twentyfourhof.app.coms;

import android.graphics.BitmapFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import com.parse.*;
import com.twentyfourhof.app.data.MediaItem;
import com.twentyfourhof.app.data.User;
import com.twentyfourhof.app.data.persistence.UserPersister;
import com.twentyfourhof.app.ui.events.ItemEventResult;
import roboguice.event.EventManager;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerComClass {

    public static final String PARSE_MEDIAITEM_CLASS = "item";
    public static final String PARSE_USER_CLASS = "user";

    public static final String PARSE_NAME = "name";
    public static final String PARSE_PICTURE_URL = "parsePictureURL";
    public static final String PARSE_USER = "user";
    public static final String PARSE_TEXT = "text";
    public static final String PARSE_USER_NAME = "name";
    public static final String PARSE_USER_AGE = "age";
    private static final String PARSE_TIMESTAMP = "timestamp";

    private EventManager eventManager;
    private UserPersister userPersister;

    @Inject
    public ServerComClass(EventManager eventManager, UserPersister userPersister) {
        this.eventManager = eventManager;
        this.userPersister = userPersister;
    }


    private FindCallback<ParseObject> callback = new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> parseObjects, ParseException e) {
            List<MediaItem> items = new ArrayList<MediaItem>(parseObjects.size());
            for (ParseObject parseObject : parseObjects) {
                MediaItem item = new MediaItem();
                item.setName(parseObject.getString(PARSE_NAME));
                item.setPictureURL(parseObject.getString(PARSE_PICTURE_URL));
                item.setText(parseObject.getString(PARSE_TEXT));
                // item.setTimeStamp(parseObject.getLong(PARSE_TIMESTAMP));
                ParseObject parseUser = parseObject.getParseObject("parent");
                // item.setUser(convertToUser(parseUser));
                items.add(item);
            }
            eventManager.fire(new ItemEventResult(items));
        }
    };

    private FindCallback<ParseObject> userCallback = new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> parseObjects, ParseException e) {
            List<MediaItem> items = new ArrayList<MediaItem>(parseObjects.size());
            for (ParseObject parseObject : parseObjects) {
                User user = new User();
                user.setName(parseObject.getString(ServerComClass.PARSE_NAME));
                user.setAge(parseObject.getString(ServerComClass.PARSE_USER_AGE));
                try {
                    parseObject.pin();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
            eventManager.fire(new ItemEventResult(items));
        }
    };


    public void saveItem(MediaItem item) {
        ParseObject parseObject = new ParseObject(PARSE_MEDIAITEM_CLASS);
        parseObject.put(PARSE_NAME, item.getName());
        parseObject.put(PARSE_PICTURE_URL, item.getPictureURL());
      //  parseObject.put("parent", ParseObject.createWithoutData(PARSE_USER_CLASS, item.getUser().getId()));
        parseObject.put(PARSE_TEXT, item.getText());
     //   parseObject.put(PARSE_TIMESTAMP, item.getTimeStamp());
        parseObject.saveInBackground();
    }

    public void getAllItems() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(PARSE_MEDIAITEM_CLASS);
        query.orderByAscending(PARSE_TIMESTAMP);
        query.include(PARSE_USER_CLASS);
        query.findInBackground(callback);
    }


    public void saveUser() {
        try {
            final User user = userPersister.get();
            File file = new File(user.getImagePath());
            int size = (int) file.length();
            byte[] bytes = new byte[size];
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
            final ParseFile parseFile = new ParseFile(file.getName(), bytes);
            parseFile.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    try {
                        final User user = userPersister.get();
                        final ParseObject parseObject = new ParseObject(PARSE_USER_CLASS);
                        parseObject.put(PARSE_USER_NAME, user.getName());
                        parseObject.put(PARSE_USER_AGE, user.getAge());
                        parseObject.put("photo", parseFile);
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                user.setId(parseObject.getObjectId());
                                try {
                                    userPersister.save(user);
                                } catch (JsonProcessingException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getAllUsers(FindCallback<ParseObject> callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(PARSE_USER_CLASS);
        query.findInBackground(callback);
    }

    private User convertToUser(ParseObject parseObject) {
        try {
            parseObject.fetchIfNeeded();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = new User ();
        user.setName(parseObject.getString(PARSE_USER_NAME));
        user.setAge(parseObject.getString(PARSE_USER_AGE));
        ParseFile parseFile = parseObject.getParseFile("photo");
        try {
            byte[] bytes = parseFile.getData();
            user.setBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }

}
