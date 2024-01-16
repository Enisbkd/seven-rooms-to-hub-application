package com.sbm.sevenroomstohub.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Seven Rooms To Hub Application.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {}
