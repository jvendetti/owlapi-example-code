package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.metrics.ReferencedClassCount;
import org.semanticweb.owlapi.metrics.ReferencedDataPropertyCount;
import org.semanticweb.owlapi.metrics.ReferencedIndividualCount;
import org.semanticweb.owlapi.metrics.ReferencedObjectPropertyCount;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class MetricsGenerationTest {

    static final Logger LOG = LoggerFactory.getLogger(MetricsGenerationTest.class);

    @Test
    public void testCountsForBRO() throws OWLOntologyCreationException {
        File file = new File("src/test/resources/BRO_v3.2.owl");
        IRI iri = IRI.create(file);
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntology(iri);

        ReferencedClassCount referencedClassCount = new ReferencedClassCount(ontology);
        referencedClassCount.setImportsClosureUsed(true);
        assertEquals(Integer.valueOf(486), referencedClassCount.getValue());

        ReferencedIndividualCount referencedIndividualCount = new ReferencedIndividualCount(ontology);
        referencedIndividualCount.setImportsClosureUsed(true);
        assertEquals(Integer.valueOf(80), referencedIndividualCount.getValue());

        ReferencedDataPropertyCount referencedDataPropertyCount = new ReferencedDataPropertyCount(ontology);
        referencedDataPropertyCount.setImportsClosureUsed(true);
        ReferencedObjectPropertyCount referencedObjectPropertyCount = new ReferencedObjectPropertyCount(ontology);
        referencedObjectPropertyCount.setImportsClosureUsed(true);
        assertEquals(Integer.valueOf(63), Integer.valueOf(referencedDataPropertyCount.getValue() + referencedObjectPropertyCount.getValue()));
    }

}
