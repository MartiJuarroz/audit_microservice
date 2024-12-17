package com.example.demo.rabbit.dto;

import com.example.demo.utils.gson.GsonTools;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;
@Data
public final class ExtraInfoDTO {

        @SerializedName("clave")
        private String clave;

        @SerializedName("valor")
        private String valor;

        public ExtraInfoDTO(String valor, String clave){
            this.valor = valor;
            this.clave = clave;
        }

}
