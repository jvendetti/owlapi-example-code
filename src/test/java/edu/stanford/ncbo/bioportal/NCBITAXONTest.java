package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OBODocumentFormat;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class NCBITAXONTest {

    @Test
    public void load_NCBITAXON_OBO_WithDocumentFormat() throws Exception {
        String path = "src/test/resources/ncbitaxon/ncbitaxon.obo";
        FileDocumentSource fileDocumentSource = new FileDocumentSource(new File(path), new OBODocumentFormat());
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

    @Test
    public void load_NCBITAXON_OWL_WithoutDocumentFormat() throws Exception {
        File file = new File("src/test/resources/ncbitaxon/ncbitaxon.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);
        assertNotNull(ontology);
        System.out.println(ontology.getOntologyID());
        System.out.println(ontology.getAxiomCount());
    }

}
