package edu.gatech.seclass.tourneymanager;

/**
 * Created by Yu on 3/1/2017.
 */


public enum Status {
    Setup(1),
    Ready(2),
    InProgress(3),
    Completed(4),
    Cancelled(5);

    public final int statusId;

    Status(int statusId) {
        this.statusId = statusId;
    }

    public static Status getStatus(int statusId) {
        for (Status status : Status.values()) {
            if (status.statusId == statusId) {
                return status;
            }
        }
        throw new IllegalArgumentException("No status with statusId of " + statusId);
    }
}
