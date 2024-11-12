package edu.stanford.ncbo.bioportal;

import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class RDFPropertyTest {
  static final Logger LOG = LoggerFactory.getLogger(RDFPropertyTest.class);

  @Test
  public void testIVOA_ROLES_DATES() throws Exception {
    File file = new File("src/test/resources/date_role.rdf");
    IRI iri = IRI.create(file);
    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    OWLOntology ontology = manager.loadOntology(iri);
    assertNotNull(ontology);

    Set<OWLDataProperty> dataPropertySet = ontology.getDataPropertiesInSignature();
    System.out.println("Data property count: " + dataPropertySet.size());
    dataPropertySet.forEach(dataProp -> System.out.println(dataProp.getIRI()));

    Set<OWLObjectProperty> objectPropertySet = ontology.getObjectPropertiesInSignature();
    System.out.println("Object property count: " + objectPropertySet.size());
    objectPropertySet.forEach(objectProp -> System.out.println(objectProp.getIRI()));

    Set<OWLAnnotationProperty> annotationPropertySet = ontology.getAnnotationPropertiesInSignature();
    System.out.println("Annotation property count: " + annotationPropertySet.size());
    annotationPropertySet.forEach(annotationProp -> System.out.println(annotationProp.getIRI()));
  }
}
