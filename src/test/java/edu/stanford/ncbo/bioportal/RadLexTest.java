package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class RadLexTest {

    static final Logger LOG = LoggerFactory.getLogger(RadLexTest.class);

    @Test
    public void testRadLexPreferredNames() throws Exception {
        File file = new File("src/test/resources/Radlex3.14.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
    }
}
