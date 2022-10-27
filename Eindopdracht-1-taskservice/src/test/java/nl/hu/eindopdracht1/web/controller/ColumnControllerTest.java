package nl.hu.eindopdracht1.web.controller;

import nl.hu.eindopdracht1.data.entity.Column;
import nl.hu.eindopdracht1.domain.exception.ColumnNotFoundException;
import nl.hu.eindopdracht1.domain.service.ColumnService;
import nl.hu.eindopdracht1.domain.service.UserService;
import nl.hu.eindopdracht1.web.dto.CreateColumnDto;
import org.iban4j.Iban;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ColumnControllerTest {
    @Mock
    ColumnService columnService;

    @InjectMocks // Class Under Test
    ColumnController columnController;

    @Captor
    ArgumentCaptor<Iban> columnIdCaptor1;

//    @Test
//    void createColumn() throws ColumnNotFoundException {
//        //given
//        final String columnId = "Dit is een kolom";
//
//        //when
//        columnController.createColumn(new CreateColumnDto(columnId));
//
//        //then
//        Mockito.verify(columnService).findColumnById(columnIdCaptor1.capture());
//        assertThat(columnService.getAllColums().get(0).getId()).isEqualTo(columnId);
//        assertThat(columnService.getAllColums()).isNotEmpty();
//    }

    @Test
    void switchTaskBetweenColumns() {
    }
}