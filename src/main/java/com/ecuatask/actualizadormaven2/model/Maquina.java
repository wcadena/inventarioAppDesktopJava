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
public class Maquina {
    private String 	nombre	;
private String 	uptime	;
private String 	kernel_version	;
private String 	product_type	;
private String 	product_version	;
private String 	service_pack	;
private String 	kernel_build_number	;
private String 	registered_organization	;
private String 	registered_owner	;
private String 	ie_version	;
private String 	system_root	;
private String 	processors	;
private String 	processor_speed	;
private String 	processor_type	;
private String 	physical_memory	;
private String 	video_driver	;

    
    
    public Maquina() {
        this.nombre = "";
        this.uptime = "";
        this.kernel_version = "";
        this.product_type = "";
        this.product_version = "";
        this.service_pack = "";
        this.kernel_build_number = "";
        this.registered_organization = "";
        this.registered_owner = "";
        this.ie_version = "";
        this.system_root = "";
        this.processors = "";
        this.processor_speed = "";
        this.processor_type = "";
        this.physical_memory = "";
        this.video_driver = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getKernel_version() {
        return kernel_version;
    }

    public void setKernel_version(String kernel_version) {
        this.kernel_version = kernel_version;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_version() {
        return product_version;
    }

    public void setProduct_version(String product_version) {
        this.product_version = product_version;
    }

    public String getService_pack() {
        return service_pack;
    }

    public void setService_pack(String service_pack) {
        this.service_pack = service_pack;
    }

    public String getKernel_build_number() {
        return kernel_build_number;
    }

    public void setKernel_build_number(String kernel_build_number) {
        this.kernel_build_number = kernel_build_number;
    }

    public String getRegistered_organization() {
        return registered_organization;
    }

    public void setRegistered_organization(String registered_organization) {
        this.registered_organization = registered_organization;
    }

    public String getRegistered_owner() {
        return registered_owner;
    }

    public void setRegistered_owner(String registered_owner) {
        this.registered_owner = registered_owner;
    }

    public String getIe_version() {
        return ie_version;
    }

    public void setIe_version(String ie_version) {
        this.ie_version = ie_version;
    }

    public String getSystem_root() {
        return system_root;
    }

    public void setSystem_root(String system_root) {
        this.system_root = system_root;
    }

    public String getProcessors() {
        return processors;
    }

    public void setProcessors(String processors) {
        this.processors = processors;
    }

    public String getProcessor_speed() {
        return processor_speed;
    }

    public void setProcessor_speed(String processor_speed) {
        this.processor_speed = processor_speed;
    }

    public String getProcessor_type() {
        return processor_type;
    }

    public void setProcessor_type(String processor_type) {
        this.processor_type = processor_type;
    }

    public String getPhysical_memory() {
        return physical_memory;
    }

    public void setPhysical_memory(String physical_memory) {
        this.physical_memory = physical_memory;
    }

    public String getVideo_driver() {
        return video_driver;
    }

    public void setVideo_driver(String video_driver) {
        this.video_driver = video_driver;
    }

    @Override
    public String toString() {
        return "Maquina{" + "nombre=" + nombre + ", uptime=" + uptime + ", kernel_version=" + kernel_version + ", product_type=" + product_type + ", product_version=" + product_version + ", service_pack=" + service_pack + ", kernel_build_number=" + kernel_build_number + ", registered_organization=" + registered_organization + ", registered_owner=" + registered_owner + ", ie_version=" + ie_version + ", system_root=" + system_root + ", processors=" + processors + ", processor_speed=" + processor_speed + ", processor_type=" + processor_type + ", physical_memory=" + physical_memory + ", video_driver=" + video_driver + '}';
    }
    

}
