package org.wowyomad.questionaire.map;

import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.OptionCreateDTO;
import org.wowyomad.questionaire.dto.OptionResponseDTO;
import org.wowyomad.questionaire.model.Option;

@Component
public class OptionMapper {

    public OptionResponseDTO toResponseDTO(Option option) {
        if (option == null) {
            return null;
        }
        OptionResponseDTO dto = new OptionResponseDTO();
        dto.setId(option.getId());
        dto.setLabel(option.getLabel());
        dto.setIndex(option.getIndex());
        return dto;
    }

    public Option fromCreateDTO(OptionCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        Option option = new Option();
        option.setLabel(dto.getLabel());
        option.setIndex(dto.getIndex());
        return option;
    }
}
