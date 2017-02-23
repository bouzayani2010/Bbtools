package com.project.bbtools;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bbouzaiene on 23/02/2017.
 */
public class MapEntryConverter implements Converter, SingleValueConverter {


    @Override
    public String toString(Object obj) {
        return null;
    }

    @Override
    public Object fromString(String str) {
        return null;
    }



    public class java {

    }

    public boolean canConvert(Class clazz) {
        return Map.class.isAssignableFrom(clazz);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        Map<String, Object> map = (Map<String, Object>) value;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            writer.startNode(entry.getKey().toString());
            writer.setValue(entry.getValue().toString());
            writer.endNode();
        }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map<String, Object> map = new HashMap<String, Object>();

        while (reader.hasMoreChildren()) {
            reader.moveDown();
            map.put(reader.getNodeName(), reader.getValue());
            reader.moveUp();
        }
        return map;
    }
}
