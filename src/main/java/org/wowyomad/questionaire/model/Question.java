package org.wowyomad.questionaire.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private boolean required;

    private boolean isActive;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Option> options = new ArrayList<>();

    public void addOptions(List<Option> newOptions) {
        long i = 0;
        for (Option option : newOptions) {
            option.setQuestion(this);
            option.setIndex(i++);
            this.options.add(option);
        }
    }

    public enum QuestionType {
        SINGLE_LINE_TEXT,
        MULTILINE_TEXT,
        RADIO_BUTTON,
        CHECKBOX,
        COMBOBOX,
        DATE
    }
}
