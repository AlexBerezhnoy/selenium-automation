package io.react.realworld.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.auto.model.User;
import com.hillel.auto.utils.UserData;
import org.testng.annotations.Test;

public class JsonSerAndDeserExample {

    @Test
    public void simpleJsonTest() throws JsonProcessingException {
        User randomUser = UserData.randomUser();
        System.out.println(randomUser);
        ObjectMapper mapper = new ObjectMapper();
        String valueAsString = mapper.writeValueAsString(randomUser);
        System.out.println(valueAsString);

        JsonNode jsonNode = mapper.readTree(valueAsString);
        System.out.println(jsonNode);
        System.out.println(jsonNode.get("email"));

        User readValue = mapper.readValue(valueAsString, User.class);
        System.out.println(readValue);

    }
}
