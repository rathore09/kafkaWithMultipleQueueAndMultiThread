package com.sender.Sender.service;

import com.sender.Sender.model.Msg;

public interface SendMsgService {
    public String sendMsg(String topic,String msg);
    public String receiveMsg(String msg);

}
