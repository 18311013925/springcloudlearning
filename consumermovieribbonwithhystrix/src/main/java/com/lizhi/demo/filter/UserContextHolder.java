package com.lizhi.demo.filter;

import org.springframework.util.Assert;

/**
 * @author: lizhi
 * @Date: 2019/11/18 11:17
 * @Description:
 */
public class UserContextHolder {
    private static final ThreadLocal<UserContext> USER_CONTEXT = new ThreadLocal<UserContext>();

    public static final UserContext getUserContext() {
        UserContext context = USER_CONTEXT.get();

        if (context == null) {
            context = createUserContext();
            USER_CONTEXT.set(context);
        }
        return USER_CONTEXT.get();

    }

    public static final UserContext createUserContext() {
        return new UserContext();
    }


    public static final void setUserContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted ");
        USER_CONTEXT.set(context);
        
    }

    /**
     * 销毁
     */
    public static void clean() {
        USER_CONTEXT.remove();
    }
}

