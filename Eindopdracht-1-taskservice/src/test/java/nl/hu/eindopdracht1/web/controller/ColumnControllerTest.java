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

//    @Test
//    public void testGetAccountHoldersSuccessful() throws InterruptedException, AccountNotFoundException {
//        // Given
//        final String iban = "NL37ABNA9999888877";
//        final String bsn = "123456";
//        final String name = "Luuk Pook";
//        Holder holder = new Holder(bsn, name, null);
//        List<Holder> holders = new ArrayList<>();
//        holders.add(holder);
//        Account account = new Account(Iban.random(), new BigDecimal(123), null, true);
//        Mockito.when(accountService.findByIban(ibanCaptor1.capture())).thenReturn(Optional.of(account));
//        Mockito.when(accountService.findAllHoldersByIban(ibanCaptor2.capture())).thenReturn(holders);
//
//        // When
//        final List<HolderDto> holdersResult = accountController.getAccountHolders(iban);
//
//        // Then
//        Mockito.verify(accountService).findByIban(ibanCaptor1.getValue());
//        assertThat(ibanCaptor1.getValue().toString()).isEqualTo(iban);
//        Mockito.verify(accountService).findAllHoldersByIban(ibanCaptor2.getValue());
//        assertThat(ibanCaptor2.getValue().toString()).isEqualTo(iban);
//        Mockito.verifyNoMoreInteractions(accountService);
//        assertThat(holdersResult).isNotEmpty();
//        assertThat(holdersResult.get(0).getBsn()).isEqualTo(bsn);
//        assertThat(holdersResult.get(0).getName()).isEqualTo(name);
//    }

    @Test
    void createColumn() throws ColumnNotFoundException {
        //given
        final String columnId = "Dit is een kolom";

        //when
        columnController.createColumn(new CreateColumnDto(columnId));

        //then
        Mockito.verify(columnService).findColumnById(columnId);
        assertThat(columnService.getAllColums().get(0).getId()).isEqualTo(columnId);
        assertThat(columnService.getAllColums()).isNotEmpty();
    }

    @Test
    void switchTaskBetweenColumns() {
    }
}