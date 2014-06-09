package com.twentyfourhof.app.data.persistence;

/**
 * Created by kannzaubern on 22.05.14.
 */

import android.content.SharedPreferences;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.twentyfourhof.app.data.User;

import java.io.IOException;

public class UserPersister implements Persister<User> {
    private static final String USER = "user";

    @Inject
    private SharedPreferences sharedPreferences;
    @Inject
    private ObjectMapper objectMapper;

    public UserPersister(){};

    @Override
    public void save(User user) throws JsonProcessingException {
        sharedPreferences.edit().putString(USER, objectMapper.writeValueAsString(user)).commit();
    }

    @Override
    public User get() throws IOException {
        return objectMapper.readValue(sharedPreferences.getString(USER, ""), User.class);
    }

}

