package com.marketplace.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Keywords")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KEYWORD_ID")
    private Long keywordId;
    @Column(name = "NAME")
    @ElementCollection
    private List<String> name;

    /*
    @ManyToMany(mappedBy = "keywords")
    @JsonBackReference("keywords")
    List<Product> products =new ArrayList<>();
     */
}
