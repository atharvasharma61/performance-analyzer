/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.performanceanalyzer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Before;
import org.mockito.Mockito;
import org.opensearch.performanceanalyzer.commons.config.PluginSettings;
import org.opensearch.performanceanalyzer.commons.config.overrides.ConfigOverridesWrapper;
import org.opensearch.performanceanalyzer.config.PerformanceAnalyzerController;

public class CustomMetricsLocationTestBase {

    private static final Path METRICS_LOCATION = Paths.get("build/tmp/junit_metrics");
    protected PerformanceAnalyzerController mockController;
    protected ConfigOverridesWrapper mockWrapper;

    @Before
    public void setUp() throws Exception {
        if (!Files.exists(METRICS_LOCATION)) {
            Files.createDirectories(METRICS_LOCATION.getParent());
            Files.createDirectory(METRICS_LOCATION);
        }
        mockController = mock(PerformanceAnalyzerController.class);
        mockWrapper = mock(ConfigOverridesWrapper.class);
        Mockito.when(mockController.isCollectorDisabled(any(), anyString())).thenReturn(false);
        Mockito.when(mockController.rcaCollectorsEnabled()).thenReturn(true);
        Mockito.when(mockController.telemetryCollectorsEnabled()).thenReturn(true);
        PluginSettings.instance().setMetricsLocation(METRICS_LOCATION + File.separator);
    }
}
