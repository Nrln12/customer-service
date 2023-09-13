package com.azercell.customerservice.config;

import org.modelmapper.Condition;
import org.modelmapper.spi.MappingContext;

public class NotNullCondition<S, D> implements Condition<S, D> {
    @Override
    public boolean applies(MappingContext<S, D> context) {
        return context.getSource() != null;
    }
}
