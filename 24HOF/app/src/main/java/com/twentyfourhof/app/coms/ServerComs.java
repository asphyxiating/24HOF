package com.twentyfourhof.app.coms;


import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.twentyfourhof.app.data.MediaItem;
import com.twentyfourhof.app.data.User;

public interface ServerComs {

  public void saveItem(MediaItem item);

    public void getAllItems(FindCallback<ParseObject> callback);

    public void saveUser(User user, SaveCallback saveCallback);

    public void getAllUsers(FindCallback<ParseObject> callback);


}
