package com.ming.blog.cache;

import com...enums.CachePrefixEnum;
import org.springframework.stereotype.Component;

@Component
public class UserSessionCache extends BaseHashCache<String, String, String> {
    @Override
    protected String getPrefix() {
        return CachePrefixEnum.MANAGE.getPrefix() + "user_session";
    }

    @Override
    protected Long getTTL() {
        return 30 * 24 * 3600L;
    }
}
