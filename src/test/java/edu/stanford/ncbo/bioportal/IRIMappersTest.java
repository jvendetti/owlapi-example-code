package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;
//import org.semarglproject.vocab.OWL;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class IRIMappersTest {

    static final Logger LOG = LoggerFactory.getLogger(SimpleLoadTest.class);

    @Test
    public void testIRIMappers() throws Exception {
        /*
         * Try to load the pizza ontology w/out a mapper. This should fail because the URL that makes up the
         * ontology IRI is invalid (doesn't resolve to an ontology file).
         */
//        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//        OWLOntology ontology = manager.loadOntology(IRI.create("http://www.co-ode.org/ontologies/pizza/pizza.owl"));

        /*
         * Try to load again, this time with an AutoIRIMapper. Loading succeeds.
         */
//        File rootDirectory = new File("src/test/resources/pizza");
//        AutoIRIMapper autoIRIMapper = new AutoIRIMapper(rootDirectory, false);
//        OWLOntologyManager manager1 = OWLManager.createOWLOntologyManager();
//        manager1.getIRIMappers().add(autoIRIMapper);
//
//        IRI pizzaOntologyIRI = IRI.create("http://www.co-ode.org/ontologies/pizza/pizza.owl");
//        OWLOntology ontology1 = manager1.loadOntology(pizzaOntologyIRI);

        /*
         * Important distinction - if you load the ontology file as FileDocumentSource, the
         * mapper isn't used. Mapper isn't necessary because you've loaded from a file rather than an IRI.
         */
        File pizzaFile = new File("src/test/resources/pizza/pizza.owl");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(pizzaFile);
        OWLOntologyManager manager2 = OWLManager.createOWLOntologyManager();
        OWLOntology ontology2 = manager2.loadOntologyFromOntologyDocument(fileDocumentSource);
        System.out.println(ontology2.getOntologyID().getOntologyIRI().get());
        System.out.println(manager2.getOntologyDocumentIRI(ontology2));
        System.out.println(ontology2.getAxiomCount());

        /*
         *
         */
    }

    @Test
    public void testSimpleIRIMapper() throws Exception {
        IRI skosCoreIRI = IRI.create("http://www.w3.org/2004/02/skos/core");
        IRI skosCoreDocumentIRI = IRI.create("file:/Users/vendetti/Downloads/skos.rdf");
        SimpleIRIMapper mapper = new SimpleIRIMapper(skosCoreIRI, skosCoreDocumentIRI);

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        manager.getIRIMappers().add(mapper);

        File broFile = new File("src/test/resources/BRO_v3.2.owl");
        FileDocumentSource fileDocumentSource = new FileDocumentSource(broFile);
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(fileDocumentSource);
        assertNotNull(ontology);
    }
}
