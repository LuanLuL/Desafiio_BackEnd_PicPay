package com.pickpay.pickpay.domains.transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import com.pickpay.pickpay.domains.user.UserModel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name= "transaction_tb")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal amount;

    @JoinColumn(name = "sender_id")
    @ManyToOne
    private UserModel sender;

    @JoinColumn(name = "receiver_id")
    @ManyToOne
    private UserModel receiver;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now(ZoneId.of("UTC"));
}
