package com.sbm.sevenroomstohub.serdes;

import java.util.Map;
/**
 * Copyright © 2017 Jeremy Custenborder (jcustenborder@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class JacksonSerde<T> implements Serde<T> {

    private final Serializer<T> serializer;
    private final Deserializer<T> deserializer;

    private JacksonSerde(Class<T> cls) {
        this.deserializer = new JacksonDeserializer<>(cls);
        this.serializer = new JacksonSerializer<>();
    }

    public JacksonSerde() {
        this.deserializer = new JacksonDeserializer<>();
        this.serializer = new JacksonSerializer<>();
    }

    public static final <T> JacksonSerde<T> of(Class<T> cls) {
        return new JacksonSerde<>(cls);
    }

    @Override
    public void configure(Map<String, ?> settings, boolean isKey) {
        this.serializer.configure(settings, isKey);
        this.deserializer.configure(settings, isKey);
    }

    @Override
    public void close() {
        this.deserializer.close();
        this.serializer.close();
    }

    @Override
    public Serializer<T> serializer() {
        return this.serializer;
    }

    @Override
    public Deserializer<T> deserializer() {
        return this.deserializer;
    }
}
