package com.honeymoney.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{

    private static final String SECURITY_SCHEMA_OAUTH_2 = "oauth2";
    private static final String AUTHORIZATION_SCOPE_GLOBAL = "global";
    private static final String AUTHORIZATION_SCOPE_GLOBAL_DESC = "accessEverything";
    private static final String PACKAGE = "com.honeymoney.controller";
    private static final String TOKEN_URL = "http://localhost:8080/oauth/token";

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE))
                .paths(regex("/.*"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
                .title("Honey-Money")
                .description("REST service for Honey Money")
                .version("0.1")
                .build();
    }

    private OAuth securitySchema()
    {
        List<AuthorizationScope> authorizationScopeList = newArrayList();
        authorizationScopeList.add(new AuthorizationScope("write", "Write"));

        List<GrantType> grantTypes = newArrayList();
        ResourceOwnerPasswordCredentialsGrant basicAuth = new ResourceOwnerPasswordCredentialsGrant(TOKEN_URL);

        grantTypes.add(basicAuth);

        return new OAuth(SECURITY_SCHEMA_OAUTH_2, authorizationScopeList, grantTypes);
    }

    private SecurityContext securityContext()
    {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/**")).build();
    }

    private List<SecurityReference> defaultAuth()
    {
        final AuthorizationScope authorizationScope =
                new AuthorizationScope(AUTHORIZATION_SCOPE_GLOBAL, AUTHORIZATION_SCOPE_GLOBAL_DESC);
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections
                .singletonList(new SecurityReference(SECURITY_SCHEMA_OAUTH_2, authorizationScopes));
    }
}
