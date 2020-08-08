package com.thoughtworks.wechatmoments.model;

public class MomentData {

    private MomentDataType type;

    private BaseData data;

    public enum MomentDataType {
        PROFILE(0x00), TWEET(0x01);

        private final int value;

        public int getValue() {
            return value;
        }

        MomentDataType(int value) {
            this.value = value;
        }
    }

    public MomentDataType getType() {
        return type;
    }

    public void setData(BaseData data) {
        this.data = data;
    }
}
