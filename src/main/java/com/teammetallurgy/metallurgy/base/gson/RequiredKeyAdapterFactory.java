package com.teammetallurgy.metallurgy.base.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;

public class RequiredKeyAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                if (value != null) {

                    Field[] fields = value.getClass().getDeclaredFields();

                    for (int i = 0; i < fields.length; i++) {
                        if (fields[i]
                                .isAnnotationPresent(JsonRequired.class)) {
                            validateNullValue(value, fields[i]);
                        }

                    }
                }
                delegate.write(out, value);
            }

            private <T> void validateNullValue(T value, Field field) {
                field.setAccessible(true);
                Class t = field.getType();
                Object v = null;
                try {
                    v = field.get(value);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalArgumentException(e);
                }
                if (t == boolean.class && Boolean.FALSE.equals(v)) {
                    throw new IllegalArgumentException(field + " is null");
                } else if (t.isPrimitive()
                        && ((Number) v).doubleValue() == 0) {

                    throw new IllegalArgumentException(field + " is null");
                } else if (!t.isPrimitive() && v == null) {
                    throw new IllegalArgumentException(field + " is null");

                }
            }

            @Override
            public T read(JsonReader in) throws IOException {
                return delegate.read(in);
            }

        };
    }
}