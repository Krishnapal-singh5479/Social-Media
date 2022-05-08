package com.social.media.application.model;

import lombok.Data;
import java.io.Serializable;

@Data
// It creates the composite key for following table.
public class FollowingKey implements Serializable {
    private long userId;
    private long gotId;
}
