package com.example.yugioh.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * This class is responsible for exporting card data from API to database
 */

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class CardExporterController {
    private final JobLauncher jobLauncher;
    private final Job exportCardsJob;

    /**
     * Exports data obtained from the external API to the local database
     */
    @PostMapping("/export")
    public void exportApiData() {
        try {
            log.info("Starting card export job...");

            JobParameters params = new JobParametersBuilder()
                    .addDate("runAt", new Date()) // unique param to force a new job instance
                    .toJobParameters();

            jobLauncher.run(exportCardsJob, params);

        } catch (Exception e) {
            log.error("Failed to start export job: {}", e.getMessage(), e);
        }
    }
}