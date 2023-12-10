package nl.hva.ict.ads.elections.models;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ExtraTest {
    @Test
    void registrationShouldSucceed() {
        Constituency constituency = new Constituency(1, "testParty");
        Party party = new Party(2, "testParty2");
        Candidate candidate = new Candidate("John", null, "Doe");

        party.addOrGetCandidate(candidate);
        assertTrue(constituency.register(1, candidate));
        assertEquals(candidate, constituency.getCandidate(party, 1));
    }

    @Test
    void registrationShouldFailIfRankIsTaken() {
        Constituency constituency = new Constituency(1, "Flevoland");
        Party party = new Party(1, "PiratenPartij");
        Candidate candidate1 = new Candidate("John", null, "Doe");
        Candidate candidate2 = new Candidate("Jane", null, "Doe");

        assertTrue(constituency.register(1, candidate1));
        assertFalse(constituency.register(1, candidate2));
    }

    @Test
    void registrationShouldFailIfCandidateIsAlreadyRegistered() {
        Constituency constituency1 = new Constituency(2, "Noord Holland");
        Constituency constituency2 = new Constituency(3, "Test Constituency 2");
        Party party = new Party(2, "vla");
        Candidate candidate = new Candidate("John", "Joris" ,"Doe");

        party.addOrGetCandidate(candidate);
        assertTrue(constituency2.register(1, candidate));
        assertFalse(constituency2.register(2, candidate));
        assertTrue(constituency1.register(1, candidate));
    }

}
