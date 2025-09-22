import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import io.cucumber.junit.platform.engine.Constants;

@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key=Constants.GLUE_PROPERTY_NAME, value = "StepDefinitions")
public class TestRunner {
}
