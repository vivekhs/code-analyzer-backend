/**
 * 
 */
package com.hackathon.util;

import java.util.UUID;

/**
 * @author vivekhs
 *
 */
public class CommonUtils {

    /**
     * this will generate a UUID
     * 
     * @return unique id
     */
    public static final String getUUID() {
        return UUID.randomUUID().toString();
    }

}
