package org.delivery;

import java.util.HashMap;
import java.util.Map;

public class LoginSession {

    private static final Map<String, LoginSessionData> sessions = new HashMap<>();

    public static void createSession(String dataStored, LoginSessionData loginSessionData) {
        sessions.put(dataStored, loginSessionData);
    }

    public static LoginSessionData getSession(String dataSaved){
        return sessions.get(dataSaved);
    }

    public static void removeSession(String dataSaved) {
        sessions.remove(dataSaved);
    }
}
