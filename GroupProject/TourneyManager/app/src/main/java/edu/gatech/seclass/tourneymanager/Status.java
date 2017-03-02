package edu.gatech.seclass.tourneymanager.model;

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
}
