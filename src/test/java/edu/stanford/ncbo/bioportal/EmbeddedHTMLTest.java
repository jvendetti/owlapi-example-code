package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmbeddedHTMLTest {

    @Test
    public void load_EmbeddedHTMLWithLaxParsing_CreatesEmptyOntology() throws Exception {
        File file = new File("src/test/resources/testanchor2.owl");
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(IRI.create(file));
        System.out.println(ontology.isEmpty());
        System.out.println(ontology.getAxiomCount());
        assertTrue(ontology.isEmpty());
        assertEquals(0, ontology.getAxiomCount());
    }

    @Test
    public void load_EmbeddedHTMLWithNonLaxParsing_ExceptionThrown() throws Exception {
        String path = "src/test/resources/testanchor2.owl";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new RDFXMLDocumentFormat());

        OWLOntologyLoaderConfiguration configuration = new OWLOntologyLoaderConfiguration();
        configuration.setStrict(true);

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource, configuration);
    }

}
