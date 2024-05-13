package org.example.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Type<T extends Parameter> extends Entity{
    protected String typeName;
    protected List<T> parameters;

    public void addParameter(T parameter) {
        parameters.add(parameter);
    }

    protected abstract List<T> getParametersList();
}