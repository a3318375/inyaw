package com.inyaw.sys.bean;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

@Table("sys_config")
@Data
public class SysConfig {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    private String configKey;

    private String configValue;

    private Integer configType;
}
