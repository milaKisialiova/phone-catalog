package pt.jumia.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features", glue = { "pt.jumia.steps" })
public class TestRunner {

}
