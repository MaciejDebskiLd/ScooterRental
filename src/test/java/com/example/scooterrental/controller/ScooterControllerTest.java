package com.example.scooterrental.controller;

import com.example.scooterrental.api.response.AddScooterResponse;
import com.example.scooterrental.api.response.CreateUserAccountResponse;
import com.example.scooterrental.model.Scooter;
import com.example.scooterrental.model.UserAccount;
import com.example.scooterrental.repository.ScooterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScooterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ScooterRepository scooterRepository;

    @Test
    public void ifAddScooterRequestContainsCorrectDataShouldReturnHttpStatus200AndAddScooter() throws Exception{
        MvcResult mvcResult = mockMvc
                .perform(
                        post("/scooter/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\n" +
                                        "\t\"modelName\": \"AVE-2\",\n" +
                                        "\t\"maxSpeed\": 12,\n" +
                                        "\t\"rentalPrice\": 3,\n" +
                                        "\t\"scooterDockId\": 1\n" +
                                        "}"
                        )
                )
                .andExpect(status().is(200))
                .andExpect(content().string(Matchers.containsString("Poprawnie dodano hulajnogę do systemu.")))
                .andExpect(content().string(Matchers.containsString("scooterId")))
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        AddScooterResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                AddScooterResponse.class
        );
        Optional<Scooter> optionalScooter = scooterRepository.findById(response.getScooterId());
        Assert.assertTrue(optionalScooter.isPresent());
    }


    @Test
    public void ifAddedScooterIsTooFastShouldReturnHttpCode400AndErrorMsg() throws Exception {
        mockMvc
                .perform(
                        post("/scooter/add")
                                .content(
                                        "{\n" +
                                                "\t\"modelName\": \"AVE-2\",\n" +
                                                "\t\"maxSpeed\": 45,\n" +
                                                "\t\"rentalPrice\": 3,\n" +
                                                "\t\"scooterDockId\": 1\n" +
                                                "}"
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(400))
                .andExpect(content().json(
                        "{\n" +
                                "\t\"errorCode\": \"Err007\",\n" +
                                "\t\"errorMsg\": \"Maksymalna predkosc hulajnogi powinna miescic sie w zakresie od 1 do 40.\",\n" +
                                "\t\"status\": \"ERROR\"\n" +
                                "}"
                        )
                )
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ifAddRequestHasInsufficientDataShouldReturnHttpCode400AndErrorMsg() throws Exception{
        mockMvc
                .perform(
                        post("/scooter/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\n" +
                                                "\t\"modelName\": \"AVE-2\",\n" +
                                                "\t\"scooterDockId\": 1\n" +
                                                "}"
                                )
                )
                .andExpect(status().is(400))
                .andExpect(content().json(
                        "{\n" +
                                "\t\"errorCode\": \"Err001\",\n" +
                                "\t\"errorMsg\": \"Dane wymagane do realizacji zadania sa niekompletne.\",\n" +
                                "\t\"status\": \"ERROR\"\n" +
                                "}"
                ));
    }
}
