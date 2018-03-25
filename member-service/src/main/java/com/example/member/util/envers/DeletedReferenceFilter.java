package com.example.member.util.envers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityNotFoundException;

public class DeletedReferenceFilter extends SimpleBeanPropertyFilter {

    @Override
    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer) throws Exception {

        try {
            writer.serializeAsField(pojo, jgen, prov);
        } catch (JsonMappingException jme) {
            Throwable cause = jme.getCause();
            if (cause instanceof EntityNotFoundException) {
                int idSeperatorIndex = StringUtils.indexOf(cause.getMessage(), "with id ") + 8;
                jgen.writeStringField("id", StringUtils.substring(cause.getMessage(), idSeperatorIndex));
                jgen.writeStringField("status", "[DELETED]");
                jgen.writeEndObject();
            }
        }
    }
}
