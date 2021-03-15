package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.config;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.config.properties.TimerProperties;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.flow.StepResolver;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.CommonHandler;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.GetQuoteHandler;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.HandleCallbackHandler;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.IntentService;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.IntentServiceImpl;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.PayoutAdapterService;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.PayoutAdapterServiceLogDecorator;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.validator.WithdrawalValidator;
import com.rbkmoney.damsel.withdrawals.provider_adapter.AdapterSrv;
import com.rbkmoney.error.mapping.ErrorMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * Основная конфигурация для работы с библиотекой адаптеров
 */
@Slf4j
@Configuration
public class ServiceConfig {

    @Bean
    public AdapterSrv.Iface payoutAdapterService(
            WithdrawalToEntryStateConverter<EntryStateModelImpl> withdrawalToEntryStateConverter,
            ExitToProcessResultConverterImpl exitStateToProcessResultConverter,
            List<CommonHandler<EntryStateModelImpl, ExitStateModelImpl>> handlers,
            StepResolver<EntryStateModelImpl, ExitStateModelImpl> stepResolver,
            WithdrawalValidator validator,
            GetQuoteHandler getQuoteHandler,
            HandleCallbackHandler callbackResult
    ) {
        return new PayoutAdapterService<>(
                withdrawalToEntryStateConverter,
                exitStateToProcessResultConverter,
                handlers,
                stepResolver,
                validator,
                getQuoteHandler,
                callbackResult
        );
    }

    @Bean
    @Primary
    public AdapterSrv.Iface payoutAdapterServiceLogDecorator(AdapterSrv.Iface payoutAdapterService) {
        return new PayoutAdapterServiceLogDecorator(payoutAdapterService);
    }
}
