package edu.stanford.ncbo.bioportal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class NCITTest {
    private OWLOntologyManager sourceManager = null;
    private String sourcePath = "src/test/resources/NCITNCBO.owl";

    @Before
    public void setUp() throws Exception {
        sourceManager = OWLManager.createOWLOntologyManager();
    }

    @Test
    public void testNCITOntologyLoad() throws Exception {
        File f = new File(sourcePath);
        OWLOntology ontology = sourceManager.loadOntology(IRI.create(f));
        assertNotNull(ontology);
    }

    @Test
    public void testNCITOntologySave() throws Exception {
        File sourceFile = new File("src/test/resources/NCITNCBO.owl");
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(IRI.create(sourceFile));

        File targetFile = new File("src/test/resources/NCITNCBO_test_save.owl");
        manager.saveOntology(ontology, IRI.create(targetFile));

        // TODO: Examine saved file.
    }

    @After
    public void tearDown() throws Exception {
        File f = new File("src/test/resources/NCITNCBO_test_save.owl");
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
    }
}