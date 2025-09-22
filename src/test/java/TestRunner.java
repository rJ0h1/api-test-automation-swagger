import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;

@ConfigurationParameter(key= Constants.GLUE_PROPERTY_NAME, value="StepDefinitions")
@SelectClasspathResource("features")
public class TestRunner {
}
