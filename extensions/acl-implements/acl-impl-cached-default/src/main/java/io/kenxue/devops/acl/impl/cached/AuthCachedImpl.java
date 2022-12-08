package io.kenxue.devops.acl.impl.cached;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.kenxue.devops.acl.cache.CacheService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 缺省缓存实现,可自行考虑redis,memcached等中间件
 */
@Service
public class AuthCachedImpl implements CacheService<String,Object> , InitializingBean {

    @Value("${jwt.token-expire-time:60}")
    private Integer jwtTokenExpireTime;

    private Cache<String, Object> cached;

    private final UserDetailsService userDetailsService;

    public AuthCachedImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @SneakyThrows
    @Override
    public Object get(String key) {
        return cached.get(key, () -> {
            UserDetails userDetails = userDetailsService.loadUserByUsername(key);
            return Optional.ofNullable(userDetails).map(v->v.getAuthorities()).orElse(null);
        });
    }

    @Override
    public void set(String key, Object value) {
        cached.put(key,value);
    }

    @Override
    public void set(String s, Object o, Long expire, TimeUnit timeUnit) {

    }

    @Override
    public void afterPropertiesSet() {
        cached = CacheBuilder.newBuilder().expireAfterAccess(jwtTokenExpireTime, TimeUnit.MINUTES).build();
    }
}
