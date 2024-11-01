package com.kojstarinnovations.afaas.commons.emuns;

/**
 * Enumerates the status of a transaction
 *
 * @author Augusto Vicente
 */
public enum TransactionStatus {

    /**
     * Represents a pending transaction
     */
    PENDING,

    /**
     * Represents an approved transaction
     */
    APPROVED,

    /**
     * Represents a rejected transaction
     */
    REJECTED,

    /**
     * Represents a suspended transaction
     */
    CANCELLED,

    /**
     * Represents an expired transaction
     */
    COMPLETED,

    /**
     * Represents a cancelled transaction
     */
    REVERSED
}
