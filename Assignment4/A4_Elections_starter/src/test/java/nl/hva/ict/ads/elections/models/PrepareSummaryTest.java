package nl.hva.ict.ads.elections.models;

import nl.hva.ict.ads.utils.PathUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import nl.hva.ict.ads.elections.models.PollingStation;


import javax.xml.stream.XMLStreamException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrepareSummaryTest {

    private Election election;

    @BeforeEach
    public void SetUp() throws XMLStreamException, IOException {
       this.election = Election.importFromDataFolder(PathUtils.getResourcePath("/EML_bestanden_TK2021_HvA_UvA"));

    }
    @Test
    public void testTotalParticipatingParties() {
        System.out.println(election.prepareSummary());
    }
}
