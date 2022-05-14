package com.sky.entity.nse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filtered {
    Data data[];

    @JsonProperty("PE")
    PETotal pe;

    @JsonProperty("CE")
    CETotal ce;
}
