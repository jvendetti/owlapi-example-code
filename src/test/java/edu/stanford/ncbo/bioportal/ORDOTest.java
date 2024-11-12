package edu.stanford.ncbo.bioportal;

import org.junit.Ignore;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.NTriplesDocumentFormat;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class ORDOTest {

    static final Logger LOG = LoggerFactory.getLogger(ORDOTest.class);

    @Ignore
    public void testSerializeORDO() throws Exception {
        File source = new File("src/test/resources/ordo_orphanet.owl");
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology sourceOntology = manager.loadOntology(IRI.create(source));
        assertNotNull(sourceOntology);
        LOG.info(String.format("ORDO document format: %s", manager.getOntologyFormat(sourceOntology)));
    }

    @Test
    public void testORDOBlankNodes() throws Exception {
        File source = new File("/Users/jvendetti/Development/Examples/ontologies/ordo/11/ordo_orphanet_part.owl");
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology sourceOntology = manager.loadOntology(IRI.create(source));
        assertNotNull(sourceOntology);
        LOG.info(String.format("ORDO document format: %s", manager.getOntologyFormat(sourceOntology)));

        File output = new File("/Users/jvendetti/Development/Examples/ontologies/ordo/11/out.xrdf");
        manager.saveOntology(sourceOntology, new RDFXMLDocumentFormat(), IRI.create("file:" + output.getAbsolutePath()));
    }

    @Test
    public void testORDONTriples() throws Exception {
        File source = new File("/Users/jvendetti/Development/Examples/ontologies/ordo/11/ordo_orphanet.owl");
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology sourceOntology = manager.loadOntology(IRI.create(source));
        assertNotNull(sourceOntology);

        File output = new File("/Users/jvendetti/Development/Examples/ontologies/ordo/11/out_ntriples.xrdf");
        manager.saveOntology(sourceOntology, new NTriplesDocumentFormat(), IRI.create("file:" + output.getAbsolutePath()));
    }

}
