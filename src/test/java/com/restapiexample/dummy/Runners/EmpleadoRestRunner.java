package com.restapiexample.dummy.Runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/com.restapiexample.dummy/features/empleadoRest.feature",
        glue = "com.restapiexample.dummy.stepDefinitions",
        snippets = SnippetType.CAMELCASE
)

public class EmpleadoRestRunner {
}
