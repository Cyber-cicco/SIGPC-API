package fr.diginamic.validation.sequences;

import fr.diginamic.projet.ProjetDto;
import fr.diginamic.validation.groups.NotBlankGroup;
import fr.diginamic.validation.groups.NotEmptyGroup;
import fr.diginamic.validation.groups.NotNullGroup;
import jakarta.validation.GroupSequence;

@GroupSequence(
    value = {NotNullGroup.class, NotEmptyGroup.class, NotBlankGroup.class, ProjetDto.class})
public interface NullEmptyBlankSequence {}
