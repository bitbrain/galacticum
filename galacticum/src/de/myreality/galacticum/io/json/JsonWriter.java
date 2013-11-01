/* Galacticum space game for Android, PC and browser.
 * Copyright (C) 2013  Miguel Gonzalez
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.myreality.galacticum.io.json;

import java.util.Map;

/**
 * 
 *
 * @author miguel
 * @since
 * @version
 */
public class JsonWriter {
	 
    public String write(JsonObject json) {
 
        StringBuilder builder=new StringBuilder();
        builder.append(JsonSettings.JSON_OBJECT_LEFT_BRACKET);
 
        Map<String, Object> props=json.getProperties();
 
        for (Map.Entry<String, Object> entry : props.entrySet()) {
            Object value=entry.getValue();
            if(value==null){
                value= "";
            }
            if(value instanceof String){
                String content=String.format("'%s'%s'%s'%s",entry.getKey(),JsonSettings.JSON_TOKEN_SPLIT,value,JsonSettings.JSON_COMMA);
                builder.append(content);
            }else{
 
 
                Json jsonObject=(Json)value;
 
                if(jsonObject.isJsonArray()){
                    JsonArray jsonArray=(JsonArray)jsonObject;
 
                    String content=String.format("'%s'%s%s",entry.getKey(),JsonSettings.JSON_TOKEN_SPLIT,JsonSettings.JSON_ARRAY_LEFT_BRACKET);
                    builder.append(content);
 
                    for (Object key : jsonArray.getKeys()) {
                        if(key instanceof String){
                            builder.append(String.format("'%s'",key)).append(JsonSettings.JSON_COMMA);
 
                        }else{
                            //parse json object
                            JsonObject jObject=(JsonObject) key;
                            builder.append(write(jObject)).append(JsonSettings.JSON_COMMA);
                        }
 
                    }
                    int end = builder.length() - 1;
                    if(end == 0){
                        String content2=builder.substring(0, end);
                        builder=new StringBuilder();
                        builder.append(content2);
                    }
                    builder.append(JsonSettings.JSON_ARRAY_RIGHT_BRACKET).append(JsonSettings.JSON_COMMA);
 
                }else{
                    JsonObject jObject=(JsonObject) jsonObject;
                    //parse json object
 
                    String content=String.format("'%s'%s%s%s",entry.getKey(),JsonSettings.JSON_TOKEN_SPLIT,write(jObject),JsonSettings.JSON_COMMA);
                    builder.append(content);
                }
            }
        }
 
        int end = builder.length() - 1;
        if(end == 0){
            boolean isComma=builder.charAt(end)==JsonSettings.JSON_COMMA;
            if(isComma){
                String content=builder.substring(0, end);
                return content+JsonSettings.JSON_OBJECT_RIGHT_BRACKET;
            }
        }
        builder.append(JsonSettings.JSON_OBJECT_RIGHT_BRACKET);
        return builder.toString();
 
    }
}