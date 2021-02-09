package com.hat.javaadvance.design_pattern.chain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Builder
public class Danmu {
    private String value;
    private String color;
    private boolean isRoll;
    private boolean nearbySubtitle;
}
