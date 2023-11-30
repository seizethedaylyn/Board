package com.rlacofls.board.entity;

import com.rlacofls.board.dto.SignUpDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User") //해당 클래스를 entity로 사용 User - 사용할 이름
@Table(name = "User") // 디비에 해당하는 테이블과 현재 클래스를 매핑 시키겠다
public class UserEntity {
    @Id
    private String userEmail;   //primary key
    private String userPassword;
    private String userNickname;
    private String userPhoneNum;
    private String userAddress;
    private String userProfile;

    public UserEntity(SignUpDto dto){
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
        this.userNickname = dto.getUserNickname();
        this.userPhoneNum = dto.getUserPhoneNum();
        this.userAddress = dto.getUserAddress() + " " + dto.getUserAddressDetail();
    }
}