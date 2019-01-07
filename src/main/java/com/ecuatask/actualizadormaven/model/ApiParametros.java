/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven.model;

import java.util.Objects;

/**
 *
 * @author Anibal
 */
public class ApiParametros {
    private String _key;
    private String _value;

    public String getKey() {
        return _key;
    }

    public void setKey(String _key) {
        this._key = _key;
    }

    public String getValue() {
        return _value;
    }

    public void setValue(String _value) {
        this._value = _value;
    }

    public ApiParametros(String _key, String _value) {
        this._key = _key;
        this._value = _value;
    }

    @Override
    public String toString() {
        return "ApiParametros{" + "_key=" + _key + ", _value=" + _value + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this._key);
        hash = 53 * hash + Objects.hashCode(this._value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApiParametros other = (ApiParametros) obj;
        if (!Objects.equals(this._key, other._key)) {
            return false;
        }
        return Objects.equals(this._value, other._value);
    }
    
    
}
