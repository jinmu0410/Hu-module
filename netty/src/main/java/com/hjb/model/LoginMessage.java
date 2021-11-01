package com.hjb.model;

import com.hjb.model.Message;
import lombok.Data;

@Data
public class LoginMessage extends Message {

    private int messageType=1;
}
