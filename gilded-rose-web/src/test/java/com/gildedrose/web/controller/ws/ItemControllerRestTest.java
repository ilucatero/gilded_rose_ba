package com.gildedrose.web.controller.ws;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.service.ItemService;
import com.gildedrose.web.adapter.ItemAdapter;
import com.gildedrose.web.dto.ItemDTO;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@ContextConfiguration({ "/spring-bean-web-conf-test.xml" })
@ComponentScan("com.gildedrose.web.controller")
@ActiveProfiles("dev")
@WebMvcTest(value = ItemController.class, secure = false)
@AutoConfigureMockMvc
public class ItemControllerRestTest {
    // TODO  complete unit tests for each method and limits using the mocked webServer "testRestTemplate"

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService itemService;
    @MockBean
    protected ItemAdapter itemAdapter;

    private final String ITEMS_REST_MAPPING = "/items";

    @Test
    public void return200WhenSendingRequestToGetItems() throws Exception {
        Item expectedItem = new Item(1,"Conjured Mana Cake", 3, 6, "conjured");
        ItemDTO expectedItemDto = new ItemDTO(1, "Conjured Mana Cake", 3, 6, "conjured");

        // given
        BDDMockito.given(itemService.getItems()).willReturn( Arrays.asList(expectedItem));
        BDDMockito.given(itemAdapter.toDto(ArgumentMatchers.any())).willReturn(expectedItemDto);

        // when
        mvc.perform(MockMvcRequestBuilders.get(ITEMS_REST_MAPPING + "/get-items")
                .contentType(MediaType.APPLICATION_JSON))
         // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(expectedItem.name)));

    }

    @Test
    public void return200WhenSendingRequestToDegradeItem() throws Exception {
        // given
        BDDMockito.given(itemService.degrade(ArgumentMatchers.anyLong())).willReturn(true);

        // when
        mvc.perform(MockMvcRequestBuilders.patch(ITEMS_REST_MAPPING + "/degrade/1")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)));

    }

    @Test
    @Ignore // FIXME: modify functionality to make it work
    public void return404WhenSendingRequestToDegradeItemThatDoesntExist() throws Exception {
        int expectedCode = 404;
        // given
        BDDMockito.given(itemService.degrade(ArgumentMatchers.anyLong())).willReturn(false);

        // when it doesn't exist
        mvc.perform(MockMvcRequestBuilders.patch(ITEMS_REST_MAPPING + "/degrade/"+Long.MAX_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(MockMvcResultMatchers.status().is(expectedCode));

        // when invalid ID (long negative number)
        mvc.perform(MockMvcRequestBuilders.patch(ITEMS_REST_MAPPING + "/degrade/"+Long.MIN_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(MockMvcResultMatchers.status().is(expectedCode));
    }

    @Test
    public void return200WhenSendingRequestToGetSingleItem(){
        // TODO: test get single item with limit cases
    }

    @Test
    public void return404WhenSendingRequestToGetSingleItemThatDoesntExist(){
        // TODO: test get single item with no passed id
    }

}