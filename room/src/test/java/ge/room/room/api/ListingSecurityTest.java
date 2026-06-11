package ge.room.room.api;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ListingSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllListings_isPublic() throws Exception {
        mockMvc.perform(get("/api/listings"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteListing_withoutAuth_returnsUnauthorized() throws Exception {
        mockMvc.perform(delete("/api/listings/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    void deleteListing_withUserRole_returnsForbidden() throws Exception {
        mockMvc.perform(delete("/api/listings/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteListing_withAdminBasicAuth_returnsNotFoundWhenMissing() throws Exception {
        mockMvc.perform(delete("/api/listings/1").with(httpBasic("admin", "admin")))
                .andExpect(status().isNotFound());
    }
}
