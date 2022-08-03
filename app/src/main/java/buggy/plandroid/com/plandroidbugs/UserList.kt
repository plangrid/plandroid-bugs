package buggy.plandroid.com.plandroidbugs

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserList(
    @JsonProperty("data")
    val userWires: List<UserWire> = ArrayList()
)