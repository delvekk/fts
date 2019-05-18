package com.dawid.fts.converters;

import com.dawid.fts.domain.Document;
import com.dawid.fts.dto.DocumentDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class DocumentToDocumentDTO implements Converter<Document, DocumentDTO> {


    @Override
    public DocumentDTO convert(Document source) {
        if (source == null) {
            return null;
        }
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(source.getId());
        documentDTO.setAuthor(source.getAuthor());
        documentDTO.setTitle(source.getTitle().get(0));

        Calendar cal = Calendar.getInstance();
        cal.setTime(source.getDate());
        documentDTO.setYear(cal.get(Calendar.YEAR));

        return documentDTO;
    }
}
