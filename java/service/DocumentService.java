package service;

import dto.DocumentDto;
import entity.Document;
import exception.InvalidDataException;
import map.MapDocument;
import messages.WrongMessage;
import repository.DocumentRepo;
import validators.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentService {

    private final DocumentRepo dr = new DocumentRepo();

    public void insertNewDocumentType(DocumentDto documentDto) {
        Validator.isEmptyOrNull(documentDto.getId());
        Validator.isEmptyOrNull(documentDto.getName());

        Document document = MapDocument.convertToDocument(documentDto);

        document.setDeleted(false);

        boolean found = dr.findDocument(document);

        if(found == true)
            throw new InvalidDataException(WrongMessage.WRONG_DOCUMENT_TYPE);

        dr.insertNewDocumentType(document);
    }

    public void removeDocumentById(String id) {
        Validator.isEmptyOrNull(id);

        dr.deleteDocumentType(id);
    }

    public List<DocumentDto> getAllDocumentTypes() {

        List<Document> documents = dr.findAllDocumentTypes();

        List<DocumentDto> documentDtos = documents.stream()
                                                    .map(d -> MapDocument.convertToDocumentDto(d))
                                                    .collect(Collectors.toList());

        return documentDtos;
    }
}
