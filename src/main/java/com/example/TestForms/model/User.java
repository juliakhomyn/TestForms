package com.example.TestForms.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {
    static final String MESSAGE = "this field cannot be empty";
    static final String PASSWORD_MESSAGE = "password must be longer than 4";

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @NotBlank(message = MESSAGE)
    @Column(nullable = false)
    String name;

    @NotBlank(message = MESSAGE)
    @Column(nullable = false)
    String surname;

    @NotBlank(message = MESSAGE)
    @Column(nullable = false)
    String email;

//    @NotBlank(message = MESSAGE)
    @Size(min = 4, message = PASSWORD_MESSAGE)
    @Column(nullable = false)
    String password;

    @Column(name = "created_date", nullable = false)
    LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    Role role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "createdBy")
    Set<Test> createdTests = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    Set<Session> sessions = new LinkedHashSet<>();

}
