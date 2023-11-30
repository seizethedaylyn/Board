package com.rlacofls.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="PopularSearch")
@Table(name="PopularSearch")
public class PopularSearchEntity {
    @Id
    private String popularTerm;
    private int popularSearchCount;
}
