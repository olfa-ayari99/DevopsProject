package tn.esprit.eventsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ParticipantServiceImplMock {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddParticipant() {
        Participant participant = new Participant();
        when(participantRepository.save(any())).thenReturn(participant);

        Participant result = eventServices.addParticipant(participant);

        assertEquals(participant, result);
        verify(participantRepository, times(1)).save(eq(participant));
    }
    @Test
    void testAddAffectEvenParticipant() {
        Event event = new Event();
        event.setIdEvent(1);

        Participant participant = new Participant();
        participant.setIdPart(1);

        when(participantRepository.findById(eq(1))).thenReturn(java.util.Optional.of(participant));
        when(eventRepository.save(any())).thenReturn(event);

        Event result = eventServices.addAffectEvenParticipant(event, 1);

        assertEquals(event, result);
        verify(participantRepository, times(1)).findById(eq(1));
        verify(eventRepository, times(1)).save(eq(event));
    }

    @Test
    void testAddAffectEvenParticipantWithParticipants() {
        Event event = new Event();
        event.setIdEvent(1);

        Set<Participant> participants = new HashSet<>();
        Participant participant = new Participant();
        participant.setIdPart(1);
        participants.add(participant);

        event.setParticipants(participants);

        when(participantRepository.findById(eq(1))).thenReturn(java.util.Optional.of(participant));
        when(eventRepository.save(any())).thenReturn(event);

        Event result = eventServices.addAffectEvenParticipant(event);

        assertEquals(event, result);
        verify(participantRepository, times(1)).findById(eq(1));
        verify(eventRepository, times(1)).save(eq(event));
    }


}
