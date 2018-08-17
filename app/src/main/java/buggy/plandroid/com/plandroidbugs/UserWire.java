package buggy.plandroid.com.plandroidbugs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWire {

    @JsonProperty(value = "uid") public String userId;
    @JsonProperty(value = "email") public String email;
    @JsonProperty(value = "first_name") public String firstName;
    @JsonProperty(value = "last_name") public String lastName;
    @JsonProperty(value = "company") public String company;
    @JsonProperty(value = "title") private String title;
    @JsonProperty(value = "language") public String language;
    @JsonProperty(value = "role") public Role role;
}
