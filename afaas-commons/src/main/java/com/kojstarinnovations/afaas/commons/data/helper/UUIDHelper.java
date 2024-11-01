package com.kojstarinnovations.afaas.commons.data.helper;

import java.util.UUID;

/**
 * Class to generate UUIDs with a prefix and a length
 *
 * @Author: Augusto Vicente
 */
public class UUIDHelper {

    public static String generateUUID(String prefix, Integer length) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        if (prefix != null && !prefix.isEmpty()) {
            uuid = prefix + uuid;
        }
        if (length != null && length > 0 && length < uuid.length()) {
            uuid = uuid.substring(0, length);
        }
        return uuid;
    }
}
