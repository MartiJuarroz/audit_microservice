package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ExtraInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clave;
    private String valor;

    @ManyToOne()
    private AuditLog auditLog;

  //  @OneToOne(cascade = CascadeType.ALL)
  //  private ModuleMicroservice module;

}
