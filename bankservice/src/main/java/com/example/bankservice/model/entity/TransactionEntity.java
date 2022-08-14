package com.example.bankservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "transaction_id_generator")
    @SequenceGenerator(name = "transaction_id_generator", sequenceName = "transaction_sequence_id", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @CreationTimestamp
    @Column(name = "timestamp", nullable = false, updatable = false)
    @Size(max = 255, message = "can not be larger than 255")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "receivent_id")
    private UserEntity receivent;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @Column(name = "amount", nullable = false, updatable = false)
    @Size(min = 0)
    private Integer amount;

}
