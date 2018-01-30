package com.zooplus.sdc.converter.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "currency")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name"})
public class CurrencyEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
}
