package com.gildedrose.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerRestTest {
    // TODO  complete unit tests for each method and limits using the mocked webServer "testRestTemplate"

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void return200WhenSendingRequestToGetItems() throws Exception {
        ResponseEntity<List> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/get-items", List.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void return200WhenSendingRequestToDegradeItem() throws Exception {
        ResponseEntity<Boolean> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/degrade/9", Boolean.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // fixme : this test only the default response value
        //assertTrue( entity.getBody());

        //TODO : add more cases
    }

}