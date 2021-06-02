package at.technikumwien.brunner.personwebapp;

import at.technikumwien.brunner.personwebapp.controller.PersonResource;
import at.technikumwien.brunner.personwebapp.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// integration test (kein Unit Test) dank MockMVC, es werden HTTP Requests simuliert
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("extended")
public class PersonResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRetrieve() throws Exception {
        long id = 1L;
        var requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/resources/persons/"+ id)
        .accept(MediaType.APPLICATION_JSON);
        // variant 1: assert uses expect method
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testRetrieveAll() throws Exception {
        // gets all activated persons
        var requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8080/resources/persons")
                .accept(MediaType.APPLICATION_JSON);
        // variant 2: with the assert methods
        var response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        var jsonString = response.getContentAsString();
        var persons = objectMapper.readValue(
                jsonString,
                new TypeReference<List<Person>>() {}
        );
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(response.getContentType()).containsIgnoringCase(MediaType.APPLICATION_JSON_VALUE);
        assertEquals(2,persons.size());
    }

    // TODO add more tests here
}
