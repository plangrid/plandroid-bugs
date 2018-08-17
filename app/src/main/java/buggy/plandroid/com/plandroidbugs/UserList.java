package buggy.plandroid.com.plandroidbugs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserList {

    @JsonProperty("data") public List<UserWire> userWires = new ArrayList<>();
}
