
package com.example.traffic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TrafficLightIntegrationTest {

 @Autowired
 private MockMvc mockMvc;

 @Test
 void fullFlowTest() throws Exception{
   mockMvc.perform(post("/traffic/create/int1")).andExpect(status().isOk());

   mockMvc.perform(post("/traffic/int1/change")
     .param("direction","NORTH_SOUTH")
     .param("color","GREEN")).andExpect(status().isOk());

   mockMvc.perform(post("/traffic/int1/change")
     .param("direction","EAST_WEST")
     .param("color","GREEN")).andExpect(status().isConflict());
 }
}
