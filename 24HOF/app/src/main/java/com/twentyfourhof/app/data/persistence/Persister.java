package com.twentyfourhof.app.data.persistence;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface Persister<T> {


    public void save(T object) throws JsonProcessingException;

    public T get() throws IOException;

}
