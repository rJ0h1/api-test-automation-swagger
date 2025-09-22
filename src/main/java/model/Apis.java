package model;

import lombok.Data;

import java.util.Map;

@Data
public class Apis {

   private Map<String, Api> apis;

    public Api getApi(String apiName) {
        return apis.get(apiName);
    }
}
