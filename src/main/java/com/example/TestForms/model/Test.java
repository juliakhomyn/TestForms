package com.example.TestForms.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tests")
public class Test {
    static final String MESSAGE = "this field cannot be empty";

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Integer id;

    @NotBlank(message = MESSAGE)
    @Column(nullable = false)
    String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    User createdBy;

    @Column(name = "is_active", nullable = false)
    boolean isActive;

    @Column(name = "created_date", nullable = false)
    LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Category category;

    @Min(0)
    @Max(100)
    @NotNull(message = MESSAGE)
    @Column(name = "passing_score", nullable = false)
    Double passingScore;

    @OneToMany(mappedBy = "test", fetch = FetchType.EAGER)
    Set<Session> sessions = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tests_questions",
            joinColumns = {@JoinColumn(name = "test_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")}
    )
    Set<Question> questions = new HashSet<>();
}
