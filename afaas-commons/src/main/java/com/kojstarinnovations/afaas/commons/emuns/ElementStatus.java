package com.kojstarinnovations.afaas.commons.emuns;

/**
 * Enumerates the status of an element
 *
 * @author Augusto Vicente
 */
public enum ElementStatus {
    /**
     * Represents an element that has been migrated from another system
     */
    MIGRATED,

    /**
     * Represents an element that has been created
     */
    NEW,

    /**
     * Represents an element that has been updated
     */
    UPDATED,

    /**
     * Represents an element that has been deleted
     */
    DELETED
}
