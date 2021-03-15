package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.config;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация для настройки и заведения процессоров
 * @see com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor.Processor
 */
@Configuration
@RequiredArgsConstructor
public class ProcessorConfig {

//    Пример заведения процессоров:
//    @Bean
//    public Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> responseProcessorChain() {
//        ErrorProcessor errorProcessor = new ErrorProcessor();
//        return new SuccessProcessor(errorProcessor);
//    }
}
