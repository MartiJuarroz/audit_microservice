package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
public class ModuleMicroservice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @DateTimeFormat(pattern = "dd-MM-YYYY' 'HH:mm:ss")
    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean audit;
    private boolean active;

  //  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "module")
  //  private List<AuditLog> auditLogs;

   // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "module")
   // private List<ExtraInfo> extraInfo;

}
