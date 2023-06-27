package com.inyaw.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class InyawSysRole {

    @Id
    @GeneratedValue
    private Integer id;

    private String roleName;

    private String roleKey;

    private String description;

}
