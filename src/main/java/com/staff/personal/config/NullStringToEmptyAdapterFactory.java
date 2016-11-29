package com.staff.personal.config;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mtustanovskyy on 11/29/16.
 */
public  class NullStringToEmptyAdapterFactory<T> extends TypeAdapter<Object> {


    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            return (HibernateProxy.class.isAssignableFrom(type.getRawType()) ? (TypeAdapter<T>) new NullStringToEmptyAdapterFactory(gson) : null);
        }
    };

    private final Gson context;

    private NullStringToEmptyAdapterFactory(Gson context) {
        this.context = context;
    }

    public String read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return "";
        }
        return reader.nextString();
    }
    public void write(JsonWriter jsonWriter, Object o) throws IOException {
        jsonWriter.beginObject();
        for (Field field : o.getClass().getDeclaredFields())
        {
            Object fieldValue = runGetter(field, o);
            jsonWriter.name(field.getName());
            if (fieldValue == null)
            {
                jsonWriter.endArray();
                jsonWriter.nullValue();
            }
            else {
                jsonWriter.value(fieldValue.toString());
            }
        }
        jsonWriter.endObject();
    }

    public static Object runGetter(Field field, Object o)
    {
        // MZ: Find the correct method
        for (Method method : o.getClass().getMethods())
        {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
            {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
                {
                    try
                    {
                        return method.invoke(o);
                    }
                    catch (IllegalAccessException e)
                    { }
                    catch (InvocationTargetException e)
                    { }
                }
            }
        }
        return null;
    }
}

