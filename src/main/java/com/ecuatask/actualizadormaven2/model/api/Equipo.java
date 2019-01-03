/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecuatask.actualizadormaven2.model.api;

import java.util.ArrayList;

/**
 *
 * @author Anibal
 */
public class Equipo {

    public String id;
    public String modelo_equipo_id;
    public String orden_de_compra_id;
    public String custodio_id;
    public String estacione_id;
    public String area_id;
    public String check_list_id;
    public String imagen;
    public String num_cajas;
    public String sociedad;
    public String no_serie;
    public String codigo_barras;
    public String codigo_avianca;
    public String codigo_otro;
    public String descripcion;
    public String ip;
    public String estado;
    public String estatus;
    public String garantia;
    public String observaciones;
    public String codigo_contable;
    public String hp_warrantyLevel;
    public String hp_endDate;
    public String hp_displaySerialNumber;
    public String hp_modelNumber;
    public String hp_countryOfPurchase;
    public String hp_newProduct_seriesName;
    public String hp_newProduct_imageUrl;
    public String hp_warrantyResultRedirectUrl;
    public String fechaCreacion;
    public String fechaActualizacion;
    public String fechaEliminacion;
    public ArrayList<Links> links;

    @Override
    public String toString() {
        return "Equipo{" + "id=" + id + ", modelo_equipo_id=" + modelo_equipo_id + ", orden_de_compra_id=" + orden_de_compra_id + ", custodio_id=" + custodio_id + ", estacione_id=" + estacione_id + ", area_id=" + area_id + ", check_list_id=" + check_list_id + ", imagen=" + imagen + ", num_cajas=" + num_cajas + ", sociedad=" + sociedad + ", no_serie=" + no_serie + ", codigo_barras=" + codigo_barras + ", codigo_avianca=" + codigo_avianca + ", codigo_otro=" + codigo_otro + ", descripcion=" + descripcion + ", ip=" + ip + ", estado=" + estado + ", estatus=" + estatus + ", garantia=" + garantia + ", observaciones=" + observaciones + ", codigo_contable=" + codigo_contable + ", hp_warrantyLevel=" + hp_warrantyLevel + ", hp_endDate=" + hp_endDate + ", hp_displaySerialNumber=" + hp_displaySerialNumber + ", hp_modelNumber=" + hp_modelNumber + ", hp_countryOfPurchase=" + hp_countryOfPurchase + ", hp_newProduct_seriesName=" + hp_newProduct_seriesName + ", hp_newProduct_imageUrl=" + hp_newProduct_imageUrl + ", hp_warrantyResultRedirectUrl=" + hp_warrantyResultRedirectUrl + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + ", fechaEliminacion=" + fechaEliminacion + ", links=" + links + '}';
    }

}
