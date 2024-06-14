package org.wowyomad.questionaire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    @JsonBackReference
    private Submission submission;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private String response; // text

    @ElementCollection
    @CollectionTable(name = "answer_selected_option", joinColumns = @JoinColumn(name = "answer_id"))
    @Column(name = "option_id")
    private List<Long> selectedOptions = new ArrayList<>(); // option IDs
}
