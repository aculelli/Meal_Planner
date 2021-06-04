package com.techelevator.model;

public class MeasurementType {
    private long measurementTypeId;
    private String measurementTypeName;

    public MeasurementType(long measurementTypeId, String measurementTypeName) {
        this.measurementTypeId = measurementTypeId;
        this.measurementTypeName = measurementTypeName;
    }

    public long getMeasurementTypeId() {
        return measurementTypeId;
    }

    public void setMeasurementTypeId(long measurementTypeId) {
        this.measurementTypeId = measurementTypeId;
    }

    public String getMeasurementTypeName() {
        return measurementTypeName;
    }

    public void setMeasurementTypeName(String measurementTypeName) {
        this.measurementTypeName = measurementTypeName;
    }
}
