package com.github.blogw.event4springboot.event;

import lombok.Value;

@Value
public class InvitationAcceptEvent {
    private final String customerId;
}
