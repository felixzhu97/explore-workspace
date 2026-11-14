package com.ai.scim.controller;

import static com.explore.provisioning.ScimProvisioningContract.IAM_USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ai.account.domain.model.Account;
import com.ai.account.domain.model.ExternalIdentity;
import com.ai.account.domain.repository.AccountRepository;
import com.ai.account.service.OwnerErasureService;
import com.ai.scim.mapper.ScimAccountMapper;
import com.ai.scim.service.ScimAccountService;
import com.ai.testsupport.AbstractDataJpaTest;
import com.explore.provisioning.ScimEndpoints;
import com.explore.provisioning.ScimFilterTranslator;
import com.explore.provisioning.ScimJsonHttpMessageConverter;
import com.explore.provisioning.ScimProvisioningContract;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Import({ScimAccountService.class, ScimAccountMapper.class, ScimFilterTranslator.class})
@DisplayName("SCIM provisioning contract")
class ScimProvisioningContractTest extends AbstractDataJpaTest {

  private static final MediaType SCIM_JSON = ScimJsonHttpMessageConverter.SCIM_JSON;

  @Autowired private ScimAccountService accountService;

  @Autowired private AccountRepository accountRepository;

  @MockitoBean private OwnerErasureService ownerErasureService;

  private MockMvc mvc;

  @BeforeEach
  void setUp() {
    mvc =
        MockMvcBuilders.standaloneSetup(new ScimUserController(accountService))
            .setControllerAdvice(new ScimExceptionHandler())
            .setMessageConverters(new ScimJsonHttpMessageConverter())
            .build();
  }

  @Test
  @DisplayName("should accept the requests IAM sends when it pushes a user")
  void shouldAcceptTheRequestsIamSendsWhenItPushesAUser() throws Exception {
    mvc.perform(lookup()).andExpect(status().isOk()).andExpect(jsonPath("$.totalResults").value(0));

    String created =
        mvc.perform(
                scim(post(ScimEndpoints.USERS))
                    .content(ScimProvisioningContract.createUserBody(IAM_USER_ID)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.externalId").value(IAM_USER_ID))
            .andExpect(jsonPath("$.userName").value(ScimProvisioningContract.USER_NAME))
            .andReturn()
            .getResponse()
            .getContentAsString();
    String id = JsonPath.read(created, "$.id");
    assertThat(accountRepository.findByIdentity(ExternalIdentity.createIamIdentity(IAM_USER_ID)))
        .get()
        .extracting(Account::getSubject, Account::getDisplayName)
        .containsExactly(IAM_USER_ID, ScimProvisioningContract.DISPLAY_NAME);

    mvc.perform(lookup())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalResults").value(1))
        .andExpect(jsonPath("$.Resources[0].id").value(id));

    mvc.perform(
            scim(put(ScimEndpoints.USERS + "/{id}", id))
                .content(ScimProvisioningContract.replaceUserBody(IAM_USER_ID)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.displayName").value(ScimProvisioningContract.RENAMED_DISPLAY_NAME));

    mvc.perform(
            scim(patch(ScimEndpoints.USERS + "/{id}", id))
                .content(ScimProvisioningContract.setActiveBody(false)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.active").value(false));

    mvc.perform(scim(delete(ScimEndpoints.USERS + "/{id}", id))).andExpect(status().isNoContent());
    verify(ownerErasureService).eraseAllForOwner(any());
    mvc.perform(lookup()).andExpect(jsonPath("$.totalResults").value(0));
  }

  private static MockHttpServletRequestBuilder lookup() {
    return scim(get(ScimEndpoints.USERS))
        .queryParam("filter", ScimProvisioningContract.externalIdFilter(IAM_USER_ID));
  }

  private static MockHttpServletRequestBuilder scim(MockHttpServletRequestBuilder request) {
    return request.contentType(SCIM_JSON).accept(SCIM_JSON);
  }
}
