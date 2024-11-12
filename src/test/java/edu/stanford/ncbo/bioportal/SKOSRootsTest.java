package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class SKOSRootsTest {

    static final Logger LOG = LoggerFactory.getLogger(SKOSRootsTest.class);

    @Test
    public void testDCMI() throws Exception {
        // Loadable?
        File file = new File("src/test/resources/skos_roots/dcterms.rdf");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);

        // Document format?
        OWLDocumentFormat format = manager.getOntologyFormat(ontology);
        LOG.debug(format.toString());
    }

}
