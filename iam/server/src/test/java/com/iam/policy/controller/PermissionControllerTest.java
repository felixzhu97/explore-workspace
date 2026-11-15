package com.iam.policy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.iam.common.domain.model.Resource;
import com.iam.common.domain.model.Scope;
import com.iam.policy.domain.model.Permission;
import com.iam.policy.service.PermissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@DisplayName("Permission catalog API")
class PermissionControllerTest {

  @Mock private PermissionService permissionService;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new PermissionController(permissionService)).build();
  }

  @Test
  @DisplayName("should return permission when path variable matches")
  void shouldReturnPermissionWhenPathVariableMatches() throws Exception {
    Permission permission = mock(Permission.class);
    when(permission.getCode()).thenReturn("read:chat");
    when(permission.getOauthScope()).thenReturn(Scope.parseScope("read:chat"));
    when(permission.getModule()).thenReturn("chat");
    when(permission.getAction()).thenReturn(new com.iam.common.domain.model.Permission("read"));
    when(permission.getResource()).thenReturn(new Resource("resource"));
    when(permission.getDescription()).thenReturn("Read chat");
    when(permissionService.get("read:chat")).thenReturn(permission);

    String body =
        mockMvc
            .perform(get("/api/v1/permissions/{scope}", "read:chat"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    assertThat(body).contains("read:chat");
  }
}
