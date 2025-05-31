package com.test.device.hexagonal.infrastructure.adapters.output.persistence.entity;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date>
{
    @Override
    public Date deserialize(JsonParser jsonparser,
            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateStr = jsonparser.getText();
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}