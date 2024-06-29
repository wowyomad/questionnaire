package org.wowyomad.questionaire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    @JsonBackReference
    private Submission submission;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Nullable
    @Column(name = "text")
    private String text;

    @ManyToMany
    @JoinTable(
            name = "answer_selected_option_assoc",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> selectedOptions = new ArrayList<>();
}
