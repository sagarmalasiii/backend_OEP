package com.sagarmalasi.App.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "institute")
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institute_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String instituteName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String instituteCode;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    private String address;

    @OneToMany(mappedBy = "institute",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Exam> examList = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createAt;

}
