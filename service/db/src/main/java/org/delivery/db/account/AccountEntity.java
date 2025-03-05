package org.delivery.db.account;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

@NoArgsConstructor
@SuperBuilder //부모로부터 상속받은 변수 추가
@Data
@EqualsAndHashCode(callSuper = true) //callSuper = true <- 부모 안에 있는 값까지 포함해서 비교
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {
}

