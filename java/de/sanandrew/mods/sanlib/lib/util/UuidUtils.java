/* ******************************************************************************************************************
   * Authors:   SanAndreasP
   * Copyright: SanAndreasP
   * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
   *                http://creativecommons.org/licenses/by-nc-sa/4.0/
   *******************************************************************************************************************/
package de.sanandrew.mods.sanlib.lib.util;

import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Utility class for tasks and methods regarding UUIDs
 */
public final class UuidUtils
{
    public static final UUID EMPTY_UUID = new UUID(0, 0);

    private static final Pattern UUID_PTRN = Pattern.compile("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89ab][a-f0-9]{3}-[a-f0-9]{12}", Pattern.CASE_INSENSITIVE);

    /**
     * Checks whether or not the String represents and can be parsed to an UUID
     * @param uuid the String representing the UUID
     * @return true, if the String represents an UUID, false otherwise
     */
    public static boolean isStringUuid(String uuid) {
        return UUID_PTRN.matcher(uuid).matches();
    }

    public static boolean areUuidsEqual(Object uuid1, Object uuid2) {
        Function<Object, UUID> getUUID = (obj) -> {
            if( obj instanceof String && isStringUuid(obj.toString()) ) {
                return UUID.fromString(obj.toString());
            } else if( obj instanceof UUID ) {
                return (UUID) obj;
            } else if( obj instanceof byte[] ) {
                return UUID.nameUUIDFromBytes((byte[]) obj);
            }

            return null;
        };

        UUID uuidInst1 = getUUID.apply(uuid1);
        UUID uuidInst2 = getUUID.apply(uuid2);

        return (uuidInst1 == null && uuidInst2 == null) || (uuidInst1 != null && uuidInst1.equals(uuidInst2));
    }
}