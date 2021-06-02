package com.godel.mastery.validation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.godel.mastery.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Defines rules of deserialization for LocalDate.
 */
@Component
public class CustomLocalDateDeserializer extends StdDeserializer<LocalDate> {

    public CustomLocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String date = jsonParser.getText();
        LocalDate localDate = LocalDate.parse(date);
        if (!EmployeeValidation.isDateOfBirthValid(localDate)) {
            throw new ValidationException("Date of birth not valid. You cannot create this employee.");
        }
        return localDate;
    }
}
