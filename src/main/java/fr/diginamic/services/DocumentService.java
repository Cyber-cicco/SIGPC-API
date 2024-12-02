package fr.diginamic.services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import fr.diginamic.dto.DocumentDto;
import fr.diginamic.dto.DocumentTransformer;
import fr.diginamic.repository.DocumentRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentTransformer documentTransformer;

}
