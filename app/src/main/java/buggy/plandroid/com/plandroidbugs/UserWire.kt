package buggy.plandroid.com.plandroidbugs

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserWire(
    @JsonProperty(value = "uid")
    val userId: String,

    @JsonProperty(value = "email")
    val email: String,

    @JsonProperty(value = "first_name")
    val firstName: String,

    @JsonProperty(value = "last_name")
    val lastName: String,

    @JsonProperty(value = "company")
    val company: String,

    @JsonProperty(value = "title")
    val title: String,

    @JsonProperty(value = "language")
    val language: String,
)