/*
 * Copyright 2019 Hippo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package me.hippo.systems.lwjeb.publisher.impl;

import me.hippo.systems.lwjeb.publisher.TopicPublisher;
import me.hippo.systems.lwjeb.subscribe.ListenerSubscriber;
import me.hippo.systems.lwjeb.handler.MessageHandler;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hippo
 * @since 06/23/2019
 */
public final class SyncTopicPublisher extends TopicPublisher {


    public SyncTopicPublisher(ListenerSubscriber<?, ?> listenerSubscriber) {
        super(listenerSubscriber);
    }

    @Override
    public void post(Object topic) {
        List<MessageHandler> subscribers = listenerSubscriber.getEventMap().get(topic.getClass());
        if(subscribers != null) {
            for (MessageHandler subscriber : subscribers) {
                subscriber.invoke(topic);
            }
        }
    }
}
