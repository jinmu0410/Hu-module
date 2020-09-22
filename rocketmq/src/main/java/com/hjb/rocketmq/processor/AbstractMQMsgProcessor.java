package com.hjb.rocketmq.processor;

import com.hjb.rocketmq.common.MQConsumeResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Arrays;
import java.util.List;

/***
 * 所有消息处理继承该类
 */
public abstract class AbstractMQMsgProcessor implements MQMsgProcessor {


    @Override
    public MQConsumeResult handle(String topic, String tag, List<MessageExt> msgs) {
        MQConsumeResult mqConsumeResult = new MQConsumeResult();
        /**可以增加一些其他逻辑*/
        for (MessageExt messageExt : msgs) {
            mqConsumeResult = this.consumeMessage(tag, messageExt.getKeys() == null ? null : Arrays.asList(messageExt.getKeys().split(MessageConst.KEY_SEPARATOR)), messageExt);
        }
        /**可以增加一些其他逻辑*/
        return mqConsumeResult;
    }

    /***
     * 消息某条消息
     * @param tag
     * @param keys
     * @param messageExt
     * @return
     */
    protected abstract MQConsumeResult consumeMessage(String tag, List<String> keys, MessageExt messageExt);
}
