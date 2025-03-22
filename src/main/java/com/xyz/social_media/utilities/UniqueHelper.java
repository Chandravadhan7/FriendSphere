package com.xyz.social_media.utilities;

import java.util.UUID;

public class UniqueHelper {

    public static String getSessionId() {
        return getId("SESN");
    }

    public static String getOrderID() {
        return getId("ORDR");
    }

    private static String getId(String prefix) {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        // Regex
        return prefix + uuidAsString;
    }
}
