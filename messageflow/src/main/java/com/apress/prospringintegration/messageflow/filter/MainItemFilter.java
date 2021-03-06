/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apress.prospringintegration.messageflow.filter;

import com.apress.prospringintegration.messageflow.domain.MarketItem;
import com.apress.prospringintegration.messageflow.domain.MarketItemCreator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

public class MainItemFilter {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("filter-item.xml");

        MessageChannel channel =
                context.getBean("marketItemChannel", MessageChannel.class);
        MarketItemCreator marketItemCreator =
                context.getBean("marketItemCreator", MarketItemCreator.class);

        for (MarketItem marketItem : marketItemCreator.getMarketItems()) {
            channel.send(MessageBuilder.withPayload(marketItem)
                    .setHeader("ITEM_TYPE", marketItem.getType()).build());
        }
    }
}
