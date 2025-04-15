package io.github.lipanre.logRecord.service;


/**
 * SPI：获取操作者ID
 */
public interface IOperatorIdGetService {

    /**
     * 获取操作者ID
     * @return 操作者ID
     */
    String getOperatorId() throws Exception;

}
