package io.github.lipanre.logRecord.service.impl;

import io.github.lipanre.logRecord.bean.LogDTO;
import io.github.lipanre.logRecord.configuration.LogRecordProperties;
import io.github.lipanre.logRecord.constants.LogConstants;
import io.github.lipanre.logRecord.service.DataPipelineService;
import io.github.lipanre.logRecord.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableConfigurationProperties({LogRecordProperties.class})
@ConditionalOnProperty(name = "log-record.data-pipeline", havingValue = LogConstants.DataPipeline.RABBIT_MQ)
public class RabbitMqDataPipelineServiceImpl implements DataPipelineService {

    @Autowired
    private RabbitTemplate rubeExchangeTemplate;

    @Autowired
    private LogRecordProperties properties;

    @Override
    public boolean createLog(LogDTO logDTO) {
        log.info("LogRecord RabbitMq ready to send routingKey [{}] LogDTO [{}]", properties.getRabbitMqProperties().getRoutingKey(), logDTO);
        rubeExchangeTemplate.convertAndSend(properties.getRabbitMqProperties().getRoutingKey(), JsonUtil.safeToJsonString(logDTO));
        return true;
    }
}
