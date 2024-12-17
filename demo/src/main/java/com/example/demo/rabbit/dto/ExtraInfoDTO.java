package com.example.demo.rabbit.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

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
