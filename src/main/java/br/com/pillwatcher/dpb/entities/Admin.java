package br.com.pillwatcher.dpb.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity(name = "ADMIN")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Admin extends Auditable {

    @Id
    @Column(name = "ID_ADMIN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN")
    @SequenceGenerator(sequenceName = "SEQ_ADMIN", allocationSize = 1, name = "SEQ_ADMIN")
    private Long id;

    @ToString.Exclude
    @JoinColumn(name = "ID_USER")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private User user;

    @Email
    @Column(name = "EMAIL")
    private String email;

}
