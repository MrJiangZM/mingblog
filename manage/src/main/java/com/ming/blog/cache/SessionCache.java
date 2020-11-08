package com.ming.blog.cache;

import com...enums.CachePrefixEnum;
import com...util.SessionVo;
import org.springframework.stereotype.Component;

@Component
public class SessionCache extends BaseValueCache<String, SessionVo> {
    @Override
    protected String getPrefix() {
        return CachePrefixEnum.MANAGE.getPrefix() + "session:";
    }

    @Override
    protected Long getTTL() {
        return 30 * 24 * 3600L;
    }
}
