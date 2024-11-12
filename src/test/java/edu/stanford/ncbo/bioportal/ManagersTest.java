package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.MissingImportHandlingStrategy;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ManagersTest {
    static final Logger LOG = LoggerFactory.getLogger(SimpleLoadTest.class);

    @Test
    public void testOWLManagers() throws Exception {
        OWLOntologyManager sourceMgr = OWLManager.createOWLOntologyManager();
        OWLOntologyManager targetMgr = OWLManager.createOWLOntologyManager();

        File rootDirectory = new File("./src/test/resources/pizza");
        AutoIRIMapper autoIRIMapper = new AutoIRIMapper(rootDirectory, true);
        sourceMgr.getIRIMappers().add(autoIRIMapper);

        OWLOntologyLoaderConfiguration conf = new OWLOntologyLoaderConfiguration();
        conf = conf.setMissingImportHandlingStrategy(MissingImportHandlingStrategy.SILENT);

        File file = new File("src/test/resources/pizza/pizza.owl");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(file);
        OWLOntology ontology = sourceMgr.loadOntologyFromOntologyDocument(fileDocumentSource, conf);
    }
}
