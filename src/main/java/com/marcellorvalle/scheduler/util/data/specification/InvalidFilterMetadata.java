package com.marcellorvalle.scheduler.util.data.specification;

class InvalidFilterMetadata extends Metadata {
    static final InvalidFilterMetadata INSTANCE = new InvalidFilterMetadata();

    @Override
    boolean isValidFilter() {
        return false;
    }
}
