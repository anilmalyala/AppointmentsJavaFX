package com.csia.anish.util;

import com.csia.anish.data.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Session {
    private static User user;

    private Session(User user){
        this.user=user;
    }

    public static User getUser(){
        return user;
    }

    private static Session session =null;

    public static Session createSession(User user) {
        if (session==null) {
            session = new Session(user);
        }
        return session;
    }

    public static Session getSession(){
       return session;
    }

    public static void logout() {
        session=null;
        user=null;
    }
}


