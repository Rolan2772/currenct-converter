package com.zooplus.sdc.converter.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name"})
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
}
