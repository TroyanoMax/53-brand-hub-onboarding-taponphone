package com.am53.visadirect.fundstransactions.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils(){

    }

    public static JsonNode updateJsonData(JsonNode jsonNode, String attribute, String data) throws JsonProcessingException {

        // Crear un ObjectMapper para trabajar con JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Convertir el JSON en un JsonNode
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(jsonNode.toString());
        } catch (JsonProcessingException e) {
            log.info("Error al procesar el archivo Json: {}", e.getMessage());
        }

        // Verificar si el JsonNode es un ObjectNode
        if (rootNode != null && rootNode.isObject()) {
            // Convertir el JsonNode en un ObjectNode para facilitar las actualizaciones
            ObjectNode objectNode = (ObjectNode) rootNode;

            // Actualizar el valor del atributo 'edad'
            objectNode.put(attribute, data);

            // Convertir el ObjectNode modificado de nuevo a JSON
            String updatedJson = null;
            try {
                updatedJson = objectMapper.writeValueAsString(objectNode);
            } catch (JsonProcessingException e) {
                log.info("Error al procesar el archivo Json: {}", e.getMessage());
            }

            // log message
            log.info("Json Updated: {}", updatedJson);

            return objectMapper.readTree(updatedJson);
        }

        return jsonNode;

    }

}
