package com.examples.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/Target.feature"},
        dryRun = false,
        glue = {"com.examples.cucumber"})


public class RunCucumberTest {
}
