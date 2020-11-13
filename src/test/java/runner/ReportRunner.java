package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@Report",
        dryRun = false,
        features = {"src/test/resources/features/"},
        plugin = {
                "pretty", "json:target/cucumber/Report.json",
                "html:target/cucumber/Report.html"}, monochrome = true,
        glue = { "stepdefinitions" })
public class ReportRunner {
}
