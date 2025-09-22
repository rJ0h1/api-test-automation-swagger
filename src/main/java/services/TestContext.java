package services;

import lombok.Data;
import model.Api;
import model.Apis;
import org.yaml.snakeyaml.Yaml;


import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class TestContext {

    private Apis apis;
    static private String ENVIRONMENT_FILE = "test-environment.yaml";
    private Api apiUnderTest;

    public Api getContext(String apiName) throws Exception {
        apis = getApis();
        apiUnderTest = apis.getApi(apiName);
        return apiUnderTest;
    }

    private Apis getApis() {
        Path path = Paths.get("environments", ENVIRONMENT_FILE);

        InputStream inputStream = Apis.class.getClassLoader().getResourceAsStream(path.toString());

        Yaml yaml = new Yaml();
        return yaml.loadAs(inputStream, Apis.class);
    }

}
