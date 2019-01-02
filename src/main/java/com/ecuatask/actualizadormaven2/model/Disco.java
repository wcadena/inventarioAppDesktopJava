/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven2.model;

/**
 *
 * @author wcadena
 */
public class Disco {
    private String 	volume	;
    private String 	type	;
    private String 	format	;
    private String 	label	;
    private String 	size	;
    private String 	free	;
    private String 	free_porsiento	;

    public Disco(String volume, String type, String format, String label, String size, String free, String free_porsiento) {
        this.volume = volume;
        this.type = type;
        this.format = format;
        this.label = label;
        this.size = size;
        this.free = free;
        this.free_porsiento = free_porsiento;
    }
    public Disco() {
        this.volume = "";
        this.type = "";
        this.format = "";
        this.label = "";
        this.size = "";
        this.free = "";
        this.free_porsiento = "";
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getFree_porsiento() {
        return free_porsiento;
    }

    public void setFree_porsiento(String free_porsiento) {
        this.free_porsiento = free_porsiento;
    }

    @Override
    public String toString() {
        return "Disco{" + "volume=" + volume + ", type=" + type + ", format=" + format + ", label=" + label + ", size=" + size + ", free=" + free + ", free_porsiento=" + free_porsiento + '}';
    }


}
