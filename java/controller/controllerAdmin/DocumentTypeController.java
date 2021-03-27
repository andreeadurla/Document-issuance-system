package controller.controllerAdmin;

import messages.SuccessMessage;
import view.viewAdmin.DocumentTypeView;
import dto.DocumentDto;
import service.DocumentService;
import validators.Validator;

import javax.swing.*;
import java.util.List;

public class DocumentTypeController {

    private DocumentService documentService;
    private DocumentTypeView documentView;
    private JFrame frame;


    public DocumentTypeController(JFrame frame) {
        this.frame = frame;

        documentService = new DocumentService();
        documentView = new DocumentTypeView();

        addListeners();
        printDocuments();
    }

    private void addListeners() {
        documentView.addSubmitButtonListener(e -> addDocumentType());
        documentView.addDeleteButtonListener(e -> deleteDocumentType());
    }

    private void printDocuments() {
        List<DocumentDto> documentsDto = documentService.getAllDocumentTypes();

        for(DocumentDto documentDto : documentsDto) {
            documentView.insertInTable(documentDto);
        }
    }

    private void addDocumentType() {

        DocumentDto documentDto = documentView.getDocumentInfos();

        try {
            Validator.isEmptyOrNull(documentDto.getId());
            Validator.isEmptyOrNull(documentDto.getName());

            documentService.insertNewDocumentType(documentDto);
            documentView.insertInTable(documentDto);
            documentView.setVisibleErrorArea(false);
        }
        catch(RuntimeException e) {
            documentView.setErrorArea(e.getMessage());
            documentView.setVisibleErrorArea(true);
        }
        finally {
            documentView.setEmptyTextField();
        }
    }

    private void deleteDocumentType() {
        String id = documentView.getIdOfSelectedDocument();

        try {
            Validator.isEmptyOrNull(id);

            documentService.removeDocumentById(id);
            documentView.deleteFromTable();
            JOptionPane.showMessageDialog(frame, SuccessMessage.SUCCESS_DELETE);
        }
        catch(RuntimeException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DocumentTypeView getDocumentView() {
        return documentView;
    }
}
