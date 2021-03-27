package map;

import dto.DocumentDto;
import entity.Document;

public class MapDocument {

    public static DocumentDto convertToDocumentDto(Document document) {
        DocumentDto documentDto = new DocumentDto();

        documentDto.setId(document.getId());
        documentDto.setName(document.getName());

        return documentDto;
    }

    public static Document convertToDocument(DocumentDto documentDto) {
        Document document = new Document();

        document.setId(documentDto.getId());
        document.setName(documentDto.getName());

        return document;
    }
}
