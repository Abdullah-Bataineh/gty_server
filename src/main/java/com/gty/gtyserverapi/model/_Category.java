package com.gty.gtyserverapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class _Category {
    private String category_id;
    private String category_name;
    private int parent_id;

}
