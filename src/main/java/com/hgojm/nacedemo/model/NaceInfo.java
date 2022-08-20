package com.hgojm.nacedemo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "nace_info")
@Data
@Builder
public class NaceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "order")
    private Integer order;
    @Column(name = "level")
    private Integer level;
    @Column(name = "code")
    private String code;
    @Column(name = "parent")
    private String parent;
    @Column(name = "description")
    private String description;
    @Column(name = "includes")
    private String includes;
    @Column(name = "also_includes")
    private String alsoIncludes;
    @Column(name = "excludes")
    private String excludes;
    @Column(name = "rulings")
    private String rulings;
    @Column(name = "reference_isic_rev_4")
    private String referenceIsicRev4;
}
