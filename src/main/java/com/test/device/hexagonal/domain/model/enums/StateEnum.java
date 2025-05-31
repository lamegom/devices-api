package com.test.device.hexagonal.domain.model.enums;

public enum StateEnum {
	AVAILABLE(1L),
    IN_USE(2L),
    INACTIVE(0L);

    private long code;

    StateEnum(long code) {
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
