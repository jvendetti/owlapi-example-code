package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class DMTOTest {

    static final Logger LOG = LoggerFactory.getLogger(DMTOTest.class);

    @Test
    public void testLoad_DMTO_Ontology() throws Exception {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        OWLOntologyLoaderConfiguration conf = new OWLOntologyLoaderConfiguration();
        conf = conf.setMissingImportHandlingStrategy(MissingImportHandlingStrategy.THROW_EXCEPTION);

        File file = new File("src/test/resources/dmto/DMTO.owl");
        FileDocumentSource documentSource = new FileDocumentSource(file);

        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(documentSource, conf);
        assertNotNull(ontology);

        logOntologyBasics("DMTO", ontology);

        //manager.saveOntology(ontology, new RDFXMLDocumentFormat());
    }

    private void logOntologyBasics(String acronym, OWLOntology ontology) {
        LOG.info("{} ontology ID: {}", acronym, ontology.getOntologyID());
        LOG.info("{} version: {}", acronym, ontology.getOntologyID().getVersionIRI());
        LOG.info("{} axiom count: {}", acronym, ontology.getAxiomCount());
    }
}
