package com.inyaw.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<InyawSysMenu> menuList;
}
