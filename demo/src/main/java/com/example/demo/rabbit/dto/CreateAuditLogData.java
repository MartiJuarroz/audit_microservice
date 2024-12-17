package com.example.demo.rabbit.dto;

import com.example.demo.utils.gson.GsonTools;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public final class CreateAuditLogData {

    @SerializedName("action")
    private final String action;

    @SerializedName("actionTo")
    private String actionTo;

    @SerializedName("actionToId")
    private String actionToId;

    @SerializedName("userId")
    private String userId;

    @SerializedName("entity")
    private String entity;

    @SerializedName("extraInfo")
    private List<ExtraInfoDTO> extraInfo;

    public CreateAuditLogData(String action, String actionTo, String actionToId,
                              String userId, String entity, List<ExtraInfoDTO> extraInfo){
        this.action = action;
        this.actionTo = actionTo;
        this.actionToId = actionToId;
        this.userId = userId;
        this.entity = entity;
        this.extraInfo = extraInfo;
    }

    public static CreateAuditLogData fromJson(String json){
        return GsonTools.gson().fromJson(json, CreateAuditLogData.class);
    }

}
