package buggy.plandroid.com.plandroidbugs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
    @JsonProperty(value = "uid") public String uid;

}
