package com.example.yugioh.export.configuration;

import com.example.yugioh.export.Item.CardApiReader;
import com.example.yugioh.export.Item.CardJsonProcessor;
import com.example.yugioh.export.Item.CardWriter;
import com.example.yugioh.export.model.CardDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class CardExporterConfiguration {
    private final CardApiReader cardApiReader;
    private final CardJsonProcessor cardJsonProcessor;
    private final CardWriter cardWriter;

    @Bean
    public Job exportCardsJob(JobRepository jobRepository, Step exportCardsStep) {
        return new JobBuilder("exportCardsJob", jobRepository)
                .start(exportCardsStep)
                .build();
    }

    @Bean
    public Step exportCardsStep(JobRepository jobRepository,
                                PlatformTransactionManager transactionManager
    ) {
        return new StepBuilder("exportCardsStep", jobRepository)
                .<JsonNode, List<CardDTO>>chunk(500, transactionManager)
                .reader(cardApiReader)
                .processor(cardJsonProcessor)
                .writer(cardWriter)
                .build();
    }
}
