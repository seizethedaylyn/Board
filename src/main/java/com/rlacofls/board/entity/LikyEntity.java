package com.rlacofls.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Liky")
@Table(name="Liky")
public class LikyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int likeId;
    private int boardNumber;
    private String userEmail;
    private String likeUserProfile;
    private String likeUserNickname;
}
