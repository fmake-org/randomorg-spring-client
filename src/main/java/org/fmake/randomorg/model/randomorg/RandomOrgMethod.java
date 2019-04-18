package org.fmake.randomorg.model.randomorg;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public enum RandomOrgMethod {
    GENERATE_INTEGERS("generateIntegers"),
    GENERATE_INTEGER_SEQUENCES("generateIntegerSequences"),
    GENERATE_DECIMAL_FRACTIONS("generateDecimalFractions"),
    GENERATE_GAUSSIANS("generateGaussians"),
    GENERATE_STRINGS("generateStrings"),
    GENERATE_UUIDS(" generateUUIDs"),
    GENERATE_BLOBS("generateBlobs"),
    GET_USAGE("getUsage");

    @Getter(onMethod_={@JsonValue})
    private final String value;
}
