package io.github.lipanre.logRecord.service.impl;

import io.github.lipanre.logRecord.bean.LogDTO;
import io.github.lipanre.logRecord.configuration.LogRecordProperties;
import io.github.lipanre.logRecord.configuration.StreamSenderConfiguration;
import io.github.lipanre.logRecord.constants.LogConstants;
import io.github.lipanre.logRecord.service.DataPipelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableConfigurationProperties({LogRecordProperties.class})
@ConditionalOnProperty(name = "log-record.data-pipeline", havingValue = LogConstants.DataPipeline.STREAM)
public class StreamDataPipelineServiceImpl implements DataPipelineService {

    @Autowired
    private StreamSenderConfiguration.LogRecordChannel channel;

    @Override
    public boolean createLog(LogDTO logDTO) {
        return channel.messageLoggingQueueInput().send(MessageBuilder.withPayload(logDTO).build());
    }
}
