package com.xyz.social_media.utilities;

import java.time.Instant;

public class UtilityHelper {
    public static Long getCurrentMillis() {
        return Instant.now().toEpochMilli();
    }
}
