package com.examples.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "classpath:features/Target.feature",
                "classpath:features/Negative.feature"
                },
        plugin = { "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        dryRun = false,
        glue = {"com.examples.cucumber"},
        monochrome = true)


public class RunCucumberTest {
}
