package com.example.petclinic.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.LongNode;

import java.io.IOException;

public class OwnerDeserializer extends StdDeserializer<Owner> {

    public OwnerDeserializer() {
        this(null);
    }

    public OwnerDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Owner deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);

        Long id = (Long) ((LongNode) node.get("id")).numberValue();
        String name = node.get("name").asText();
        String address = node.get("address").asText();
        String city = node.get("city").asText();
        String phoneNumber = node.get("phoneNumber").asText();

        Owner owner = Owner.builder().withId(id).withName(name).withAddress(address).withCity(city).withPhoneNumber(phoneNumber).build();
        return owner;

    }
}
