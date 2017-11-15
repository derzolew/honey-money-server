package com.honeymoney.app.config.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

@Configuration
@EnableAsync
@EnableScheduling
@Transactional
public class RemoveTokenTask {

    private static final String cronRefreshTime = "0 0 1 * * ?";
    private static final String clientId = "web";

    @Resource(name = "tokenStore")
    private JdbcTokenStore tokenStore;

    @Scheduled(cron = cronRefreshTime)
    public void removeAccessTokensTask() {
        Collection<OAuth2AccessToken> accessTokens = tokenStore.findTokensByClientId(clientId);
        for (OAuth2AccessToken accessToken : accessTokens) {
            if (accessToken.isExpired()) {
                tokenStore.removeAccessToken(accessToken);
            }
        }
    }
}
